# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.7 Dependency Injection 

##### 1. IoC (Inversion of Control) and DI (Dependency Injection)
* IoC cannot fully describe the Spring framework.
* Dependency Injection can describe more clearly How the Spring framework works.
_The main concept of DI (Dependency Injection) is_
    1. _Object reference is injected, or provided, from outside._
    2. _In so doing, dynamically create dependent relations with other objects._
    
##### 2. What is Dependency Relationship?
* Conventional Dependency Relationship in UML
    1. It is established in compile time, or in the point of architect. 
    2. It is directive from an object to other object.
    3. If a change in object A effects a change in object B, than we said that B is dependent on A.
    4. If we use interface and its implementation, than the dependency becomes more weakened.
    
* How about runtime Dependency Relationship?
    1. We cannot predict implemented object of interface, which is actually used.
    2. At runtime, what object will be used by 'Client Object' is decided. 
    3. The object is what we call 'Dependent Object', The process is what we call 'Dependency Injection'.
    
* In conclusion, Dependency Injection is the functioning that satisfies following three conditions.
    1. Runtime Dependency Relationship doesn't revealed on class model or code. 
       For doing so, dependency object should depend on interface.
    2. Runtime Dependency Relationship is decided by the third player such as container or factory.
    3. Dependency Relationship is created by handing a reference of object, which will be used, over anther object.

    **_The main point is that, in runtime, there is someone who helps establish dependency relationship, which is unknown before running the program, between two objects._**
    
* Constructor Before Separating Responsibility for Dependency Relationship
    1. Before separation, we must know which class will be used exactly.
    2. Client object has privilege to decide and maintain the object which is dependent on the client object.
    3. That is, client object decide not only static dependency relationship but also dynamic one.
     
    ```java
    public UserDao() {
      connectionMaker = new DConnectionMaker();
    }
    ```

* After Dependency Injection
    1. DI container handing the reference of DConnectionMaker instance over constructor of ```UserDao``` class as parameter.
    2. We can call Spring container with both IoC container or DI container.

    ```java
    public class UserDao {
      private ConnectionMaker connectionMaker;
      
      public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
      }
      //...
    }
    
    public interface ConnectionMaker {
      Connection makeConnection() throws ClassNotFoundException, SQLException;
    }
    
    public class DConnectionMaker implements ConnectionMaker {//...}
    ```

##### 3. Dependency Lookup and Injection

* Dependency Lookup and Injection
    1. Dependency lookup    : 
       ```daoFactory.connectionMaker();```
    2. Dependency Injection : 
       ```this.connectionMaker = daoFactory.connectionMaker();```

    ```java
    public UserDao() {
      DaoFactory daoFactory = new DaoFactory(); 
      this.connectionMaker = daoFactory.connectionMaker();
    }
    ```

* Or, we can use application context
    1. Dependency lookup    : 
       ```context.getBean("connectionMaker", ConnectionMaker.class);```
    2. Dependency Injection : 
       ```this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);```

    ```java
    public UserDao() {
      AnnotationConfigApplicationContext context =
          new AnnotationConfigApplicationContext(DaoFactory.class);
      this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }
    ```

* What's different between Lookup and Injection
    1. It has almost same functionality and features.
    2. But, Dependency Injection is more simple and clean than Lookup.
    3. Lookup mashes up between modules that has different property.
       (such as between 'Dao object' and 'Factory or Spring API') 
    4. But, sometimes we need to use Dependency Lookup when we use ```main()``` method, because there is no way to get injection from DI container.
       (Especially, in test case such as ```UserDaoTest``` class.)
    5. DI (Dependency Injection) requires that client object also must be the bean object of Spring framework to grant privileges on construction and initialization to application context.
       In other hand, DL do not require that. It only requires that dependent object must be the bean object.

##### 4. Applications of Dependency Injection

* **Case 1** : The exchange of implemented functionality.
    1. Local DB : ```LocalDBConnectionMaker```
    2. Production DB : ```ProductionDBConnectionMaker```

    ```java
    @Bean
    public ConnectionMaker connectionMaker() {
      return new LocalDBConnectionMaker();
    }
    
    @Bean
    public ConnectionMaker connectionMaker() {
      return new ProductionDBConnectionMaker();
    }
    ```


* **Case 2** : Add new functionality.
    1. New functionality : Count the number of connection
    2. Implementation : ```class CountingConnectionMaker```

    **_New Functionality :_**
    ```java
    public class CountingConnectionMaker implements ConnectionMaker {
      int counter = 0;
      private ConnectionMaker realConnectionMaker;
    
      public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
      }
    
      public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();
      }
    
      public int getCounter() {
        return this.counter;
      }
    }
    ```

    **_Configuration :_**
    ```java
    @Configuration
    public class DaoFactory {
    
      @Bean
      public UserDao userDao() {
        return new UserDao(connectionMaker());
      }
    
      @Bean
      public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
      }
    
      @Bean
      public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
      }
    }
    ```
    
    **_TEST :_**
    ```java
    public class UserDaoConnectionCountingTest {
      public static void main(String[] args) throws ClassNotFoundException, SQLException {
    
        ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(DaoFactory.class);
    
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    
        /*
        Code to use userDao...
        */
        
        CountingConnectionMaker ccm =
            applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
    
        System.out.println("Connection counter : " + ccm.getCounter());
      }
    }
    ```

##### 5. DI using method
* We have only used constructor to inject dependency relationship so far, but we can use general method, which is more broadly used than constructor.
    1. DI using constructor
    ```public UserDao(ConnectionMaker connectionMaker, Object... params) {//...}```
    2. DI using setter method
    ```public void setConnectionMaker(ConnectionMaker connectionMaker) {//...}```
    3. DI using general method
    ```public void setUserDao(ConnectionMaker connectionMaker, Object... params) {//...}```
    
    
* Example : DI using setter method
    
    **_UserDao Object :_**
    ```java
    public class UserDao {
    
      private ConnectionMaker connectionMaker;
    
    //  public UserDao(ConnectionMaker connectionMaker) {
    //    this.connectionMaker = connectionMaker;
    //  }
    
      public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
      }
      //...
    }
    ```
    
    **_Configuration :_**
    ```java
    @Bean
    public UserDao userDao() {
      UserDao userDao = new UserDao();
      userDao.setConnectionMaker(connectionMaker());
      return userDao;
      //return new UserDao(connectionMaker());
    }
    ```