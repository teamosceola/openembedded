require x-load.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/x-load-sakoman-git/${MACHINE}"

PV = "1.44ss+${PR}+gitr${SRCREV}"

SRCREV = ${AUTOREV}
SRC_URI = "git://www.sakoman.com/git/x-loader.git;branch=gnome-r13;protocol=git"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
