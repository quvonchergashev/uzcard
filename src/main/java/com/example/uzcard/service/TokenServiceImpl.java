package com.example.uzcard.service;

import com.example.uzcard.entity.Token;
import com.example.uzcard.repositories.TokenRepository;
import com.example.uzcard.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }
    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
