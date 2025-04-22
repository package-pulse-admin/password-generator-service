package com.passgenerator.password_generator_service.encrypting;

import javax.crypto.Cipher;
import java.util.Base64;

public abstract class AbstractEncryptor implements Encryptor{

    protected abstract Cipher getCipher(int mode) throws Exception;

    @Override
    public String encrypt(String data) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
