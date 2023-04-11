package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection connection = util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String string = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                " name VARCHAR(45) NULL," +
                " lastName VARCHAR(45) NULL, " +
                " age TINYINT NULL)";

        try(Statement statement = connection.createStatement()) {
            statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String string = "DROP TABLE IF EXISTS users";
        try(Statement statement = connection.createStatement()) {
            statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String string = "INSERT INTO mydbtest.users (id, name, lastName, age) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(string)) {
            preparedStatement.setLong(1, 0);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String string = "DELETE FROM `USERS` WHERE `id` = " + id + " LIMIT 1;";

        try(Statement statement = connection.createStatement()) {
            statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String string = "SELECT id, name, lastName, age FROM users";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(string);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String string = "TRUNCATE users";

        try(Statement statement = connection.createStatement()) {
            statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
