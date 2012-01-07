# Gnome demo image

require sakoman-console-image.bb

IMAGE_LINGUAS = "en-gb en-us de-de es-es fr-fr it-it ja-jp"
ROOTFS_POSTPROCESS_COMMAND += 'install_linguas;'
IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

IMAGE_LOGIN_MANAGER = "shadow"
IMAGE_SPLASH = "psplash-sakoman"
DISTRO_SSH_DAEMON = "openssh-sshd"

ANGSTROM_EXTRA_INSTALL ?= ""

IMAGE_INSTALL += " \
  ${ANGSTROM_EXTRA_INSTALL} \
  ${IMAGE_SPLASH} \
  angstrom-task-gnome \
  nfs-utils nfs-utils-client \
  sox \
  task-gnome-apps \
  task-gnome-sdk \
  task-java \
  task-java-gtk \
  ti-msp430-chronos-apps \
  avrdude \
  media-ctl \
  yavta \
  u-boot-mkimage \
#  graphicsmagick \
 "

IMAGE_INSTALL_append_omap3-multi += " \
  libgles-omap3-x11demos \
  libgles-omap3-x11trainingcourses \
  "
