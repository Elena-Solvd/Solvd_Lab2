package com.solvd.booking2.dao;

import com.solvd.booking2.models.Card;
import com.solvd.booking2.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.booking2.Main.LOGGER;

public class CardDAO extends AbstractMySQLDAO implements ICardDAO {

    Override
    public Card save(Card card) {
        String sql = "INSERT INTO cards (card_number, card_holder_name, cvv, valid_to, customer_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;

        if (card.getCustomer() == null || card.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Customer must be provided before saving card");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, card.getCardNumber());
                stm.setString(2, card.getCardHolderName());
                stm.setString(3, card.getCvv());
                stm.setDate(4, java.sql.Date.valueOf(card.getValidTo()));
                stm.setLong(5, card.getCustomer().getId());

                stm.executeUpdate();
            }

            return card;
        } catch (SQLException e) {
            LOGGER.error("Error saving card", e);
            throw new RuntimeException("Failed to save card", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }


    @Override
    public Card getById(Long id) {
        String sql = "SELECT id, card_number, card_holder_name, cvv, valid_to, customer_id FROM cards WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Card card = new Card();
                        card.setId(rs.getLong("id"));
                        card.setCardNumber(rs.getString("card_number"));
                        card.setCardHolderName(rs.getString("card_holder_name"));
                        card.setCvv(rs.getString("cvv"));
                        card.setValidTo(rs.getDate("valid_to").toLocalDate());

                        Customer customer = new Customer();
                        customer.setId(rs.getLong("customer_id"));
                        card.setCustomer(customer);

                        return card;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding card by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }

}