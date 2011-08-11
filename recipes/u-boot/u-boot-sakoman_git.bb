require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

SRCREV = "b4e7dbd7cb393e6d05373477d7a37ca6ec178c62"

PV = "2010.12+${PR}+git${SRCREV}"

SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=gnome-r12;protocol=git \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

