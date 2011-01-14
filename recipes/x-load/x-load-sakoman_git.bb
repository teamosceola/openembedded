require x-load.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/x-load-sakoman-git/${MACHINE}"

PV = "1.44ss+${PR}+gitr${SRCREV}"

SRCREV = "33e8e562dd66aadf96b0e0bed0571f196807be9d"
SRC_URI = "git://www.sakoman.com/git/x-loader.git;branch=gnome-r12;protocol=git"

SRCREV_omap4430-sdp = ${AUTOREV}
SRC_URI_omap4430-sdp = "git://dev.omapzoom.org/pub/scm/bootloader/x-loader.git;branch=omap4_dev;protocol=git"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
