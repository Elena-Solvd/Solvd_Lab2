package com.solvd.booking2.dao;

import com.solvd.booking2.models.Photo;
import com.solvd.booking2.models.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.booking2.Main.LOGGER;

public class PhotoDAO extends AbstractMySQLDAO implements IPhotoDAO {

    @Override
    public Photo save(Photo photo) {
        String sql = "INSERT INTO photos (url, property_id) VALUES (?, ?)";
        Connection con = null;

        if (photo.getProperty() == null || photo.getProperty().getId() == null) {
            throw new IllegalArgumentException("Property must be provided before saving photo");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, photo.getUrl());
                stm.setLong(2, photo.getProperty().getId());

                stm.executeUpdate();
            }

            return photo;
        } catch (SQLException e) {
            LOGGER.error("Error saving photo", e);
            throw new RuntimeException("Failed to save photo", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }
    @Override
    public Photo getById(Long id) {
        String sql = "SELECT id, url, property_id FROM photos WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Photo photo = new Photo();
                        photo.setId(rs.getLong("id"));
                        photo.setUrl(rs.getString("url"));

                        Property property = new Property();
                        property.setId(rs.getLong("property_id"));
                        photo.setProperty(property);

                        return photo;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding photo by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }
}