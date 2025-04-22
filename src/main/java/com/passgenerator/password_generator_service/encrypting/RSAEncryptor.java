package com.passgenerator.password_generator_service.encrypting;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component("RSA")
public class RSAEncryptor extends AbstractEncryptor{

    private final KeyPair keyPair;

    public RSAEncryptor() throws Exception {
        var keyGen = KeyPairGenerator.getInstance(EncryptionType.RSA.name());
        keyGen.initialize(2048);
        this.keyPair = keyGen.generateKeyPair();
    }

    @Override
    protected Cipher getCipher(int mode) throws Exception {
        var cipher = Cipher.getInstance(EncryptionType.RSA.name());
        cipher.init(mode, mode == Cipher.ENCRYPT_MODE ? keyPair.getPublic() : keyPair.getPrivate());
        return cipher;
    }
}
