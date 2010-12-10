DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT"

DEPENDS = "openssl"

SRC_URI = " \
  http://nodejs.org/dist/node-v${PV}.tar.gz \
  file://libev-cross-cc.patch \
  file://node-cross-cc.patch \
"
SRC_URI[md5sum] = "7f6f99fefef172e0517657d0eb69b59d"
SRC_URI[sha256sum] = "6c964096e2fb7bfa9108b31bdd2a920465a1b7f7a603e3937128eee9538b44bb"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

do_configure () {
  ./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
  make
}

do_install () {
  DESTDIR=${D} oe_runmake install
}

BBCLASSEXTEND = "native"

FILES_${PN}-dev += "${libdir}/node"
