package com.solvd.booking2.dao;

import com.solvd.booking2.models.*;

import java.sql.*;

import static com.solvd.booking2.Main.LOGGER;

public class PaymentDAO extends AbstractMySQLDAO implements IPaymentDAO {

    @Override
    public Payment save(Payment payment) {
        String sql = "INSERT INTO payments (payment_status_id, amount, paid_at, payment_method_id, card_id, booking_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;

        if (payment.getBooking() == null || payment.getBooking().getId() == null) {
            throw new IllegalArgumentException("Booking must be provided before saving payment");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, payment.getPaymentStatus().getId());
                stm.setFloat(2, payment.getAmount());
                stm.setTimestamp(3, Timestamp.valueOf(payment.getPaidAt()));
                stm.setLong(4, payment.getPaymentMethod().getId());
                stm.setLong(5, payment.getCard().getId());
                stm.setLong(6, payment.getBooking().getId());

                stm.executeUpdate();
            }

            return payment;
        } catch (SQLException e) {
            LOGGER.error("Error saving payment", e);
            throw new RuntimeException("Failed to save payment", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }

    @Override
    public Payment getById(Long id) {
        String sql = "SELECT id, payment_status_id, amount, paid_at, payment_method_id, card_id, booking_id " +
                "FROM payments WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Payment payment = new Payment();
                        payment.setId(rs.getLong("id"));
                        payment.setAmount(rs.getFloat("amount"));
                        payment.setPaidAt(rs.getTimestamp("paid_at").toLocalDateTime());

                        PaymentStatus paymentStatus = new PaymentStatus();
                        paymentStatus.setId(rs.getLong("payment_status_id"));
                        payment.setPaymentStatus(paymentStatus);

                        PaymentMethod paymentMethod = new PaymentMethod();
                        paymentMethod.setId(rs.getLong("payment_method_id"));
                        payment.setPaymentMethod(paymentMethod);

                        Card card = new Card();
                        card.setId(rs.getLong("card_id"));
                        payment.setCard(card);

                        Booking booking = new Booking();
                        booking.setId(rs.getLong("booking_id"));
                        payment.setBooking(booking);

                        return payment;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding payment by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }


}
