# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.7 Configuration Setting Using XML

##### 1. Setting in XML

* Ex 1. realConnectionMaker

    ```java
    @Bean
    public ConnectionMaker realConnectionMaker() {
      return new DConnectionMaker();
    }
    ```

    ```xml
    <bean id="realConnectionMaker" class="springbook.user.dao.DConnectionMaker" />
    ```    

* Ex 2. connectionMaker

    ```java
    @Bean
    public ConnectionMaker connectionMaker() {
      CountingConnectionMaker countingConnectionMaker =
          new CountingConnectionMaker();
      countingConnectionMaker.setRealConnectionMaker(realConnectionMaker());
      return countingConnectionMaker;
      // return new CountingConnectionMaker(realConnectionMaker());
    }
    ```
    
    ```xml
    <bean id="connectionMaker" class="springbook.user.dao.CountingConnectionMaker">
      <property name="realConnectionMaker" ref="realConnectionMaker" />
    </bean>
    ```    
    
* Ex 3. userDao

    ```java
    @Bean
    public UserDao userDao() {
      UserDao userDao = new UserDao();
      userDao.setConnectionMaker(connectionMaker());
      return userDao;
    }
    ```
    
    ```xml
    <bean id="userDao" class="springbook.user.dao.UserDao">
      <property name="connectionMaker" ref="connectionMaker" />
    </bean>
    ```    
    
##### 2. DI information in XML

* Spring application context also can read and use DI information from XML file to configuration.

    |                    | Java Code               | XML Code                   |
    |--------------------|-------------------------|----------------------------|
    | Bean configuration | @Configuration          | <beans>                    |
    | Name of bean       | @Bean methodName()      | <bean id="methodName"      |
    | Class of bean      | return new BeanClass(); | class="a.b.c...BeanClass"> |
    | Dependency Object  | beanClass.setPropertyName(refOfDependencyObject());|<property name="propertyName" ref="refOfDependencyObject" />|

* More generally, a bean object has at most three DI information.
    1. **Name** : method name
       ```@Bean methodName()``` or ```id="methodName"```
    2. **Class**
       ```return new BeanClass();``` or ```class="a.b.c...BeanClass"```
    3. **Dependency Object**
       ```beanClass.setPropertyName(refOfDependencyObject());``` 
       or ```<property name="propertyName" ref="refOfDependencyObject" />```
       

##### 3. Applying DataSource Interface

* We will now use ```DataSource``` Interface.
    ```java
    package javax.sql;
    
    public interface DataSource  extends CommonDataSource, Wrapper {
      Connection getConnection() throws SQLException;
      // ...
    }
    ```

* Refactoring ```UserDao```
    ```java
    public class UserDao {
    
      private DataSource dataSource;

      public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
      }
      
      public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = dataSource.getConnection();
        // ...
      }
    }
    ```
    
* Refactoring ```DaoFactory```
    ```java
    @Configuration
    public class DaoFactory {

      @Bean
      public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbook");
        dataSource.setUsername("springbook");
        dataSource.setPassword("springbook");
        
        return dataSource;
      }

      @Bean
      public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
      }
    }
    ```

* Refactoring ```ApplicationContext.xml```
    ```xml
    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
      <property name="driverClass" ref="com.mysql.jdbc.Driver"/>
      <property name="url" ref="jdbc:mysql://localhost:3306/springbook"/>
      <property name="username" ref="springbook"/>
      <property name="password" ref="springbook"/>
    </bean>
    ```
    







