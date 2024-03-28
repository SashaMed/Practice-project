package org.project.DAO.Impl;

import org.project.DAO.RecipeDAO;
import org.project.entity.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeDAOImpl implements RecipeDAO {
    private final PreparedStatement createStatement;
    private final PreparedStatement readAllStatement;
    private final PreparedStatement readByIdStatement;
    private final PreparedStatement updateStatement;
    private final PreparedStatement deleteStatement;

    public RecipeDAOImpl(Connection connection) {
        try {
            createStatement = connection.prepareStatement("INSERT INTO \"recipe\" (uuid, dtcreate, dtupdate, title, categoryid, description) VALUES (?, ?, ?, ?, ?, ?)");
            readAllStatement = connection.prepareStatement("""
                    SELECT r.*, array_agg(p.uuid || ', ' || p.title || ', ' || p.calories || ', ' || p.proteins || ', ' || p.fats || ', ' || p.carbohydrates) AS composition, sum(pm.weight) as weight,
                           sum(p.calories) as calories, sum(p.proteins) as proteins, sum(p.fats) as fats, sum(p.carbohydrates) as carbohydrates, c.title as category
                    FROM "recipe" r
                             LEFT JOIN "productmodal" pm ON pm.recipeid = r.uuid
                             LEFT JOIN "product" p ON pm.productid = p.uuid
                             LEFT JOIN "category" c ON r.categoryid = c.uuid
                    GROUP BY r.uuid, c.title""");
            readByIdStatement = connection.prepareStatement("""
                    SELECT r.*, array_agg(p.uuid || ', ' || p.title || ', ' || p.calories || ', ' || p.proteins || ', ' || p.fats || ', ' || p.carbohydrates) AS composition, sum(pm.weight) as weight,
                           sum(p.calories) as calories, sum(p.proteins) as proteins, sum(p.fats) as fats, sum(p.carbohydrates) as carbohydrates, c.title as category
                    FROM "recipe" r
                             LEFT JOIN "productmodal" pm ON pm.recipeid = r.uuid
                             LEFT JOIN "product" p ON pm.productid = p.uuid
                             LEFT JOIN "category" c ON r.categoryid = c.uuid
                    WHERE r.uuid = ?
                    GROUP BY r.uuid, c.title""");
            updateStatement = connection.prepareStatement("UPDATE \"recipe\" SET dtupdate = ?, title = ?, categoryid = ?, description = ? WHERE uuid = ?");
            deleteStatement = connection.prepareStatement("DELETE FROM \"recipe\" WHERE uuid = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Recipe entity) {
        try {
            createStatement.setObject(1, entity.getUuid());
            createStatement.setTimestamp(2, entity.getDtCreate());
            createStatement.setTimestamp(3, entity.getDtUpdate());
            createStatement.setString(4, entity.getTitle());
            createStatement.setObject(5, entity.getCategoryId());
            createStatement.setString(6, entity.getDescription());
            createStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Recipe> readAll() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            ResultSet result = readAllStatement.executeQuery();
            while (result.next()) {
                Recipe recipe = new Recipe(
                        result.getObject("uuid", UUID.class),
                        result.getTimestamp("dt_create"),
                        result.getTimestamp("dt_update"),
                        result.getString("title"),
                        result.getObject("category_id", UUID.class),
                        result.getString("description"),
                        result.getArray("composition")
                );
                recipes.add(recipe);
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Recipe readById(String id) {
        try {
            readByIdStatement.setObject(1, UUID.fromString(id));
            ResultSet result = readByIdStatement.executeQuery();
            if (result.next()) {
                return new Recipe(
                        result.getObject("uuid", UUID.class),
                        result.getTimestamp("dt_create"),
                        result.getTimestamp("dt_update"),
                        result.getString("title"),
                        result.getObject("category_id", UUID.class),
                        result.getString("description"),
                        result.getArray("composition")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Recipe entity) {
        try {
            updateStatement.setTimestamp(1, entity.getDtUpdate());
            updateStatement.setString(2, entity.getTitle());
            updateStatement.setObject(3, entity.getCategoryId());
            updateStatement.setString(4, entity.getDescription());
            updateStatement.setObject(5, entity.getUuid());
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