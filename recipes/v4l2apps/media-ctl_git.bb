DESCRIPTION = "Media controller control application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.ideasonboard.org/media-ctl.git;protocol=git"
SRCREV = "2333126b3b5178e4670f01bfeaeec5948abb5546"

PV = "0.0.1"
S = "${WORKDIR}/git"

inherit autotools

# It needs some kernel definitions for v4l2, so it isn't machine specific
STAGING_KERNEL_DIR = "${STAGING_DIR}/${MACHINE_ARCH}${TARGET_VENDOR}-${TARGET_OS}/kernel"
EXTRA_OECONF = "--with-kernel-headers=${STAGING_KERNEL_DIR}"


