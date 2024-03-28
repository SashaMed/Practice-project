package org.project.DAO.Impl;

import org.project.DAO.UserDAO;
import org.project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        String sql = "INSERT INTO public.\"appuser\" (uuid, dtcreate, dtupdate, mail, fio, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForCreateOrUpdate(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM public.\"appuser\"";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return users;
    }

    @Override
    public User readById(String id) {
        String sql = "SELECT * FROM public.\"appuser\" WHERE uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE public.\"appuser\" SET dtupdate = ?, mail = ?, fio = ?, role = ?, status = ? WHERE uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForCreateOrUpdate(statement, entity);
            statement.setObject(6, entity.getUuid());
            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM public.\"appuser\" WHERE uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void prepareStatementForCreateOrUpdate(PreparedStatement statement, User entity) throws SQLException {
        statement.setObject(1, entity.getUuid());
        statement.setTimestamp(2, entity.getDtCreate());
        statement.setTimestamp(3, entity.getDtUpdate());
        statement.setString(4, entity.getMail());
        statement.setString(5, entity.getFio());
        statement.setString(6, entity.getRole());
        statement.setString(7, entity.getStatus());
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("uuid", UUID.class),
                resultSet.getTimestamp("dtcreate"),
                resultSet.getTimestamp("dtupdate"),
                resultSet.getString("mail"),
                resultSet.getString("fio"),
                resultSet.getString("role"),
                resultSet.getString("status")
        );
    }

    private void handleSQLException(SQLException e) {
        // Централизованная обработка исключений SQL
        throw new RuntimeException("Database error", e);
    }
}
