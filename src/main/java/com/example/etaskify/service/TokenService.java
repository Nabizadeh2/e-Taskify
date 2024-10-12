package com.example.etaskify.service;

import ch.qos.logback.core.subst.Token;
import com.example.etaskify.entity.TokenEntity;
import com.example.etaskify.entity.UserEntity;
import com.example.etaskify.enums.TokenType;
import com.example.etaskify.repository.TokenRepository;
import com.example.etaskify.service.impl.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService implements TokenServiceImpl {
    private final TokenRepository tokenRepository;
    //@Value("${app.time.token.confirmation}")
    private long confirmationTime;
    //@Value("${app.time.token.refresh}")
    private long refreshTime;


    @Override
    public String generateAndSaveRefreshToken(UserEntity userEntity) {
        String refreshToken = getRandomUUID();
        tokenRepository.findByUserAndTokenType(userEntity, TokenType.REFRESH).ifPresentOrElse(token -> {
            token.update(refreshToken,refreshTime);
            tokenRepository.save(token);
        },() -> {
            TokenEntity tokenEntity = new TokenEntity(confirmationTime, refreshToken, TokenType.REFRESH, userEntity);
            tokenRepository.save(tokenEntity);
        });
        return refreshToken;
    }

    @Override
    public String generateAndSaveConfirmationToken(UserEntity userEntity) {
        String confirmationToken = getRandomUUID();

        tokenRepository.findByUserAndTokenType(userEntity, TokenType.CONFIRMATION).ifPresentOrElse(token -> {
            token.update(confirmationToken, confirmationTime);
            tokenRepository.save(token);
        }, () -> {
            TokenEntity tokenEntity = new TokenEntity(confirmationTime, confirmationToken, TokenType.CONFIRMATION, userEntity);
            tokenRepository.save(tokenEntity);
        });

        return confirmationToken;
    }



    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
