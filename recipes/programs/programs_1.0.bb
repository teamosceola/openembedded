DESCRIPTION = "This package sets up the programs folder - Chris Crews"
SECTION = "base"
PR = "r13"

#PACKAGES = "${PN}"

SRC_URI = "\
  file://Programs.tar.gz \
"

S = "${WORKDIR}/Programs"

do_install () {
	install -m 0755 -d ${D}/usr/bin/Programs/Exec
	install -m 0755 -d ${D}/usr/bin/Programs/Fonts
	install -m 0755 -d ${D}/usr/bin/Programs/Graphics
	install -m 0755 -d ${D}/usr/bin/Programs/Config_files
	
	cp -rf ${S} ${D}/usr/bin/.
}

#FILES_${PN} = " \
#  /usr/bin/Programs/* \ 
#  /usr/bin/Programs/Exec/* \
#  /usr/bin/Programs/Fonts/* \
#  /usr/bin/Programs/Graphics/* \
#  /usr/bin/Programs/Config_files/* \
#"

