require gnome-mplayer.inc

SRC_URI += "file://default.patch \
"

SRC_URI[gnomemplayer.md5sum] = "2c8c7e679fa02e524face3fdb504b39d"
SRC_URI[gnomemplayer.sha256sum] = "4694a4f8409039cef7206eb072952721262dbec372669373b732e617dd5ee530"

PR = "${INC_PR}.0"
