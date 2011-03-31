require u-boot.inc

#FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

PV = "2011.03"

SRCREV = ${AUTOREV}
SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=gnome-r13;protocol=git \
	   file://fix-break-caused-by-new-binutils.patch \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

