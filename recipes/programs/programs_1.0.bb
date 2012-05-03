DESCRIPTION = "This package sets up the programs folder - Chris Crews"
SECTION = "base"
PR = "r6"

inherit base

PACKAGES = "${PN}"

SRC_URI = "\
  file://Programs.tar.gz \
"

S = "${WORKDIR}/Programs"

do_install () {
	install -m 0755 -d ${D}/home/root/Programs/Exec
	install -m 0755 -d ${D}/home/root/Programs/Fonts
	install -m 0755 -d ${D}/home/root/Programs/Graphics
	install -m 0755 -d ${D}/home/root/Programs/Config_files
	
	install -m 0755 ${S}/* ${D}/home/root/Programs/
	install -m 0755 ${S}/Fonts/* ${D}/home/root/Programs/Fonts/
	install -m 0755 ${S}/Graphics/* ${D}/home/root/Programs/Graphics/
	install -m 0755 ${S}/Config_files/* ${D}/home/root/Programs/Config_files/
	install -m 0755 ${S}/Exec/* ${D}/home/root/Programs/Exec/
}

FILES_${PN} = " \
  /home/root/Programs/* \ 
  /home/root/Programs/Exec/* \
  /home/root/Programs/Fonts/* \
  /home/root/Programs/Graphics/* \
  /home/root/Programs/Config_files/* \
"

