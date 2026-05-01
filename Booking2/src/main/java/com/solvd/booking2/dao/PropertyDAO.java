package com.solvd.booking2.dao;

import com.solvd.booking2.models.Property;
import com.solvd.booking2.models.PropertyType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.booking2.Main.LOGGER;

public class PropertyDAO extends AbstractMySQLDAO implements IPropertyDAO {
    @Override
    public Property save(Property property) {
        String sql = "INSERT INTO properties (title, property_type_id, price_per_night, max_guests, address_id, host_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;

        if (property.getHostId() == null) {
            throw new IllegalArgumentException("Host must be provided before saving property");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, property.getTitle());
                stm.setLong(2, property.getPropertyType().getId());
                stm.setFloat(3, property.getPricePerNight());
                stm.setLong(4, property.getMaxGuests());
                stm.setLong(5, property.getAddressId());
                stm.setLong(6, property.getHostId().getId());

                stm.executeUpdate();
            }

            return property;
        } catch (SQLException e) {
            LOGGER.error("Error saving property", e);
            throw new RuntimeException("Failed to save property", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

    @Override
    public Property getById(Long id) {
        String sql = "SELECT id, title, property_type_id, price_per_night, max_guests, address_id, host_id " +
                "FROM properties WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Property property = new Property();
                        property.setId(rs.getLong("id"));
                        property.setTitle(rs.getString("title"));
                        property.setPricePerNight(rs.getFloat("price_per_night"));
                        property.setMaxGuests(rs.getLong("max_guests"));
                        property.setAddressId(rs.getLong("address_id"));

                        PropertyType propertyType = new PropertyType();
                        propertyType.setId(rs.getLong("property_type_id"));
                        property.setPropertyType(propertyType);

                        HostId hostId = new HostId();
                        hostId.setId(rs.getLong("host_id"));
                        property.setHostId(hostId);

                        return property;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding property by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }
}
