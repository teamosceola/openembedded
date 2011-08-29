DESCRIPTION = "Algorithm::Permute - Handy and fast permutation with object oriented interface"
SECTION = "libs"
RDEPENDS_${PN} += "perl-module-exporter"
PR = "r6"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/E/ED/EDPRATOMO/Algorithm-Permute-${PV}.tar.gz"

S = "${WORKDIR}/Algorithm-Permute-${PV}"

inherit cpan

BBCLASSEXTEND="native"

SRC_URI[md5sum] = "93c9e11af9511390379720fe046fb960"
SRC_URI[sha256sum] = "3a708a475144ad9fd32d02df07703b957535d8b6208b35d28c1055dafeafd2af"

