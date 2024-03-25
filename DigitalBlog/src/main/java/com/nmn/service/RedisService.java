package com.nmn.service;


import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    void saveToken(int idUser, String token);
    void deleteToken(int idUser);
    String findTokenByUser(int user);
}
