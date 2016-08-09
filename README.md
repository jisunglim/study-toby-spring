# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.2-3 Separation of Concerns


##### 1. Extract Redundant Method
* tend to eliminate redundant codes
* extract redundant code and make private method
```java
private Connection getConnection() throws ClassNotFoundException, SQLException {
  Class.forName("com.mysql.jdbc.Driver");
  Connection conn = DriverManger.getConenction(
    "jdbc:mysql://localhost:3306/springbook", "user", "pass");
  return conn;
}

public void add(User user) throws ClassNotFoundException, SQLException {
  Connection conn = getConnection();
  // ...
}

public user get(String id) throws ClassNotFoundException, SQLException {
  Connection conn = getConnection();
  // ...
}
```

##### 2. Template (Factory) Method Pattern
* tend to make DAO to be adaptive to various connection strategies
* use abstract class and inheritance so that throw the responsibility to implement connection strategy 
```java
public abstract class AbstractUserDao {
  protected abstract Connection getConnection() throws ClassNotFoundException, SQLException;
  // ...
}

public class NUserDao extends AbstractUserDao {
  @Override
  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    // suit DB connection to N company
  }
}

public class DUserDao extends AbstractUserDao {
  @Override
  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    // suit DB connection to D company
  }
}
```

##### 3. Extension of DAO
* Uncomfortable with Inheritance, It's too sticky.
* create connection module
```java
public class SimpleConnectionMaker {
  public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
    // ...
    return conn;
  }
}

public class UserDao {

  SimpleConnectionMaker simpleConnectionMaker;
  
  public UserDao() {
    simpleConnectionMaker = new SimpleConnectionMaker();
  }

  public void add(User user) throws ClassNotFoundException, SQLException {
    Connection con = simpleConnectionMaker.makeNewConnection();
    // ...
  }

  public User get(String id) throws ClassNotFoundException, SQLException {
    Connection con = simpleConnectionMaker.makeNewConnection();
    // ...
  }
}
```

##### 4. Introduce Interface
* It's not flexible to various connection strategies, it's worse than inheritance. 
* introduce interface to make proper abstraction
* throw the responsibility to decide which connection maker to DAO
```java
public interface ConnectionMaker {
  Connection makeConnection() throws ClassNotFoundException, SQLException;
}

public class SimpleConnectionMaker implements ConnectionMaker {
  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    // ...
    return conn;
  }
}

public class UserDao {
  private ConnectionMaker connectionMaker;

  public UserDao() {
    connectionMaker = new SimpleConnectionMaker();
  }

  public void add(User user) throws ClassNotFoundException, SQLException {
    Connection con = connectionMaker.makeConnection();
    // ...
  }

  public User get(String id) throws ClassNotFoundException, SQLException {
    Connection con = connectionMaker.makeConnection();
    // ...
  }
}
```

##### 5. Who decide the relation between different modules?
* afraid that DAO decide the relation between itself and another module
* delegate the decision to the client
```java
public class UserDao {
  private ConnectionMaker connectionMaker;

  public UserDao(ConnectionMaker connectionMaker) {
    this.connectionMaker = connectionMaker;
  }
  // ...
}

public class UserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException{
    ConnectionMaker connectionMaker = new SimpleConnectionMaker();

    UserDao userDao = new UserDao(connectionMaker);
    // ...
  }
}
```