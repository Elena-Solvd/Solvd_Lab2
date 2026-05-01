package com.solvd.booking2.dao;

import com.solvd.booking2.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.booking2.Main.LOGGER;

public class CustomerDAO extends AbstractMySQLDAO implements ICustomerDAO {


    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (phone_number) VALUES (?)";
        Connection con = null;

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, customer.getPhoneNumber());
                stm.executeUpdate();
            }

            return customer;
        } catch (SQLException e) {
            LOGGER.error("Error saving customer", e);
            throw new RuntimeException("Failed to save customer", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

    @Override
    public Customer getById(Long id) {
        String sql = "SELECT id, phone_number FROM customers WHERE id = ?";
        Connection con = null;

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Customer customer = new Customer();
                        customer.setId(rs.getLong("id"));
                        customer.setPhoneNumber(rs.getString("phone_number"));
                        return customer;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding customer by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }



    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        Connection con = null;

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting customer by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

}