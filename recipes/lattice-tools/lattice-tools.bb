DESCRIPTION = "Lattice tools"

SRC_URI = "file://svf2vme12 \
           file://vme12 \
          "

do_install () {
  install -d ${D}${bindir}/
  install -m 0755 ${WORKDIR}/svf2vme12 ${D}${bindir}/
  install -m 0755 ${WORKDIR}/vme12 ${D}${bindir}/
}

