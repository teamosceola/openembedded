DESCRIPTION = "Gives a fake root environment"
HOMEPAGE = "http://fakeroot.alioth.debian.org/"
SECTION = "base"
LICENSE = "GPL"
# fakeroot needs getopt which is provided by the util-linux package
RDEPENDS_${PN} = "util-linux"
PR = "r3"

SRC_URI = "${DEBIAN_MIRROR}/main/f/fakeroot/fakeroot_${PV}.tar.gz \
           file://configure-libtool.patch"
	    
inherit autotools

do_stage() {
        install -d ${STAGING_INCDIR}/fakeroot
        install -m 644 *.h ${STAGING_INCDIR}/fakeroot
        autotools_stage_all
}

SRC_URI[md5sum] = "9441c981bfd4e521abcd5d93385f71d8"
#SRC_URI[md5sum] = "09bdd8641dc62588b1ee69f9ff244e34"
SRC_URI[sha256sum] = "06c2772ae6e446227f6798ad8994fcdb1fe64385bc83a34f7e29fd8af2e4f5da"
#SRC_URI[sha256sum] = "20871f523f24d047deae4614ad20a8d805a1f535b6776f8fc368a40dd30752d7"
