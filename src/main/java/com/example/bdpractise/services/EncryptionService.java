package com.example.bdpractise.services;

public interface EncryptionService {
    String encrypt(String text);

    String decrypt(String encryptText);
}
