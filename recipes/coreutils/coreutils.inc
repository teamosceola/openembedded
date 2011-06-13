DESCRIPTION = "A collection of core GNU utilities."
HOMEPAGE = "http://www.gnu.org/software/coreutils"
LICENSE = "GPLv3"
SECTION = "base"
RREPLACES_${PN} = "textutils shellutils fileutils"
RPROVIDES_${PN} = "textutils shellutils fileutils"
DEPENDS = "gmp"

INC_PR = "r7"
inherit autotools
