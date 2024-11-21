package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//fixme расстояние между названием класса и конструктором хотя бы 1 строку нужно
public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "lastName VARCHAR(100), " +
                "age TINYINT)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection(); Statement st = connection.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //fixme выровнять код
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> user = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement st = connection.createStatement()) {
          ResultSet resultSet = st.executeQuery(sql);
          while (resultSet.next()) {
              String name = resultSet.getString("name");
              String lastName = resultSet.getString("lastName");
              Byte  age = resultSet.getByte("age");
              User user1 = new User(name, lastName, age);
              user.add(user1);
          }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            //fixme неиспользуемая переменная
            int rowsAffected = statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
