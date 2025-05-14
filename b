<VirtualHost *:80>
    ServerAdmin webmaster@agenda.edu.pe
    ServerName www.agenda.edu.pe
    ServerAlias agenda.edu.pe
    DocumentRoot /var/www/html/agenda.edu.pe/public_html
    ErrorLog logs/agenda.edu.pe_error_log
    CustomLog logs/agenda.edu.pe_access_log combined
    
    <Directory "/var/www/html/agenda.edu.pe/public_html">
        AllowOverride All
        Require all granted
    </Directory>
</VirtualHost>

<VirtualHost *:80>
    ServerAdmin webmaster@nombres_apellidos.com
    ServerName www.nombres_apellidos.com
    ServerAlias nombres_apellidos.com
    DocumentRoot /var/www/html/nombres_apellidos.com/public_html
    ErrorLog logs/nombres_apellidos.com_error_log
    CustomLog logs/nombres_apellidos.com_access_log combined
    
    <Directory "/var/www/html/nombres_apellidos.com/public_html">
        AllowOverride All
        Require all granted
    </Directory>
</VirtualHost>
