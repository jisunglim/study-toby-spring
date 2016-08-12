package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.8 Configuration Setting Using XML
 * @since   1.4 Inversion of Control
 */
@Configuration
public class DaoFactory {

  @Bean
  public UserDao userDao() {
    UserDao userDao = new UserDao();
    userDao.setConnectionMaker(connectionMaker());
    return userDao;
    // return new UserDao(connectionMaker());
  }

  @Bean
  public ConnectionMaker connectionMaker() {
    CountingConnectionMaker countingConnectionMaker =
        new CountingConnectionMaker();
    countingConnectionMaker.setRealConnectionMaker(realConnectionMaker());
    return countingConnectionMaker;
    // return new CountingConnectionMaker(realConnectionMaker());
  }

  @Bean
  public ConnectionMaker realConnectionMaker() {
    return new DConnectionMaker();
  }
  /*
  public AccountDao accountDao() {
    return new UserDao(connectionMaker());
  }

  public MessageDao messageDao() {
    return new UserDao(connectionMaker());
  }
  */
}
