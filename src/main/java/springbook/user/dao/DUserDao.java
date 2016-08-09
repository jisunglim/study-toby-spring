package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jisung Lim <iejisung@gmail.com>
 */
public class DUserDao extends AbstractUserDao {

  @Override
  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/springbook", "springbook", "springbook"
    );
    return con;
  }
}
