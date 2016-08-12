package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import springbook.user.domain.User;

/**
 * Here your documentation.
 *
 * @author  Jisung Lim ( iejisung@gmail.com )
 * @version 1.8 Configuration Setting Using XML (3) Applying {@code DataSource} Interface
 * @since   1.1 Distracting DAO
 */
public class UserDao {

  private DataSource dataSource;
  //private ConnectionMaker connectionMaker;

//  public UserDao(ConnectionMaker connectionMaker) {
//    this.connectionMaker = connectionMaker;
//  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    // this.connectionMaker = connectionMaker;
  }

  public void add(User user) throws ClassNotFoundException, SQLException {

    Connection conn = dataSource.getConnection();
    // Coonection conn = connectionMaker.makeConnection();

    PreparedStatement pstmt = conn.prepareStatement(
        "INSERT INTO users(id, name, password) values(?, ?, ?)"
    );

    pstmt.setString(1, user.getId());
    pstmt.setString(2, user.getName());
    pstmt.setString(3, user.getPassword());

    pstmt.executeUpdate();

    pstmt.close();
    conn.close();
  }

  public User get(String id) throws ClassNotFoundException, SQLException {

    Connection conn = dataSource.getConnection();
    // Coonection conn = connectionMaker.makeConnection();

    PreparedStatement pstmt = conn.prepareStatement(
        "SELECT * FROM users WHERE id = ?"
    );

    pstmt.setString(1, id);

    ResultSet rs = pstmt.executeQuery();

    User user = new User();

    if (rs.next()) {
      user.setId(rs.getString("id"));
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
    }

    rs.close();
    pstmt.close();
    conn.close();

    return user;
  }

}
