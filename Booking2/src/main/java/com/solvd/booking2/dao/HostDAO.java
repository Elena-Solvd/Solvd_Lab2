package com.solvd.booking2.dao;

import com.solvd.booking2.models.Host;
import com.solvd.booking2.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.booking2.Main.LOGGER;

public class HostDAO extends AbstractMySQLDAO implements IHostDAO {

    @Override
    public Host save(Host host) {
        String sql = "INSERT INTO hosts (is_company, company_name, user_id) VALUES (?, ?, ?)";
        Connection con = null;

        if (host.getUser() == null || host.getUser().getId() == null) {
            throw new IllegalArgumentException("User must be provided before saving host");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setBoolean(1, host.getIsCompany());
                stm.setString(2, host.getCompanyName());
                stm.setLong(3, host.getUser().getId());

                stm.executeUpdate();
            }

            return host;
        } catch (SQLException e) {
            LOGGER.error("Error saving host", e);
            throw new RuntimeException("Failed to save host", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }


    @Override
    public Host getById(Long id) {
        String sql = "SELECT id, is_company, company_name, user_id FROM hosts WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Host host = new Host();
                        host.setId(rs.getLong("id"));
                        host.setIsCompany(rs.getBoolean("is_company"));
                        host.setCompanyName(rs.getString("company_name"));

                        User user = new User();
                        user.setId(rs.getLong("user_id"));
                        host.setUser(user);

                        return host;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding host by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }


}
