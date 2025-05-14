# Configuración básica
listen=YES
listen_ipv6=NO
anonymous_enable=NO
local_enable=YES
write_enable=YES
local_umask=022
dirmessage_enable=YES
use_localtime=YES
xferlog_enable=YES
connect_from_port_20=YES
secure_chroot_dir=/var/run/vsftpd/empty

# Configuración de chroot
chroot_local_user=YES
allow_writeable_chroot=YES

# Configuración de directorios específicos de usuarios
user_sub_token=$USER
local_root=/var/www/html/$USER

# Configuración de puertos pasivos
pasv_enable=YES
pasv_min_port=40000
pasv_max_port=40100

# Lista de usuarios permitidos
userlist_enable=YES
userlist_file=/etc/vsftpd/vsftpd.userlist
userlist_deny=NO

# Log
xferlog_std_format=YES
xferlog_file=/var/log/xferlog
