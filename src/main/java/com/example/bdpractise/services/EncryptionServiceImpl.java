package com.example.bdpractise.services;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public String encrypt(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decrypt(String encryptText) {
        return new String(Base64.getDecoder().decode(encryptText));
    }
}
