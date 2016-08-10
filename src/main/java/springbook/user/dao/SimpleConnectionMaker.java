package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.3 Extension of DAO
 * @since   1.2 Separation of Concerns
 */
public class SimpleConnectionMaker implements ConnectionMaker {

  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/springbook", "springbook", "springbook"
    );
    return conn;
  }
}
