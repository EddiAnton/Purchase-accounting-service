Восстановление базы данных.
После создания целевой базы данных воспользуйтесь следующей
командой, чтобы восстановить данные в целевую базу данных из
фвйла дампа:
$ pg_restore -v --no-owner --host=<server name> --port=<port> --username=<user@servername> --dbname=<target database name> <database>.dump
-где:
<server name> - имя сервера,
<port> - номер порта,
<user@servername> - имя пользователя,
<target database name> - имя присваиваемое базе данных,
<database> - имя файла дампа.
Если включить параметр --no-owner, все объекты, созданные во время
восстановления, будут присвоены пользователю --username.
Дополнительные сведения см. в официальной документации PostgreSQL по pg_restore.
Если серверу PostgreSQL требуются SSL-подключения, задайте переменную среды
PGSSLMODE=require, чтобы средство pg_restore подключалось через протокол SSL.
Без протокола SSL может отобразиться такая ошибка: FATAL: SSL connection is
required. Please specify SSL options and retry.
В командной строке Windows выполните команду SET PGSSLMODE=require перед
выполнением команды pg_restore. В Linux или Bash выполните команду
export PGSSLMODE=require перед выполнением команды pg_restore.
