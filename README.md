# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.6 Application Context for Singleton Registry 

##### 1. Singleton
* comparison between simple Factory and Spring framework
###### _TEST 1 : Simple Factory_
```java
UserDao userDao3 = new DaoFactory().userDao();
UserDao userDao4 = new DaoFactory().userDao();

System.out.println(userDao3);
System.out.println(userDao4);

System.out.println(userDao3 == userDao4);
```
###### _OUTPUT 1 : Simple Factory_
```
springbook.user.dao.UserDao@2f686d1f
springbook.user.dao.UserDao@3fee9989
false
```
###### _TEST 2 : Spring framework_
```java
ApplicationContext applicationContext =
    new AnnotationConfigApplicationContext(DaoFactory.class);

UserDao userDao1 = applicationContext.getBean("userDao", UserDao.class);
UserDao userDao2 = applicationContext.getBean("userDao", UserDao.class);

System.out.println(userDao1);
System.out.println(userDao2);

System.out.println(userDao1 == userDao2);
```
###### _OUTPUT 2 : Spring framework_
```
springbook.user.dao.UserDao@4cf777e8
springbook.user.dao.UserDao@4cf777e8
true
```

##### 2. Simple Singleton Pattern
* How to implement Singleton pattern in Java
```
public class UserDao {
  private static UserDao INSTANCE;
  //...
  
  private UserDao(ConnectionMaker connectionMaker) {
    this.connectionMaker = connectionMaker;
  }
  
  public static synchronized UserDao getInstance() {
    if (INSTANCE == null) INSTANCE = new UserDao(???);
    return INSTNCE;
  }
  //...
}
```
* New problems arises
    1. Codes become more complicated.
    2. Separating a decision of ConnectionMaker becomes impossible.

* Universal problem of Singleton pattern
    1. Private constructor -> Impossible to inherit.
    2. Construction and initialization is restricted -> Difficult to test.
    3. Dependant to server environment -> Cannot assure the singularity.
    4. Singleton method can be easily reached whenever or wherever you can call static method getInstance(). 
       -> It makes you easy to be tempted to use singleton object as global state. 
       -> But, OOP recommends to do not have global state.
    
##### 3. Server Application and Singleton Registry
* In Spring framework, it is actively supported to create bean as a singleton in server environment.
* Since the simple singleton pattern has a bunch of problematic disadvantages, Spring provides the functionality, which is called "Singleton Registry", to create and maintain singleton-like object.
  
* What's different in Singleton Registry?
    1. We don't need any static method or private constructor. 
    2. IoC makes it possible to use generally defined object as a singleton instance.
    3. It makes no problem to apply object oriented architecture and principle, and other design patterns. 

##### 4. Singleton and State of object
* Singleton in multithreaded environment
    1. Do NOT make object stateful, must make it stateful.
       -> Do not use field variables
    2. How to deal with data from request, database, server resource, and etc.
       -> Use parameter, local variable, and return value.
 
```java
public class UserDao {
  private ConnectionMaker connectionMaker;
  private Connection c;
  private User user;
  
  public User get(String id) throws ClassNotFoundException, SQLException {
    this.c = connectionMaker.makeConnection();
    //...
    this.user = new User();
    this.user = setId(rs.getString("id"));
    this.user = setName(rs.getString("name"));
    this.user = setPassword(rs.getString("password"));
    //...
    return this.user;
  }
}
```

* Some specific case, we can use field variables to store information.
    1. Read only data
               ```java
               private final int dateInfo;
               ```
               ```java
               private final static int CONSTANT_VALUE;
               ```
    2. Another bean object which is managed by Spring container.
               ```java
               private ConnectionMaker connectionMaker;
               ```

##### 5. Spring Bean Object and Its Scope
* Basically, Scope of Spring bean is singleton. One-container, one-object.
* Other kinds of scope : prototype, request, session, and etc.
* We will deal with, in detail, scope at ch 10.
