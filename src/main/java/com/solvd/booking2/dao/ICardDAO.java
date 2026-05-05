package com.solvd.booking2.dao;

import com.solvd.booking2.models.Card;

import java.util.List;

public interface ICardDAO extends IBaseDAO<Card> {

    Card findCardByCardNumber(String cardNumber);

    List<Card> findCardsByCustomerId(Long customerId);
}