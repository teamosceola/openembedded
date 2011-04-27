SRC_URI = "file://85-unionfsboot.sh"
PR = "r0"
RDEPENDS_${PN} = "initramfs-uniboot"
DESCRIPTION = "An initramfs module for setting up a ro unionfs on block devices."

do_install() {
	install -d ${D}/initrd.d
        install -m 0755 ${WORKDIR}/85-unionfsboot.sh ${D}/initrd.d/
}

PACKAGE_ARCH = "all"
FILES_${PN} += " /initrd.d/* "
