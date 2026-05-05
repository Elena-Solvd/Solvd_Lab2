package com.solvd.booking2.dao;

import com.solvd.booking2.ConnectionPool;
import com.solvd.booking2.models.Address;
import com.solvd.booking2.models.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.booking2.Main.LOGGER;

public class AddressDAO extends AbstractMySQLDAO implements IAddressDAO {


    @Override
    public Address save(Address address) {
        String sql = "INSERT INTO address (city, street, zipcode, country_id) VALUES (?, ?, ?, ?)";
        Connection con = null;

        if (address.getCountry() == null || address.getCountry().getId() == null) {
            throw new IllegalArgumentException("Country must be provided before saving address");
        }

        Collator connectionPool = null;
        try {
            con = ConnectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, address.getCity());
                stm.setString(2, address.getStreet());
                stm.setString(3, address.getZipcode());
                stm.setLong(4, address.getCountry().getId());

                stm.executeUpdate();
            }

            return address;
        } catch (SQLException e) {
            LOGGER.error("Error saving address", e);
            throw new RuntimeException("Failed to save address", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

    @Override
    public Address getById(Long id) {
        String sql = "SELECT id, city, street, zipcode, country_id FROM address WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Address address = new Address();
                        address.setId(rs.getLong("id"));
                        address.setCity(rs.getString("city"));
                        address.setStreet(rs.getString("street"));
                        address.setZipcode(rs.getString("zipcode"));
                        Country country = new Country();
                        country.setId(rs.getLong("country_id"));
                        address.setCountry(country);
                        return address;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding address by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }

    @Override
    public void update(Address entity) {

    }


    @Override
    public List<Address> getByCountry(Long countryId) {
        String sql = " SELECT id, city, street, zipcode, country_id FROM address WHERE country_id = ? ";

        Connection con = null;
        List<Address> addresses = new ArrayList<>();

        Collator connectionPool;
        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, countryId);

                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        Address address = new Address();
                        address.setId(rs.getLong("id"));
                        address.setCity(rs.getString("city"));
                        address.setStreet(rs.getString("street"));
                        address.setZipcode(rs.getString("zipcode"));

                        Country country = new Country();
                        country.setId(rs.getLong("country_id"));
                        address.setCountry(country);

                        addresses.add(address);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding addresses by country id {}", countryId, e);
            throw new RuntimeException("Failed to find addresses by country id " + countryId, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return addresses;
    }


    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM address WHERE id = ?";
        Connection con = null;

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting address by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

    @Override
    public List<Address> findAddressesByCountryId(Long countryId) {
        return List.of();
    }

    @Override
    public Address findAddressByPropertyId(String propertyId) {
        return null;
    }
}