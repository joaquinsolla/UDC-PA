# PA Project 

## Requirements

- Node 14.
- Java 11 (tested with AdoptOpenJDK 11).
- Maven 3.8+.
- MySQL 8.

## Database creation

```
Start Mysql server if not running (e.g. mysqld).

mysqladmin -u root create paproject -p
mysqladmin -u root create paprojecttest -p

mysql -u root -p
    CREATE USER 'pa'@'localhost' IDENTIFIED BY 'pa';
    GRANT ALL PRIVILEGES ON paproject.* to 'pa'@'localhost' WITH GRANT OPTION;
    GRANT ALL PRIVILEGES ON paprojecttest.* to 'pa'@'localhost' WITH GRANT OPTION;
    exit
```

## Run

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run

cd frontend
npm install (only first time to download libraries)
npm start
```
