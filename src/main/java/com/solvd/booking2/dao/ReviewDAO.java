package com.solvd.booking2.dao;

import com.solvd.booking2.models.Customer;
import com.solvd.booking2.models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.solvd.booking2.Main.LOGGER;

public class ReviewDAO extends AbstractMySQLDAO implements IReviewDAO {
    @Override
    public Review save(Review review) {
        String sql = "INSERT INTO reviews (rating_id, comment, property_id, customer_id) VALUES (?, ?, ?, ?)";
        Connection con = null;

        if (review.getProperty() == null || review.getProperty().getId() == null) {
            throw new IllegalArgumentException("Property must be provided before saving review");
        }

        try {
            con = connectionPool.getInstance().getConnection();

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, review.getRating().getId());
                stm.setString(2, review.getComment());
                stm.setLong(3, review.getProperty().getId());
                stm.setLong(4, review.getCustomer().getId());

                stm.executeUpdate();
            }

            return review;
        } catch (SQLException e) {
            LOGGER.error("Error saving review", e);
            throw new RuntimeException("Failed to save review", e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }
    }
    @Override
    public Review getById(Long id) {
        String sql = "SELECT id, rating_id, comment, property_id, customer_id FROM reviews WHERE id = ?";
        Connection con = null;
        try {
            con = connectionPool.getInstance().getConnection();
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        Review review = new Review();
                        review.setId(rs.getLong("id"));
                        review.setComment(rs.getString("comment"));

                        Rating rating = new Rating();
                        rating.setId(rs.getLong("rating_id"));
                        review.setRating(rating);

                        Property property = new Property();
                        property.setId(rs.getLong("property_id"));
                        review.setProperty(property);

                        Customer customer = new Customer();
                        customer.setId(rs.getLong("customer_id"));
                        review.setCustomer(customer);

                        return review;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding review by id {}", id, e);
        } finally {
            if (con != null) {
                connectionPool.getInstance().releaseConnection(con);
            }
        }

        return null;
    }

    @Override
    public void update(Review entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Review> findReviewsByPropertyId(Long propertyId) {
        return List.of();
    }

    @Override
    public List<Review> findReviewsByCustomerId(Long customerId) {
        return List.of();
    }
}
