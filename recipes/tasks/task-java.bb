DESCRIPTION = "Base task package for Java"
PR = "r4"
LICENSE = "MIT"

inherit task

RDEPENDS_${PN} = "\
    openjdk-6-jdk \
    classpath-awt \
    java2-runtime \
    librxtx-java \
 "
