package springbook.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.7 Dependency Injection
 * @since   1.7 Dependency Injection
 */
public class UserDaoConnectionCountingTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

    User user = new User();
    user.setId("aeri");
    user.setName("Aeri B");
    user.setPassword("aerib");

    userDao.add(user);

    System.out.println("USER_ID : " + user.getId() + " successfully registered.");

    User user2 = userDao.get(user.getId());

    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println("USER_ID : " + user2.getId() + " successfully accessed.");

    CountingConnectionMaker ccm =
        applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);

    System.out.println("Connection counter : " + ccm.getCounter());
  }
}
