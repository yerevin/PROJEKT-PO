# PROJEKT-PO MATEUSZ SKULSKI [COLLEGIATE PROJECT]

## MYSQL:

1. Ściągnąć https://dev.mysql.com/downloads/file/?id=480558
2. Wypakować w C:/
3. Otworzyć cmd i wejść do C:/mysql/bin
4. Odpalić `mysqld --initialize-insecure`
5. Odpalić `mysqld --console`
6. Otworzyć nowe cmd i w C:/mysql/bin
7. Odpalić `mysql -u root --skip-password`
8. Odpalić `alter user 'root'@'localhost' identified by 'mypass';
9. Odpalić `quit`
10. Odpalić `mysql -u root -p` potem `mypass`
11. Zaimportować plik `sqldump.sql` jako nową baze przez program do zarządzania bazami lub jego zawartość skopiwować i odpalić jako SQL query

## JAVA:

1. W Netbeans upewnić się że `mysql-connector-java-8.0.13.jar` jest wrzucony do projektu jako Library
2. Odpalić projekt przez komende Run w Netbeans
