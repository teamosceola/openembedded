DESCRIPTION = "GNOME frontend for packagekit"
LICENSE = "GPL"

DEPENDS = "packagekit libsexy gconf policykit policykit-gnome libunique gnome-menus devicekit-power"

inherit gnome

SRC_URI[archive.md5sum] = "56a8535ca4e10f6bfaf6092de774859e"
SRC_URI[archive.sha256sum] = "bc68dad11e5c9db89dcd2548a29736456063b38aa99633f9cfc4427a77a4525e"

EXTRA_OECONF = " --enable-compile-warnings=no  --disable-scrollkeeper "

do_configure_prepend() {
	sed -i -e s/help/docs/ Makefile.am 
	sed -i -e s:-Werror::g configure.ac
}

FILES_${PN} += "${datadir}/icons ${datadir}/gnome"
