package springbook.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.6 Application Context for Singleton Registry
 * @since   1.6 Application Context for Singleton Registry
 */
public class UserDaoSingletonTest {
  public static void main(String[] args) {

    // Spring Framework with Inversion of Control

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao userDao1 = applicationContext.getBean("userDao", UserDao.class);
    UserDao userDao2 = applicationContext.getBean("userDao", UserDao.class);

    System.out.println(userDao1);
    System.out.println(userDao2);

    System.out.println(userDao1 == userDao2);

    // Simple Factory with Inversion of Control

    UserDao userDao3 = new DaoFactory().userDao();
    UserDao userDao4 = new DaoFactory().userDao();

    System.out.println(userDao3);
    System.out.println(userDao4);

    System.out.println(userDao3 == userDao4);

  }
}
