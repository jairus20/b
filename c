# Puerto para el servidor HTTP (por defecto 8200)
port=8200

# Directorios de medios
media_dir=V,/var/lib/minidlna/media/videos
media_dir=A,/var/lib/minidlna/media/music
media_dir=P,/var/lib/minidlna/media/pictures

# Nombre del servidor que aparecerá en la red
friendly_name=Servidor-MiniDLNA-Fedora

# Directorio para la base de datos
db_dir=/var/cache/minidlna

# Directorio para archivos de log
log_dir=/var/log

# Nivel de log (off, fatal, error, warn, info, debug)
log_level=info

# Habilitar inotify para detectar cambios automáticamente
inotify=yes

# Permitir nuevas conexiones
enable_tivo=no

# Modo estricto de conformidad con DLNA
strict_dlna=no

# Activar presentación de álbumes
album_art_names=Cover.jpg,cover.jpg,AlbumArtSmall.jpg,albumartsmall.jpg
