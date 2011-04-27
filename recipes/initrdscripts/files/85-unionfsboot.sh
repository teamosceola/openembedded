#!/bin/sh
# Set up union between a normal block device and tmpfs.

if [ -e "$ROOT_DEVICE" ]; then
    echo "booting from: $ROOT_DEVICE"
    ls -l /
    type=""
    if [ -n "$ROOT_FSTYPE" ]; then
	type="-t $ROOT_FSTYPE"
    fi
    mount -r $type "$ROOT_DEVICE" /mnt/ro || fatal "Unable to mount rootfs device"
    mount -w -t tmpfs tmpfs /mnt/rw || fatal "Unable to mount tmpfs overlay"
    mount -t unionfs -o dirs=/mnt/rw=rw:/mnt/ro=ro unionfs /mnt/union || fatal "Unable to mount union"
    BOOT_ROOT=/mnt/union
fi
