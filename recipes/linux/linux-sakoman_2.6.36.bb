require linux.inc

DESCRIPTION = "Linux kernel for OMAP processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beagleboard|omap3-multi|overo|omap4-multi|omap4430-panda|omap4430-sdp|rockhopper"

BOOT_SPLASH ?= "logo_linux_clut224-generic.ppm"
PV = "2.6.36"

S = "${WORKDIR}/git"

SRCREV = "e49044c0beb4ffad1c785b9ef40e4f8b13336ac6"
SRC_URI = "git://www.sakoman.com/git/linux-omap-2.6.git;branch=omap-2.6.36;protocol=git \
	   file://defconfig \
           file://${BOOT_SPLASH} \
           "

MUSB_MODE ?= "host"

do_configure_prepend() {

        if [ "${MUSB_MODE}" = "host" ]; then
            sed -i 's:CONFIG_USB_GADGET=y:# CONFIG_USB_GADGET is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_MUSB_HOST is not set:CONFIG_USB_MUSB_HOST=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_PERIPHERAL=y:# CONFIG_USB_MUSB_PERIPHERAL is not set:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_OTG=y:# CONFIG_USB_MUSB_OTG is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_MUSB_HDRC_HCD is not set:CONFIG_USB_MUSB_HDRC_HCD=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_GADGET_MUSB_HDRC=y:# CONFIG_USB_GADGET_MUSB_HDRC is not set:g' ${WORKDIR}/defconfig
        fi

        if [ "${MUSB_MODE}" = "peripheral" ]; then
            sed -i 's:# CONFIG_USB_GADGET is not set:CONFIG_USB_GADGET=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_HOST=y:# CONFIG_USB_MUSB_HOST is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_MUSB_PERIPHERAL is not set:CONFIG_USB_MUSB_PERIPHERAL=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_OTG=y:# CONFIG_USB_MUSB_OTG is not set:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_HDRC_HCD=y:# CONFIG_USB_MUSB_HDRC_HCD is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_GADGET_MUSB_HDRC is not set:CONFIG_USB_GADGET_MUSB_HDRC=y:g' ${WORKDIR}/defconfig
        fi

        if [ "${MUSB_MODE}" = "otg" ]; then
            sed -i 's:# CONFIG_USB_GADGET is not set:CONFIG_USB_GADGET=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_HOST=y:# CONFIG_USB_MUSB_HOST is not set:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_PERIPHERAL=y:# CONFIG_USB_MUSB_PERIPHERAL is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_MUSB_OTG is not set:CONFIG_USB_MUSB_OTG=y:g' ${WORKDIR}/defconfig
            sed -i 's:CONFIG_USB_MUSB_HDRC_HCD=y:# CONFIG_USB_MUSB_HDRC_HCD is not set:g' ${WORKDIR}/defconfig
            sed -i 's:# CONFIG_USB_GADGET_MUSB_HDRC is not set:CONFIG_USB_GADGET_MUSB_HDRC=y:g' ${WORKDIR}/defconfig
        fi
}

