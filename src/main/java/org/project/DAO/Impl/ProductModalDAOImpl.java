package org.project.DAO.Impl;

import org.project.DAO.ProductModalDAO;
import org.project.entity.ProductModal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class ProductModalDAOImpl implements ProductModalDAO {
    private final PreparedStatement createStatement;
    private final PreparedStatement readAllStatement;
    private final PreparedStatement readByIdStatement;
    private final PreparedStatement updateStatement;
    private final PreparedStatement deleteStatement;

    public ProductModalDAOImpl(Connection connection) {
        try {
            createStatement = connection.prepareStatement("INSERT INTO \"productmodal\" (uuid, recipeid, productid, weight) VALUES (?, ?, ?, ?)");
            readAllStatement = connection.prepareStatement("SELECT * FROM \"productmodal\"");
            readByIdStatement = connection.prepareStatement("SELECT * FROM \"productmodal\" WHERE uuid = ?");
            updateStatement = connection.prepareStatement("UPDATE \"productmodal\" SET recipeid = ?, productid = ?, weight = ? WHERE uuid = ?");
            deleteStatement = connection.prepareStatement("DELETE FROM \"productmodal\" WHERE uuid = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(ProductModal entity) {
        try {
            createStatement.setObject(1, entity.getUuid());
            createStatement.setObject(2, entity.getRecipeId());
            createStatement.setObject(3, entity.getProductId());
            createStatement.setInt(4, entity.getWeight());
            createStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductModal> readAll() {
        List<ProductModal> productModals = new ArrayList<>();
        try {
            ResultSet result = readAllStatement.executeQuery();
            while (result.next()) {
                ProductModal productModal = new ProductModal(
                        result.getObject("uuid", UUID.class),
                        result.getObject("recipeid", UUID.class),
                        result.getObject("productid", UUID.class),
                        result.getInt("weight")
                );
                productModals.add(productModal);
            }
            return productModals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductModal readById(String id) {
        try {
            readByIdStatement.setObject(1, UUID.fromString(id));
            ResultSet result = readByIdStatement.executeQuery();
            if (result.next()) {
                return new ProductModal(
                        result.getObject("uuid", UUID.class),
                        result.getObject("recipeid", UUID.class),
                        result.getObject("productid", UUID.class),
                        result.getInt("weight")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ProductModal entity) {
        try {
            updateStatement.setObject(1, entity.getRecipeId());
            updateStatement.setObject(2, entity.getProductId());
            updateStatement.setInt(3, entity.getWeight());
            updateStatement.setObject(4, entity.getUuid());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            deleteStatement.setObject(1, UUID.fromString(id));
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}