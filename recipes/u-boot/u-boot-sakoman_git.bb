require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

SRCREV = "f0e0469b8ed5fe21d947f366e1894934d51c2c72"

PV = "2010.12+${PR}+git${SRCREV}"

SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=gnome-r12;protocol=git \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

