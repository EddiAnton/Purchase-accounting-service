PurchaseAccountingService-1-0.
Для запуска приложения на локальной машине необходимо выполнить следующие процедуры:
--Восстановить базу данных из файла pa_service.dump
--Подключить базу данных к приложению
--Собрать приложение при помощи Maven

Сервер БД используется для процедур хранения, обработки и извлечения данных.
В качестве базы данных используется Postgresql. Перед запуском приложения
необходимо создать базу данных путем восстановления из дампа и подключить ее
к приложению внеся изменения в файл aplication.properties расположенный в
пакете src/main/resources.

hibernate.connection.url=jdbc:postgresql://localhost:PORT/DB_NAME
hibernate.connection.username=USERNAME
hibernate.connection.password=PASSWORD

PORT, DB_NAME, USERNAME и PASSWORD необходимо заменить на действительные значения.

Перед запуском приложения необходимо собрать его при помощи Maven и получить
.jar файл. Для этого используем команду:
mvn package

Для запуска приложения введите в командной строке:
java -jar target/PurchaseAccountingService-1.0.jar [arguments]

Приложение принимает три аргумента из командной строки
1 - тип операции
    --search - поиск по передаваемым в input.json параметрам
    --stat   - вывод всей статистики в промежутке дат передаваемых в input_stat.json
2 - фвйл *.json с параметрамии поиска
3 - файл *.json для записи результата поиска

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


