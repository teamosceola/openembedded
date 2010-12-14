require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-sakoman-git/"

SRCREV = "f324e23488854347efc23d8b12c69fb25935e89c"

PV = "2010.12+${PR}+git${SRCREV}"

SRC_URI = "git://www.sakoman.com/git/u-boot.git;branch=omap4-exp;protocol=git \
           file://fw_env.config \
          "
S = "${WORKDIR}/git"

