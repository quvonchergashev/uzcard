package com.example.uzcard.service.interfaces;

import com.example.uzcard.entity.Card;
import com.example.uzcard.entity.Token;

import java.util.Optional;

public interface TokenService {
    Token save(Token token);
    Optional<Token> findByToken(String token);
}
