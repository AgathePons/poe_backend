# Usefull commands

## Docker & DB

Dump table 

```cmd
docker compose -p <docker-image_name> exec -it db mysqldump -u <user-name> -c -p -N -t -y --skip-opt --skip-comments --skip-quote-names post-suivi-stagiaire poe
```

For "prod" docker with root user:

```cmd
docker compose -p docker-mysql8-phpmyadmin-phpmyadmin-1 exec -it d186fe20e28546114d2abc07b7cf9ae37412795f74386c18a37810810204c456 mysqldump -u root -c -pazerty -N -t -y --skip-opt --skip-comments --skip-quote-names post-suivi-stagiaire poe
```

```cmd
docker exec -it mysql8 mysqldump -u root -c -pazerty -N -t -y --skip-opt --skip-comments --skip-quote-names post_suivi_stagiaire poe > poe.sql
```