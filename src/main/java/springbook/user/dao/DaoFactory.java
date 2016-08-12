package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;


/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.8 Configuration Setting Using XML (3) Applying {@code DataSource} Interface
 * @since   1.4 Inversion of Control
 */
@Configuration
public class DaoFactory {

  @Bean
  public UserDao userDao() {
    UserDao userDao = new UserDao();
    userDao.setDataSource(dataSource());
    return userDao;
    // return new UserDao(connectionMaker());
  }

  @Bean
  public DataSource dataSource() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
    dataSource.setUrl("jdbc:mysql://localhost:3306/springbook");
    dataSource.setUsername("springbook");
    dataSource.setPassword("springbook");

    return dataSource;
  }

  /*
  @Deprecated
  @Bean
  public ConnectionMaker connectionMaker() {
    CountingConnectionMaker countingConnectionMaker =
        new CountingConnectionMaker();
    countingConnectionMaker.setRealConnectionMaker(realConnectionMaker());
    return countingConnectionMaker;
    // return new CountingConnectionMaker(realConnectionMaker());
  }

  @Deprecated
  @Bean
  public ConnectionMaker realConnectionMaker() {
    return new DConnectionMaker();
  }
  */

  /*
  public AccountDao accountDao() {
    return new UserDao(connectionMaker());
  }

  public MessageDao messageDao() {
    return new UserDao(connectionMaker());
  }
  */
}
