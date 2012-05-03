DESCRIPTION = "This package provides the init script to turnon the oled - Chris Crews"
SECTION = "base"
PR = "r5"

inherit update-rc.d

INITSCRIPT_NAME = "turnon_oled"
INITSCRIPT_PARAMS = "start 27 S ."

SRC_URI = "\
  file://init \
"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/turnon_oled
}

