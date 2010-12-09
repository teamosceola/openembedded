require xapian-quickstartindex_${PV}.bb

DEPENDS = "xapian-core-native"

inherit native

do_stage() {
        install -m 0744 ${WORKDIR}/${BINARY_NAME} ${STAGING_BINDIR}
}

SRC_URI[md5sum] = "757f72c82e68c2422286e6b6299a3652"
SRC_URI[sha256sum] = "58ccae4d28fb6434558764132d5c4e1d87a3134e4acb11eb11f92d2a748118f4"
