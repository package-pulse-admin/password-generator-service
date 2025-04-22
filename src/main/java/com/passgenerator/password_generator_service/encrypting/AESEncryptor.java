package com.passgenerator.password_generator_service.encrypting;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component("AES")
public class AESEncryptor extends AbstractEncryptor {

    private final SecretKey key;

    public AESEncryptor() throws Exception {
        var keyGen = KeyGenerator.getInstance(EncryptionType.AES.name());
        keyGen.init(256);
        this.key = keyGen.generateKey();
    }

    @Override
    protected Cipher getCipher(int mode) throws Exception {
        var cipher = Cipher.getInstance(EncryptionType.AES.name());
        cipher.init(mode, key);
        return cipher;
    }
}
