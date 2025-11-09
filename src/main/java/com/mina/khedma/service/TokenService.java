package com.mina.khedma.service;

import com.mina.khedma.DAO.TokenDAO;
import com.mina.khedma.DAO.UserDAO;
import com.mina.khedma.model.enums.TokenType;
import com.mina.khedma.repo.TokenRepo;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepo tokenRepo;

    public TokenService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public void revokeAllUserTokens(UserDAO user) {
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepo.saveAll(validUserTokens);
    }

    public void saveUserToken(UserDAO user, String jwtToken) {
        var token = TokenDAO.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }
}
