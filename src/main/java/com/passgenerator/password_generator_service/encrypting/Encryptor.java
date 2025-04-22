package com.passgenerator.password_generator_service.encrypting;

public interface Encryptor {
    String encrypt(String data) throws Exception;
    String decrypt(String encryptedData) throws Exception;
}
