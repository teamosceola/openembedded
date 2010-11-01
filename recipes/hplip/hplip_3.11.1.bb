DESCRIPTION = "HP Linux Imaging and Printing"
LICENSE = "GPL, MIT, BSD"

DEPENDS = "cups net-snmp"

SRC_URI = "${SOURCEFORGE_MIRROR}/${PN}/${PN}-${PV}.tar.gz \
	"
inherit autotools

EXTRA_OECONF = ' \
                BUILD_SYS="" HOST_SYS="" \
                --enable-hpijs-install \
                --enable-gui-build \
                --enable-pp-build \
                --enable-policy-kit \
                --disable-scan-build \
                --enable-foomatic-ppd-install \
                --enable-foomatic-drv-install \
                --enable-udev-acl-rules \
               '

# needed by python checks in configure
EXTRA_OEMAKE = 'BUILD_SYS="" HOST_SYS=""'

do_install_prepend() {
	sed -i -e s:SYSFS:ATTRS:g data/rules/55-hpmud.rules
	sed -i -e s:SYSFS:ATTRS:g data/rules/56-hpmud_support.rules
}

PACKAGES += "${PN}-ppd ${PN}-cups ${PN}-backend ${PN}-filter"
RDEPENDS_${PN} += "net-snmp-client python-syslog python-pprint python-compression python-shell python-xml python-unixadmin python-html python-resource python-terminal"

# need to snag the debug file or OE will fail on backend package
FILES_${PN}-dbg += "\
     ${libdir}/cups/backend/.debug \
     ${libdir}/python2.6/site-packages/.debug \
     ${libdir}/cups/filter/.debug "

FILES_${PN}-ppd = "\
     ${datadir}/ppd"

FILES_${PN}-cups = "\
     ${datadir}/cups"

FILES_${PN}-backend = "\
     ${libdir}/cups/backend"

FILES_${PN}-filter = "\
     ${libdir}/cups/filter"

FILES_${PN} += "\
     ${libdir}/python2.6/site-packages/*.so"

SRC_URI[md5sum] = "86f63a00c911313206fef6a147f7faad"
SRC_URI[sha256sum] = "2f5d988ce53c084556425ae6c5e56a9b5cfa3ac0381059eed913b4523bd1c878"

