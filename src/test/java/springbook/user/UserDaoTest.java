package springbook.user;

import java.sql.SQLException;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

/**
 * User Dao Test.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.4 Inversion of Control
 * @since   1.3 Extension of DAO
 */
public class UserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException{

    UserDao userDao = new DaoFactory().userDao();

    User user = new User();
    user.setId("Kim");
    user.setName("Lin Kim");
    user.setPassword("linlim");

    userDao.add(user);

    System.out.println("USER_ID : " + user.getId() + " successfully registered.");

    User user2 = userDao.get(user.getId());

    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println("USER_ID : " + user2.getId() + " successfully accessed.");

  }
}
