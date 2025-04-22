package com.passgenerator.password_generator_service.services;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.passgenerator.password_generator_service.encrypting.EncryptionType;
import com.passgenerator.password_generator_service.encrypting.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    @Qualifier("AES")
    private Encryptor aesEncryptor;

    @Autowired
    @Qualifier("RSA")
    private Encryptor rsaEncryptor;

    public Encryptor getEncryptor(EncryptionType type) {
        return switch (type) {
            case AES -> aesEncryptor;
            case RSA -> rsaEncryptor;
            default -> throw new IllegalArgumentException("Invalid encryption type");
        };
    }

    public String evaluatePassword(String password) {
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password);
        int score = strength.getScore();

        return switch (score) {
            case 0, 1 -> "Weak";
            case 2 -> "Fair";
            case 3 -> "Strong";
            case 4 -> "Very Strong";
            default -> "Unknown";
        };
    }
}
