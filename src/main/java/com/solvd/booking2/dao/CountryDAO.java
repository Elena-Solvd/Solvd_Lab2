package com.solvd.booking2.dao;

import com.solvd.booking2.models.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;

import static com.solvd.booking2.Main.LOGGER;

public class CountryDAO extends AbstractMySQLDAO implements ICountryDAO {

    @Override
    public Country save(Country country) {
        String sql = "INSERT INTO countries (code, name, calling_code) VALUES (?, ?, ?)";
        Connection con = null;

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, country.getCode());
                stm.setString(2, country.getName());
                stm.setInt(3, country.getCallingCode());

                stm.executeUpdate();
            }

            return country;
        } catch (SQLException e) {
            LOGGER.error("Error saving country", e);
            throw new RuntimeException("Failed to save country", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }


    @Override
    public Country getById(Long id) {
        String sql = "SELECT id, code, name, calling_code FROM countries WHERE id = ?";
        Connection con = null;
        Collator connectionPool;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Country country = new Country();
                        country.setId(rs.getLong("id"));
                        country.setCode(rs.getInt("code"));
                        country.setName(rs.getString("name"));
                        country.setCallingCode(rs.getInt("calling_code"));
                        return country;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding country by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }


}
