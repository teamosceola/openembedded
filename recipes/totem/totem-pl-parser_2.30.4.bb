DESCRIPTION = "Playlist parser for Totem, a GTK2 based media player"
HOMEPAGE = "http://www.gnome.org/projects/totem/"
LICENSE = "LGPLv2"

PR = "r1"

DEPENDS = "gnome-doc-utils gmime eds-dbus"

inherit gnome

SRC_URI[archive.md5sum] = "eaa98c8829aa51a3e8eae3b43371fd36"
SRC_URI[archive.sha256sum] = "6674c46ab66909bf35474183daa72be50ff3f26935ae5fc4dd8037fa967d2c20"

EXTRA_OECONF = "--enable-introspection=no"

# Build of documentation is not yet possible:
do_configure_prepend() {
	sed -i -e s:docs::g ${S}/Makefile.am
}

