DESCRIPTION = "Take photos and videos with your webcam, with fun graphical effects"
LICENSE = "GPLv2"

PR = "r0"

DEPENDS = "gtk+ gstreamer gst-plugins-base libcanberra udev librsvg gnome-desktop eds-dbus"
RRECOMMENDS_${PN} = "gst-plugin-gconfelements gst-plugins-good-meta gst-plugins-base-meta"

inherit gnome

SRC_URI[archive.md5sum] = "e3b822e46b2558d0bbdfa4809d5d3c24"
SRC_URI[archive.sha256sum] = "8debc47113d9889247f411d256a8eca0d8da49e5ab5909c200923945b8d5e087"

FILES_${PN} += "${datadir}/dbus-1"

