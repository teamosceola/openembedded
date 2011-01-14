require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

SRCREV = "259bdf5e681bba335dd7eee858cdefa8ed1c22d0"

PV = "2010.12+${PR}+git${SRCREV}"

SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=gnome-r12;protocol=git \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

