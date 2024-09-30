package com.example.etaskify.repository;

import com.example.etaskify.entity.TokenEntity;
import com.example.etaskify.entity.UserEntity;
import com.example.etaskify.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity,Long> {

    Optional<TokenEntity> findByTokenTypeAndNameAndAvailable(TokenType tokenType, String name, boolean available);

    Optional<TokenEntity> findByUserAndTokenType(UserEntity userEntity, TokenType tokenType);
}
