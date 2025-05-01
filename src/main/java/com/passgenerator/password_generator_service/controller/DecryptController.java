package com.passgenerator.password_generator_service.controller;

import com.passgenerator.password_generator_service.encrypting.EncryptionType;
import com.passgenerator.password_generator_service.models.EncryptRequest;
import com.passgenerator.password_generator_service.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/decrypt")
public class DecryptController {

    private final PasswordService passwordService;

    @PostMapping("/{type}")
    public String decrypt(@PathVariable EncryptionType type, @RequestBody EncryptRequest encryptedData) throws Exception {
        return passwordService.getEncryptor(type).decrypt(encryptedData.password());
    }   
}
