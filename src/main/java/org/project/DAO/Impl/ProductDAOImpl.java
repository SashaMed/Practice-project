package org.project.DAO.Impl;
import org.project.DAO.ProductDAO;
import org.project.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDAOImpl implements ProductDAO {
    private Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product entity) {
        String sql = "INSERT INTO \"product\" (uuid, dtcreate, dtupdate, title, calories, proteins, fats, carbohydrates) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            prepareStatementForProductCreateOrUpdate(stmt, entity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create product", e);
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM \"product\"";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(createProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read all products", e);
        }
        return products;
    }

    @Override
    public Product readById(String id) {
        String sql = "SELECT * FROM \"product\" WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, UUID.fromString(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createProductFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read product by id", e);
        }
        return null;
    }

    @Override
    public void update(Product entity) {
        String sql = "UPDATE \"product\" SET dtupdate = ?, title = ?, calories = ?, proteins = ?, fats = ?, carbohydrates = ? WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            prepareStatementForProductCreateOrUpdate(stmt, entity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update product", e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM \"product\" WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, UUID.fromString(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product", e);
        }
    }

    private void prepareStatementForProductCreateOrUpdate(PreparedStatement stmt, Product entity) throws SQLException {
        stmt.setTimestamp(1, entity.getDtUpdate());
        stmt.setString(2, entity.getTitle());
        stmt.setInt(3, entity.getCalories());
        stmt.setDouble(4, entity.getProteins());
        stmt.setDouble(5, entity.getFats());
        stmt.setDouble(6, entity.getCarbohydrates());
        stmt.setObject(7, entity.getUuid());
    }

    private Product createProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getObject("uuid", UUID.class),
                rs.getTimestamp("dtcreate"),
                rs.getTimestamp("dtupdate"),
                rs.getString("title"),
                rs.getInt("calories"),
                rs.getDouble("proteins"),
                rs.getDouble("fats"),
                rs.getDouble("carbohydrates")
        );
    }
}