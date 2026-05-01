package com.solvd.booking2.dao;

import com.solvd.booking2.models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Collator;
import java.time.LocalDateTime;

import static com.solvd.booking2.Main.LOGGER;

public class BookingDAO extends AbstractMySQLDAO implements IBookingDAO {


    @Override
    public Booking save(Booking booking) {

        String sql = "INSERT INTO bookings " +
                "(booking_status_id, total_price, checkin, checkout, created_at, updated_at, property_id, customer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;

        Collator connectionPool;
        try {
            con = connectionPool.getInstance().getConnection();

            LocalDateTime now = LocalDateTime.now();
            booking.setCreatedAt(now);
            booking.setUpdatedAt(now);

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, booking.getBookingStatus().getId());
                stm.setFloat(2, booking.getTotalPrice());
                stm.setTimestamp(3, Timestamp.valueOf(booking.getCheckin()));
                stm.setTimestamp(4, Timestamp.valueOf(booking.getCheckout()));
                stm.setTimestamp(5, Timestamp.valueOf(booking.getCreatedAt()));
                stm.setTimestamp(6, Timestamp.valueOf(booking.getUpdatedAt()));
                stm.setLong(7, booking.getProperty().getId());
                stm.setLong(8, booking.getCustomer().getId());

                stm.executeUpdate();
            }

            return booking;
        } catch (SQLException e) {
            LOGGER.error("Error saving booking", e);
            throw new RuntimeException("Failed to save booking", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }


    @Override
    public Booking getById(Long id) {
        String sql = "SELECT id, booking_status_id, total_price, checkin, checkout, " +
                "created_at, updated_at, property_id, customer_id " +
                "FROM bookings WHERE id = ?";

        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Booking booking = new Booking();

                        booking.setId(rs.getLong("id"));
                        booking.setTotalPrice(rs.getFloat("total_price"));
                        booking.setCheckin(rs.getTimestamp("checkin").toLocalDateTime());
                        booking.setCheckout(rs.getTimestamp("checkout").toLocalDateTime());
                        booking.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                        booking.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                        BookingStatus bookingStatus = new BookingStatus();
                        bookingStatus.setId(rs.getLong("booking_status_id"));
                        booking.setBookingStatus(bookingStatus);

                        Property property = new Property();
                        property.setId(rs.getLong("property_id"));
                        booking.setProperty(property);

                        Customer customer = new Customer();
                        customer.setId(rs.getLong("customer_id"));
                        booking.setCustomer(customer);

                        return booking;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding booking by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }

}