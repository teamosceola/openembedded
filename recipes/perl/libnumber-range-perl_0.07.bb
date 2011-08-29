DESCRIPTION = "Number::Range - Perl extension defining ranges of numbers and testing if a number is found in the range. You can also add and delete from this range."
SECTION = "libs"
LICENSE = "Artistic"
RDEPENDS_${PN} += "perl-module-exporter"
PR = "r0"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/L/LA/LARRYSH/Number-Range-${PV}.tar.gz"

S = "${WORKDIR}/Number-Range-${PV}"

inherit cpan

BBCLASSEXTEND="native"

SRC_URI[md5sum] = "800383d2c7c11d9fc1abae511bf79b3e"
SRC_URI[sha256sum] = "247eb404caf258c10fe1208ba9166b711fe6148a7db51cf8b9cbb9320276f76e"

