DESCRIPTION="SoX is the Swiss Army knife of sound processing tools. \
It converts audio files among various standard audio file formats \
and can apply different effects and filters to the audio data."
HOMEPAGE = "http://sox.sourceforge.net"
SECTION = "audio"
LICENSE = "GPL"

SRC_URI = "${SOURCEFORGE_MIRROR}/sox/sox-${PV}.tar.gz"

SRC_URI_virtclass-native = "${SOURCEFORGE_MIRROR}/sox/sox-${PV}.tar.gz"

inherit autotools

do_install() {
	make bindir="${D}${bindir}" libdir="${D}${libdir}" mandir="${D}${mandir}" includedir="${D}${includedir}" install
	rm ${D}${bindir}/rec
	ln -s /usr/bin/play ${D}${bindir}/rec
}

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "e9d35cf3b0f8878596e0b7c49f9e8302"
SRC_URI[sha256sum] = "0668cc087c346f7c4084ae294c676a11ddefb462974bc3f67be30d789c850e8f"

