package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.8 Configuration Setting Using XML
 * @since   1.7 Dependency Injection
 */
public class CountingConnectionMaker implements ConnectionMaker {
  int counter = 0;
  private ConnectionMaker realConnectionMaker;

//  public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
//    this.realConnectionMaker = realConnectionMaker;
//  }

  public void setRealConnectionMaker(ConnectionMaker realConnectionMaker) {
    this.realConnectionMaker = realConnectionMaker;
  }

  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    this.counter++;
    return realConnectionMaker.makeConnection();
  }

  public int getCounter() {
    return this.counter;
  }
}
