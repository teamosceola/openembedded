# Sample initramfs image, very minimal

export IMAGE_BASENAME = "sakoman-initramfs-image"

IMAGE_INSTALL = "busybox-static initramfs-module-unionfs"

ONLINE_PACKAGE_MANAGEMENT = "none"
IMAGE_FSTYPES = "cpio.gz"
IMAGE_LINGUAS = ""

# Remove any kernel-image that the kernel-module-* packages may have pulled in.
PACKAGE_REMOVE = "kernel-image-* update-modules"
ROOTFS_POSTPROCESS_COMMAND += "opkg-cl ${IPKG_ARGS} -force-depends \
                                remove ${PACKAGE_REMOVE};"

inherit image
