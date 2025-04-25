package com.passgenerator.password_generator_service.controller;

import com.passgenerator.password_generator_service.encrypting.EncryptionType;
import com.passgenerator.password_generator_service.encrypting.PasswordEncryptor;
import com.passgenerator.password_generator_service.models.EncryptRequest;
import com.passgenerator.password_generator_service.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/encrypt")
@CrossOrigin(origins = "http://localhost:3000")
public class EncryptController {

    private final PasswordService passwordService;

    @PostMapping("/bcrypt")
    public String bcryptEncrypt(@RequestBody EncryptRequest encryptedData) {
        return PasswordEncryptor.hashPassword(encryptedData.password());
    }

    @PostMapping("/{type}")
    public String encrypt(@PathVariable EncryptionType type, @RequestBody EncryptRequest encryptedData) throws Exception {
        if (type.name().equals(EncryptionType.bcrypt.name())){
            return PasswordEncryptor.hashPassword(encryptedData.password());
        }
        return passwordService.getEncryptor(type).encrypt(encryptedData.password());
    }
}
