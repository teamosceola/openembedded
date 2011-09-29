DESCRIPTION = "Yet Another V4L2 Test Application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.ideasonboard.org/yavta.git;protocol=git"
SRCREV = "9b242594bfe0256419d5cf2b0701d2997beccc8b"

PV = "0.0"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 yavta ${D}${bindir}
}


