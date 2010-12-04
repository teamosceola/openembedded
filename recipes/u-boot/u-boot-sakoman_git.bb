require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

SRCREV = "430c63ecaa47a5efad17274b5de7d589e86d87c2"

PV = "2010.12+${PR}+git${SRCREV}"

SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=omap4-exp;protocol=git \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

