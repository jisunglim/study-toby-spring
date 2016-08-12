package springbook.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.8 Configuration Setting Using XML
 * @since   1.8 Configuration Setting Using XML
 */
public class XMLConfigUserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    ApplicationContext applicationContext =
        new GenericXmlApplicationContext("ApplicationContext.xml");
        //new ClassPathXmlApplicationContext("../../../ApplicationContext.xml", UserDao.class);

    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

    User user = new User();
    user.setId("cccc");
    user.setName("cccc");
    user.setPassword("cccc");

    userDao.add(user);

    System.out.println("USER_ID : " + user.getId() + " successfully registered.");

    User user2 = userDao.get(user.getId());

    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println("USER_ID : " + user2.getId() + " successfully accessed.");

  }

}
