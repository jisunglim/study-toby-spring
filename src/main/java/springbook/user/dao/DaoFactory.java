package springbook.user.dao;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.4 Inversion of Control
 * @since   1.4 Inversion of Control
 */
public class DaoFactory {

  public UserDao userDao() {
    return new UserDao(connectionMaker());
  }

  /*
  public AccountDao accountDao() {
    return new UserDao(connectionMaker());
  }

  public MessageDao messageDao() {
    return new UserDao(connectionMaker());
  }
  */

  private ConnectionMaker connectionMaker() {
    return new DConnectionMaker();
  }
}
