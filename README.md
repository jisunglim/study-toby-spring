# toby-spring-practice
### This project is for practicing SPRING framework.

## Created
### _BY_ [JisungLim](https://github.com/jisunglim)
### _SINCE_ August 9, 2016

## Chapter 1.5 IoC for Spring Framework

##### 0. Spring Framework
* To use spring framework, do import libraries
```
com.springsource.net.sf.cglib-2.2.0.jar
com.springsource.org.apache.commons.logging-1.1.1.jar
com.springframework.asm-3.0.7.RELEASE.jar
com.springframework.beans-3.0.7.RELEASE.jar
com.springframework.context-3.0.7.RELEASE.jar
com.springframework.core-3.0.7.RELEASE.jar
com.springframework.expression-3.0.7.RELEASE.jar
```
* If your project uses Maven, add dependencies
```xml
<project>
  
  <!-- Ommited... -->

  <properties>
    <org.springframework-version>3.0.7.RELEASE</org.springframework-version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
  </properties>

  <dependencies>

    <!--ch 1.5 cglib-->
    <dependency>
      <groupId>cglib</groupId>
      <artifactId>cglib</artifactId>
      <version>2.2</version>
    </dependency>


    <!-- ch 1.5 apache commons logging -->
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.1.1</version>
    </dependency>

    <!-- ch 1.5 spring asm -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-asm</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- ch 1.5 spring beans -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- ch 1.5 spring context -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- ch 1.5 spring core -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>

    <!-- ch 1.5 spring expression language -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>
       
    <!-- Ommited... -->

  </dependencies>

  <!-- Ommited... -->

</project>

```


##### 1. Spring Framework and Application Context
* Let's use spring framework for our project.
* We will use DaoFactory as a configuration file for application context
```java
@Configuration
public class DaoFactory {

  @Bean
  public ConnectionMaker connectionMaker() {
    return new DConnectionMaker();
  }

  @Bean
  public UserDao userDao() {
    return new UserDao(connectionMaker());
  }
}

public class UserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException{

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    //...
  }
}
```
