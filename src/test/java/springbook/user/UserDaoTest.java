package springbook.user;

import java.sql.SQLException;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.SimpleConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

/**
 * @author Jisung Lim <iejisung@gmail.com>
 */
public class UserDaoTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException{
    ConnectionMaker connectionMaker = new SimpleConnectionMaker();

    UserDao userDao = new UserDao(connectionMaker);

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
