DESCRIPTION = "GNOME Media Tools"
LICENSE = "GPL"
SECTION = "x11/gnome"
DEPENDS = "gnome-doc-utils gconf glib-2.0 gstreamer gst-plugins-base gtk+ gdk-pixbuf-csource-native libcanberra libglade libgnomeui libunique libxml2"
# optional for volume control:
DEPENDS += "pulseaudio"

inherit gnome

PR = "r1"

EXTRA_OECONF = "--disable-static --disable-gtk-doc --disable-scrollkeeper"

# FIXME: Native doc processing with xsltproc using docbook needs additional work:
do_configure_prepend() {
	sed -i 's/^\(SUBDIRS = .*\)\(doc\|help\)/\1/' */Makefile.am
}

FILES_${PN} += "${libdir}/libglade/2.0/libgnome-media-profiles.so \
		${datadir}/gstreamer-properties \
		${datadir}/gnome-sound-recorder"

FILES_${PN}-dbg += "${libdir}/libglade/2.0/.debug"

SRC_URI[archive.md5sum] = "027b11fb8b126ab1b25def6b9e121bd3"
SRC_URI[archive.sha256sum] = "994cd9b88ff22e4815585629b14efc828a95284900809b0b4f8ebcde0c6e2926"

