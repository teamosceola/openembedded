# console image

inherit image

DEPENDS = "task-base"

IMAGE_SPLASH = "psplash-chris"

RRECOMMENDS_${PN}_append_omap3-multi = " \
#  libgles-omap3 \
  omap3-writeprom \
 "

IMAGE_EXTRA_INSTALL ?= ""

AUDIO_INSTALL = " \
# alsa-utils \
#  alsa-utils-aplay \
#  alsa-utils-amixer \
#  angstrom-zeroconf-audio \
 "

BASE_INSTALL = " \
  task-base \
  task-base-wifi \
  task-base-touchscreen \
  task-sdl-libs \
 "

FIRMWARE_INSTALL = " \
  linux-firmware-wl12xx \
  marvell-sdio-fw \
  rt73-firmware \
  zd1211-firmware \
 "

TOOLS_INSTALL = " \
  turnonoled \
  turnonlumus \
  programexe \
  shutdown \
#  programs \
  bash \
  bzip2 \
  cpufrequtils \
  devmem2 \
  dhcp-client \
  fbgrab \
  fbida \
  fbset \
  fbset-modes \
  fbv \
  fuser \
  i2c-tools \
  iftop \
  iw \
  lrzsz \
  lsof \
  memtester \
  mkfs-jffs2 \
  mtd-utils \
  ntp ntpdate \
  openssh-misc \
  openssh-scp \
  openssh-ssh \
  procps \
  socat \
  strace \
  sudo \
  syslog-ng \
  task-proper-tools \
  u-boot-sakoman-fw-utils \
  zip \
 "

IMAGE_INSTALL += " \
  ${BASE_INSTALL} \
  ${IMAGE_EXTRA_INSTALL} \
  ${AUDIO_INSTALL} \
  ${FIRMWARE_INSTALL} \
  ${TOOLS_INSTALL} \
  ${IMAGE_SPLASH} \
 "

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

#ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("DISTRO_TYPE", "release", "zap_root_password; ", "",d)}'


