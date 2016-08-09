package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jisung Lim <iejisung@gmail.com>
 */
public interface ConnectionMaker {
  Connection makeConnection() throws ClassNotFoundException, SQLException;
}
