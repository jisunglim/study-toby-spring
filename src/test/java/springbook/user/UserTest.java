package springbook.user;

import java.sql.SQLException;

import springbook.user.dao.AbstractUserDao;
import springbook.user.dao.ExtendsDUserDao;
import springbook.user.domain.User;

/**
 * Simple, ugly test for Abstract User DAO.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.3 (deprecated)
 * @since   1.1
 */
@Deprecated
public class UserTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    AbstractUserDao userDao = new ExtendsDUserDao();

    User user = new User();
    user.setId("Sidney");
    user.setName("Sidney Lee");
    user.setPassword("sidneylee");

    userDao.add(user);

    System.out.println("USER_ID : " + user.getId() + " successfully registered.");

    User user2 = userDao.get(user.getId());

    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println("USER_ID : " + user2.getId() + " successfully accessed.");
  }
}
