# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.1 Distracting DAO
##### Domain : User.java
##### DAO : UserDao.java
##### TestUnit : UserTest.java

##### SQL Setting
```mysql
$ mysql -u root -p

mysql> CREATE USER '<user-name-here>'@'localhost'
     IDENTIFIED BY '<password-here>';

mysql> CREATE DATABASE <database-name-here>;

mysql> GRANT ALL PRIVILEGES ON <database-name-here>.*
                            TO '<user-name-here>'@'localhost'
                 IDENTIFIED BY '<password-here>';

mysql> quit

$ mysql -u <user-name-here> -p

mysql> CREATE TABLE users(
    id varchar(10) PRIMARY KEY,
    name varchar(20) NOT NULL,
    password varchar(10) NOT NULL
);

mysql> DESC users;

mysql> quit
```