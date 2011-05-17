DESCRIPTION = "Tools needed for cross building Ghostscript"
LICENSE = "GPLv2"
HOMEPAGE = "http://www.gnu.org/software/ghostscript/ghostscript.html"
DEPENDS = "tiff-native zlib-native fontconfig-native"

S = "${WORKDIR}/ghostscript-${PV}"
SRC_URI = "http://downloads.ghostscript.com/public/ghostscript-${PV}.tar.gz;name=tarball"

inherit autotools native

SRC_URI[tarball.md5sum] = "5352c1c542cfabe928cfd3a17db051ad"
SRC_URI[tarball.sha256sum] = "2259d2b6a819d8a6daed274681c8de43996ae1767b20bfa0fc78e37a5be96a8b"

EXTRA_OECONF = "--without-x --without-jasper --with-system-libtiff --without-jbig2dec"

do_compile() {
        mkdir -p obj
        for i in genarch genconf mkromfs echogs gendev genht; do
                oe_runmake obj/$i
        done
}

NATIVE_INSTALL_WORKS = "1"

do_install () {
	install -d ${D}${bindir}/gs-tools-${PV}
        for i in genarch genconf mkromfs echogs gendev genht; do
		install -m 755 obj/$i ${D}${bindir}/gs-tools-${PV}/$i
	done
}
