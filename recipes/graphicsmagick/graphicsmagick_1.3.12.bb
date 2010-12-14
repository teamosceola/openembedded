DESCRIPTION = "GraphicsMagick is an image conversion tool"
SECTION = "console/utils"
LICENSE = "MIT"
DEPENDS = "lcms bzip2 jpeg libpng librsvg tiff zlib"
RDEPENDS = "eglibc-gconv-utf-16"

PR = "r1"

inherit autotools binconfig pkgconfig

SRC_URI = "http://sourceforge.net/projects/graphicsmagick/files/graphicsmagick/${PV}/GraphicsMagick-${PV}.tar.bz2 \
          "
S = "${WORKDIR}/GraphicsMagick-${PV}"

SRC_URI[md5sum] = "55182f371f82d5f9367bce04e59bbf25"
SRC_URI[sha256sum] = "4ceea8558c959fba847b2a4cd92232aac37f5f6a59dda234bc17ef83b90e57d7"

do_configure_prepend() {
	sed -i -e 's:-I${freetype_prefix}/include/freetype2 $CPPFLAGS:$CPPFLAGS:g' configure.ac
	sed -i -e 's:$CPPFLAGS -I${xml2_prefix}/include/libxml2:$CPPFLAGS:g' configure.ac
}

FILES_${PN} += " \
                ${libdir}/GraphicsMagick-${PV}/config/ \
                ${datadir}/GraphicsMagick-${PV}/config/ \
               "

