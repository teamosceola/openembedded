DESCRIPTION = "flash plugin"
LICENSE = "GPL"

DEPENDS = "gtk+ gconf swfdec"
PR = "r1"

inherit gnome pkgconfig

do_configure_prepend() {
	sed -i -e 's/^SWFDEC_MAJORMINOR=0\.8/SWFDEC_MAJORMINOR=0.9/' configure.ac
}


SRC_URI[archive.md5sum] = "28a72e9c2ad79f33e4cf83bbd54fa124"
SRC_URI[archive.sha256sum] = "e684a2ae44ff52b925796734813c32fa222d4aba0aa85fd77276e99a12317b88"

