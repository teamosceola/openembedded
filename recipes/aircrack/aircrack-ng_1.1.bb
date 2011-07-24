DESCRIPTION = "Aircrack-ng is a set of tools for wep key statistical cracking"
HOMEPAGE = "http://www.aircrack-ng.org/"
SECTION = "console/network"
LICENSE = "GPLv2"
PR = "r4"

SRC_URI = "http://download.aircrack-ng.org/aircrack-ng-${PV}.tar.gz \
           file://channel_minus_one_warning_aireplay-ng-1.1.patch \
          "

BINFILES = "aircrack-ng airdecap-ng packetforge-ng ivstools kstats makeivs-ng airdecloak-ng"
BINFILES += "wesside-ng tkiptun-ng easside-ng"

SBINFILES = "aireplay-ng airodump-ng airserv-ng airtun-ng airbase-ng"
SBINFILES += "buddy-ng"

export TOOL_PREFIX = "${HOST_SYS}-"
CFLAGS += "${LDFLAGS}"

do_compile() {
	oe_runmake SQLITE=true UNSTABLE=true
}

do_install() {
	pwd
	install -d ${D}/${sbindir}
	for i in ${SBINFILES}; do
		install -m 0755 src/$i ${D}/${sbindir}
	done
	install -m 0755 scripts/airdriver-ng ${D}/${sbindir}
	install -m 0755 scripts/airmon-ng ${D}/${sbindir}
	install -m 0755 scripts/airodump-ng-oui-update ${D}/${sbindir}

	install -d ${D}/${bindir}
	for i in ${BINFILES}; do
		install -m 0755 src/$i ${D}/${bindir}
	done
}

SRC_URI[md5sum] = "f7a24ed8fad122c4187d06bfd6f998b4"
SRC_URI[sha256sum] = "b136b549b7d2a2751c21793100075ea43b28de9af4c1969508bb95bcc92224ad"

