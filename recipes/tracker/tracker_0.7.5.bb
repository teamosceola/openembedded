DESCRIPTION = "Tracker is a tool designed to extract information and metadata about your personal data so that it can be searched easily and quickly."
LICENSE = "GPLv2"
DEPENDS = "gnome-panel glib-2.0 libgee file gtk+ gstreamer gamin libgmime dbus dbus-glib poppler libexif libgsf libgnomecanvas sqlite3 sqlite3-native"

inherit gnome

SRC_URI += " \
           file://no-ioprio.patch;patch=1 \
           file://no-trackerd-check.patch;patch=1 \
           file://90tracker \
          "

EXTRA_OECONF += " tracker_cv_have_ioprio=yes"

LEAD_SONAME = "libtrackerclient.so.0"

do_install_append() {
	cp -dPr ${D}${STAGING_DATADIR}/* ${D}${datadir}/ || true 
	install -d ${D}/${sysconfdir}/X11/Xsession.d/
	install -m 0755 ${WORKDIR}/90tracker  ${D}/${sysconfdir}/X11/Xsession.d/
}

FILES_${PN} += "${datadir}/dbus-1/"
FILES_${PN} += "${libdir}/tracker-0.7/*.so*"
FILES_${PN}-dbg += "${libdir}/tracker-0.7/.debug"
FILES_${PN} += "${libdir}/tracker-0.7/extract-modules"
FILES_${PN}-dbg += "${libdir}/tracker-0.7/extract-modules/.debug"
FILES_${PN} += "${libdir}/tracker-0.7/push-modules/daemon"
FILES_${PN}-dbg += "${libdir}/tracker-0.7/push-modules/daemon/.debug"

CONFFILES_${PN} += "${sysconfdir}/X11/Xsession.d/90tracker"

SRC_URI[archive.md5sum] = "82eeefdb69412539b6161b1189eb3861"
SRC_URI[archive.sha256sum] = "be1fc592ae5b7d491cf9ef0790148b342e860a33f3dca6ed91ef6b72c24aa0f1"



