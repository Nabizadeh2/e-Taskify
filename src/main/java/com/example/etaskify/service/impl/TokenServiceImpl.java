package com.example.etaskify.service.impl;

import com.example.etaskify.entity.UserEntity;

public interface TokenServiceImpl {

    String generateAndSaveRefreshToken(UserEntity userEntity);
    String generateAndSaveConfirmationToken(UserEntity userEntity);

}
