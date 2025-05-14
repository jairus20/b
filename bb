sudo dnf install httpd vsftpd -y

sudo mkdir -p /var/www/html/jairoparedes.com/public
sudo mkdir -p /var/www/html/agenda.edu.pe/public

echo "<html><head><title>www.jairoparedes.com</title></head><body><h1>Bienvenido a jairoparedes.com</h1></body></html>" | sudo tee /var/www/html/jairoparedes.com/public/index.html
echo "<html><head><title>www.agenda.edu.pe</title></head><body><h1>Bienvenido a agenda.edu.pe</h1></body></html>" | sudo tee /var/www/html/agenda.edu.pe/public/index.html

sudo bash -c 'cat > /etc/httpd/conf.d/jairoparedes.com.conf << EOL
<VirtualHost *:80>
    ServerName www.jairoparedes.com
    ServerAlias jairoparedes.com
    DocumentRoot /var/www/html/jairoparedes.com/public
    ErrorLog /var/log/httpd/jairoparedes.com-error.log
    CustomLog /var/log/httpd/jairoparedes.com-access.log combined
    
    <Directory /var/www/html/jairoparedes.com/public>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
    </Directory>
</VirtualHost>
EOL'

sudo bash -c 'cat > /etc/httpd/conf.d/agenda.edu.pe.conf << EOL
<VirtualHost *:80>
    ServerName www.agenda.edu.pe
    ServerAlias agenda.edu.pe
    DocumentRoot /var/www/html/agenda.edu.pe/public
    ErrorLog /var/log/httpd/agenda.edu.pe-error.log
    CustomLog /var/log/httpd/agenda.edu.pe-access.log combined
    
    <Directory /var/www/html/agenda.edu.pe/public>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
    </Directory>
</VirtualHost>
EOL'

sudo cp /etc/vsftpd/vsftpd.conf /etc/vsftpd/vsftpd.conf.backup
sudo bash -c 'cat > /etc/vsftpd/vsftpd.conf << EOL
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
chroot_local_user=YES
allow_writeable_chroot=YES
user_sub_token=\$USER
local_root=/var/www/html/\$USER
pasv_enable=YES
pasv_min_port=40000
pasv_max_port=40100
userlist_enable=YES
userlist_file=/etc/vsftpd/vsftpd.userlist
userlist_deny=NO
xferlog_std_format=YES
xferlog_file=/var/log/xferlog
secure_chroot_dir=/var/run/vsftpd/empty
pam_service_name=vsftpd
EOL'

sudo touch /etc/vsftpd/vsftpd.userlist

sudo useradd -d /var/www/html/jairoparedes.com -s /bin/bash ftp_jairo
sudo useradd -d /var/www/html/agenda.edu.pe -s /bin/bash ftp_agenda

echo "ftp_jairo" | sudo tee -a /etc/vsftpd/vsftpd.userlist
echo "ftp_agenda" | sudo tee -a /etc/vsftpd/vsftpd.userlist

sudo chown -R ftp_nombres:ftp_jairo /var/www/html/jairoparedes.com
sudo chown -R ftp_agenda:ftp_agenda /var/www/html/agenda.edu.pe
sudo chmod -R 755 /var/www/html/jairoparedes.com
sudo chmod -R 755 /var/www/html/agenda.edu.pe


sudo dnf install policycoreutils-python-utils -y
sudo setsebool -P ftpd_full_access 1
sudo setsebool -P httpd_enable_homedirs 1
sudo setsebool -P httpd_read_user_content 1
sudo semanage fcontext -a -t httpd_sys_content_t "/var/www/html/jairoparedes.com(/.*)?"
sudo semanage fcontext -a -t httpd_sys_content_t "/var/www/html/agenda.edu.pe(/.*)?"
sudo restorecon -Rv /var/www/html/

sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=ftp
sudo firewall-cmd --permanent --add-port=40000-40100/tcp
sudo firewall-cmd --reload

sudo systemctl enable httpd
sudo systemctl enable vsftpd
sudo systemctl start httpd
sudo systemctl start vsftpd

sudo passwd ftp_jairo
sudo passwd ftp_agenda
