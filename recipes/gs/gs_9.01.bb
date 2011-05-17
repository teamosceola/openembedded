DESCRIPTION = "An interpreter of the Postscript language"
LICENSE = "GPLv2"
SECTION = "console/utils"
HOMEPAGE = "http://www.gnu.org/software/ghostscript/ghostscript.html"
DEPENDS = "jpeg zlib fontconfig cups gs-tools-native"
PR = "r1"

SRC_URI = "http://downloads.ghostscript.com/public/ghostscript-${PV}.tar.gz;name=tarball \
           file://unix-aux.mak \
           "

SRC_URI[tarball.md5sum] = "5352c1c542cfabe928cfd3a17db051ad"
SRC_URI[tarball.sha256sum] = "2259d2b6a819d8a6daed274681c8de43996ae1767b20bfa0fc78e37a5be96a8b"

S = "${WORKDIR}/ghostscript-${PV}"

inherit autotools

EXTRA_OECONF = "--without-x --with-jasper --with-fontpath=${datadir}/fonts"

TARGET_CC_ARCH += "${LDFLAGS}"

PACKAGES += "cups-gs"

do_configure() {
	# hack script to allow for cross compiling jasper
	sed 's,&& ./configure$,& --host=\$host --build=\$build --target=\$target,g' -i configure
	gnu-configize
	CFLAGS="${CFLAGS} -fPIC" oe_runconf
}

do_configure_append() {
	# copy tools from the native gs build
	mkdir -p obj soobj
        for i in genarch genconf mkromfs echogs gendev genht; do
                cp ${STAGING_BINDIR_NATIVE}/gs-tools-${PV}/$i obj/$i
                cp ${STAGING_BINDIR_NATIVE}/gs-tools-${PV}/$i soobj/$i
        done
        # Prevent mkromfs from being recompiled for the target
        cp ${WORKDIR}/unix-aux.mak base/
}

do_compile_append () {
        oe_runmake CFLAGS="${CFLAGS} -fPIC" so
}

do_install_prepend () {
        sed -e 's#CUPSSERVERBIN=${STAGING_LIBDIR}/cups#CUPSSERVERBIN=${libdir}/cups#g' -i ${S}/Makefile
        sed -e 's#CUPSDATA=${STAGING_DATADIR}/cups#CUPSDATA=${datadir}/cups#g' -i ${S}/Makefile
	# 64 bit Ubuntu cp fails if file exists, so use -f option
        sed -e 's#CP_=cp#CP_=cp -f#g' -i ${S}/base/unixhead.mak

}

do_install_append () {
        oe_runmake 'DESTDIR=${D}' soinstall
	# recent cups expects this file in /usr/share/cups/mime
	# so duplicate it for compatibility
	mkdir -p ${D}/${datadir}/cups/mime/
        cp ${D}/etc/cups/pstoraster.convs ${D}/${datadir}/cups/mime/

	install -d ${D}${incluedir}/ghostscript
	install -m 644 ${S}/psi/*.h ${D}${includedir}/ghostscript/
}

FILES_${PN} += "${datadir}/ghostscript "
FILES_${PN}-dbg += "${libdir}/cups/filter/.debug/ "
FILES_cups-gs += "${libdir}/cups/filter/ ${datadir}/cups/ ${sysconfdir}/cups/*.convs "

RDEPENDS_cups-gs += "gs"
