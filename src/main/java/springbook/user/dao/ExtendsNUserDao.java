package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * N User Dao extends Abstract User Dao.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.3 (deprecated)
 * @since   1.1
 */
@Deprecated
public class ExtendsNUserDao extends AbstractUserDao {

  @Override
  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/springbook", "springbook", "springbook"
    );
    return con;
  }

}
