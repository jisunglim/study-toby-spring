package springbook.user;

import java.sql.SQLException;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

/**
 * @author Jisung Lim <iejisung@gmail.com>
 */
public class UserTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    UserDao userDao = new UserDao();

    User user = new User();
    user.setId("Lim");
    user.setName("Heasung Lim");
    user.setPassword("heasunglim");

    userDao.add(user);

    System.out.println("USER_ID : " + user.getId() + " successfully registered.");

    User user2 = userDao.get(user.getId());

    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println("USER_ID : " + user2.getId() + " successfully accessed.");
  }
}
