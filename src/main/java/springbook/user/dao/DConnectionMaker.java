package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.3 Extension of DAO
 * @since   1.3 Extension of DAO
 */
public class DConnectionMaker implements ConnectionMaker {

  public Connection makeConnection() throws SQLException {
    Connection conn = null;

    try {

      // 1. Load Driver
      Class.forName("com.mysql.jdbc.Driver");

      // 2. Get connection
      String url = "jdbc:mysql://localhost:3306/sprigbook";
      String usr = "springbook";
      String pwd = "springbook";

      conn = DriverManager.getConnection(url, usr, pwd);

    } catch (ClassNotFoundException e) {
      System.out.println("Cannot find driver.");
    }

    return conn;
  }
}
