DESCRIPTION = "This package provides the init script to run the main program - Chris Crews"
SECTION = "base"
PR = "r2"

inherit update-rc.d

INITSCRIPT_NAME = "program_exe"
INITSCRIPT_PARAMS = "start 99 5 ."

SRC_URI = "\
  file://init \
"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/program_exe
}
