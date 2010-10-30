require linux.inc

DESCRIPTION = "Linux kernel for OMAP processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beagleboard|omap3-multi|overo|omap4-multi|omap4430-panda|omap4430-sdp"

BOOT_SPLASH ?= "logo_linux_clut224-generic.ppm"
PV = "2.6.36"

S = "${WORKDIR}/git"

SRCREV = ${AUTOREV}
SRC_URI = "git://www.sakoman.com/git/linux-omap-2.6.git;branch=omap-2.6.36;protocol=git \
	   file://defconfig \
           file://${BOOT_SPLASH} \
           "

