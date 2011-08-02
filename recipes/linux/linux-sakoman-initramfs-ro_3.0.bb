require linux.inc

INITRAMFS_IMAGE = "sakoman-initramfs-image"

DESCRIPTION = "Linux kernel for OMAP processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "omap3-multi|omap4-multi"

BOOT_SPLASH ?= "logo_linux_clut224-generic.ppm"
PV = "3.0"

S = "${WORKDIR}/git"

SRCREV = ${AUTOREV}
SRC_URI = "git://www.sakoman.com/git/linux-omap-2.6.git;branch=omap-3.0-pm;protocol=git \
	   file://defconfig \
           file://${BOOT_SPLASH} \
           "

PROVIDES = ""

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-initramfs-r0-${PV}-${PR}-${MACHINE}"
KERNEL_IMAGE_SYMLINK_NAME = "${KERNEL_IMAGETYPE}-initramfs-ro-${MACHINE}"

do_configure_append() {
sed -i -e /CONFIG_BLK_DEV_INITRD/d \
       -e /CONFIG_KEXEC/d \
       -e /CONFIG_INITRAMFS_SOURCE/d \
       ${S}/.config

echo 'CONFIG_BLK_DEV_INITRD=y
CONFIG_KEXEC=y
CONFIG_INITRAMFS_SOURCE="initramfs.cpio.gz"
CONFIG_MODULES=y' >> ${S}/.config

        yes '' | oe_runmake oldconfig
}

