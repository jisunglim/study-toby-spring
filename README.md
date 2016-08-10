# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.4 Inversion of Control


##### 1. Separate of responsibility to decide relation
* afraid that UserDaoTest undertake another responsibility
* create DaoFactory and Separation the relation-setting responsibility
```java
public class DaoFactory {
  public UserDao userDao() {
    ConnectionMaker connectionMaker = new DConnectionMaker();
    UserDao userDao = new UserDao(connectionMaker);
    return userDao;
  }
}

public class UserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException{
    UserDao userDao = new DaoFactory().userDao();
    // ...
  }
}
```

##### 2. Eliminate redundant code
* Code line to make connection is redundant in DaoFactory.
* Separate connectionMaker as a private method
```java
public class DaoFactory {

  public UserDao userDao() {
    return new UserDao(connectionMaker());
  }
  
  public AccountDao accountDao() {
    return new UserDao(connectionMaker());
  }
  
  public MessageDao messageDao() {
    return new UserDao(connectionMaker());
  }
  
  private ConnectionMaker connectionMaker() {
    return new DConnectionMaker();
  }
}
```
