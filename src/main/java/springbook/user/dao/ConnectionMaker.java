package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.3 (Deprecated)
 * @since   1.3 Extension of DAO
 */
@Deprecated
public interface ConnectionMaker {
  Connection makeConnection() throws ClassNotFoundException, SQLException;
}
