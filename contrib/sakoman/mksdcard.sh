#! /bin/sh
# fdisk portion of script based on mkcard.sh v0.4
# (c) Copyright 2009 Graeme Gregory <dp@xora.org.uk>
# Additional functionality by Steve Sakoman
# (c) Copyright 2010-2011 Steve Sakoman <steve@sakoman.com>
# Licensed under terms of GPLv2
#
# Parts of the procudure base on the work of Denys Dmytriyenko
# http://wiki.omap.com/index.php/MMC_Boot_Format

VERSION=0.8
RELEASE="gnome-r13"

export LC_ALL=C

FEEDURL="http://feeds.sakoman.com/feeds/$RELEASE/images"
MOUNT_POINT="/media/sakoman-sd"

PWD=`pwd`
WORK_DIR=$PWD

if [ $# -eq 0 ]; then
	echo ""
	echo "$0 version $VERSION"
	echo "Usage: $0 <drive> <machine> <image> <release>"
	echo "   drive: SD device (i.e. /dev/sdc)"
	echo "   machine: target machine (default overo)"
	echo "            beagleboard"
	echo "            overo"
	echo "   image: image name (default gnome)"
	echo "            console"
	echo "            gnome"
	echo "            minimal"
	echo "   release: release name (default current)"
	echo "            current (most recent build)"
	echo "            build-timestamp (i.e. 201011182053)"
	echo "            local (use files in current directory)"
	echo ""
	exit 1;
fi

if ! id | grep -q root; then
	echo "This utility must be run prefixed with sudo or as root"
	exit 1;
fi

if [ "$1" = "/dev/sda" ] ; then
	echo "Sorry, /dev/sda probably holds your PC's rootfs.  Please specify a SD device."
	exit 1;
fi
DRIVE=$1

if [ -z "$2" ]; then
	MACHINE="overo"
else
	MACHINE=$2
fi

if [ -z "$3" ]; then
	IMAGE="gnome"
else
	IMAGE=$3
fi

if [ -z "$4" ]; then
	RELEASE="current"
else
	RELEASE=$4
fi

case $MACHINE in
	beagleboard )
		ARCH="omap3-multi" ;;
	overo )
		ARCH="omap3-multi" ;;
	rockhopper )
		ARCH="omap3-multi" ;;
	omap4430-panda )
		ARCH="omap4-multi" ;;
	* )
		echo "Error: unkown machine: $MACHINE"
		exit 1;;
esac

MACHURL="$FEEDURL/$MACHINE/$RELEASE"
ARCHURL="$FEEDURL/$ARCH/$RELEASE"

#make sure that the SD card isn't mounted before we start
if [ -b ${DRIVE}1 ]; then
	umount ${DRIVE}1
	umount ${DRIVE}2
elif [ -b ${DRIVE}p1 ]; then
	umount ${DRIVE}p1
	umount ${DRIVE}p2
else
	umount ${DRIVE}
fi

dd if=/dev/zero of=$DRIVE bs=1024 count=1024

SIZE=`fdisk -l $DRIVE | grep Disk | grep bytes | awk '{print $5}'`

echo DISK SIZE – $SIZE bytes

CYLINDERS=`echo $SIZE/255/63/512 | bc`

echo CYLINDERS – $CYLINDERS

# FAT size is 131072 sectors (64MB) less:
#	MBR - 1 sector
#       padding to align to the page size of the underlying flash - 127 sectors
# so we start the first partition at sector 128 and make it 131072 - 128 = 130944 sectors
# second partition starts at 131072 and continues to fill the card
{
echo 128,130944,0x0C,*
echo 131072,,,-
} | sfdisk --force -D -uS -H 255 -S 63 -C $CYLINDERS $DRIVE

sleep 1

if [ -b ${DRIVE}1 ]; then
	PART1=${DRIVE}1
	PART2=${DRIVE}2
elif [ -b ${DRIVE}p1 ]; then
	PART1=${DRIVE}p1
	PART2=${DRIVE}p2
else
	echo "Improper partitioning on $DRIVE"
	exit 1;
fi

umount ${PART1}
mkfs.vfat -F 32 -n boot ${PART1}

umount ${PART2}
mke2fs -j -L rootfs ${PART2}

if [ "$RELEASE" = "local" ]; then
	echo "Using local files"
else
	echo "Backing up previously downloaded files (if any)"
	mv -b MLO MLO.bak
	mv -b u-boot.bin u-boot.bin.bak
	mv -b uImage uImage.bak
	mv -b sakoman-$IMAGE-image.tar.bz2 sakoman-$IMAGE-image.tar.bz2.bak

	echo "Downloading files from $MACHURL"
	if [ "$RELEASE" = "current" ]; then	
		wget $MACHURL/MLO
		wget $MACHURL/u-boot.bin
		wget $ARCHURL/uImage-pm
		mv   uImage-pm uImage
		wget $ARCHURL/sakoman-$IMAGE-image.tar.bz2
	else
		if wget $MACHURL/MLO-$MACHINE-$RELEASE; then
			mv   MLO-$MACHINE-$RELEASE MLO
		fi
     
		if wget $MACHURL/u-boot-$MACHINE-$RELEASE.bin; then
			mv   u-boot-$MACHINE-$RELEASE.bin u-boot.bin
		fi

		if wget $ARCHURL/uImage-pm-$ARCH-$RELEASE.bin; then
			mv   uImage-pm-$ARCH-$RELEASE.bin uImage
		fi

		if wget $ARCHURL/sakoman-$IMAGE-image-$ARCH-$RELEASE.tar.bz2; then
			mv   sakoman-$IMAGE-image-$ARCH-$RELEASE.tar.bz2 sakoman-$IMAGE-image.tar.bz2
		fi
	fi
fi

# create a mount point if it doesn't already exist
if [ ! -d $MOUNT_POINT ]; then
	if ! mkdir $MOUNT_POINT; then
		echo "Could not create mount point: $MOUNT_POINT"
		echo "SD card creation was not successful"
		exit 1;
	fi
fi

if mount -t vfat ${PART1} $MOUNT_POINT; then
	echo "Populating boot partition files"
	cp MLO        $MOUNT_POINT
	cp u-boot.bin $MOUNT_POINT
	cp uImage     $MOUNT_POINT
	sync
	umount $MOUNT_POINT
	echo "Boot partition complete"
else
	echo "Can't mount ${PART1} at $MOUNT_POINT for boot partition creation"
	echo "SD card creation was not successful"
	exit 1;
fi

# this shouldn't be necessary, but Ubuntu seems to remove the mount point!
if [ ! -d $MOUNT_POINT ]; then
	if ! mkdir $MOUNT_POINT; then
		echo "Could not create mount point: $MOUNT_POINT"
		echo "SD card creation was not successful"
		exit 1;
	fi
fi

if mount -t ext3 ${PART2} $MOUNT_POINT; then
	if cd $MOUNT_POINT; then
		echo "Populating rootfs partition files"
		echo "This will take several minutes . . ."
		tar xf $WORK_DIR/sakoman-$IMAGE-image.tar.bz2
		sync
		cd $WORK_DIR
		umount $MOUNT_POINT
		echo "Rootfs partition complete"
	else
		echo "Error populating rootfs partition"
		echo "SD card creation was not successful"
	fi
else
	echo "Can't mount ${PART2} at $MOUNT_POINT for rootfs creation"
	echo "SD card creation was not successful"
	exit 1;
	
fi

echo "SD card creation was successful"

exit 0;


