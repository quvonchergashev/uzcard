package com.example.uzcard.service.interfaces;

import com.example.uzcard.entity.Card;
import com.example.uzcard.entity.Token;

import java.util.Optional;

public interface CardService {
     Card findById(Long id);
     Card findByCardNumber(String cardNumber);
     Token addCardForGenerateToken(String cardMask);
     Optional<Card> findByCardNumberForTransaction(String cardNumber);
     Card findByToken(String token);
     Card save(Card card);

     Long refresh(String token);







}
