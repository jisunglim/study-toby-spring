package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

/**
 * @author Jisung Lim <iejisung@gmail.com>
 */
public class UserDao {
  public void add(User user) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/springbook", "springbook", "springbook"
    );

    PreparedStatement pstmt = con.prepareStatement(
        "INSERT INTO users(id, name, password) values(?, ?, ?)"
    );

    pstmt.setString(1, user.getId());
    pstmt.setString(2, user.getName());
    pstmt.setString(3, user.getPassword());

    pstmt.executeUpdate();

    pstmt.close();
    con.close();
  }

  public User get(String id) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/springbook", "springbook", "springbook"
    );

    PreparedStatement pstmt = con.prepareStatement(
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
    con.close();

    return user;
  }
}
