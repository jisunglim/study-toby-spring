package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.5 IoC for Spring framework
 * @since   1.4 Inversion of Control
 */
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

  /*
  public AccountDao accountDao() {
    return new UserDao(connectionMaker());
  }

  public MessageDao messageDao() {
    return new UserDao(connectionMaker());
  }
  */
}
