package com.solvd.booking2.dao;

import com.solvd.booking2.models.Review;

import java.util.List;

public interface IReviewDAO extends IBaseDAO<Review> {

    List<Review> findReviewsByPropertyId(Long propertyId);
    List <Review> findReviewsByCustomerId(Long customerId);

}