package com.ra.model.dao;

import com.ra.model.dao.utils.ConnectionDB;
import com.ra.model.dao.utils.Role;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public List<User> getAll() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("select * from user;");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getInt("phone"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return userList;    }

    @Override
    public boolean save(User user) {
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name, email, password, phone, role) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public User getById(Integer userId) {
        User user = new User();
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getInt("phone"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return user;
    }

    @Override
    public boolean update(User user, Integer userId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?, email = ?, password = ?, phone = ?, role = ? where id = ?;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setInt(6, user.getId());

            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Integer userId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?;");
            preparedStatement.setInt(1, userId);

            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public User getByEmail(String email) {
        User user = new User();
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getInt("phone"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return user;
    }
}
