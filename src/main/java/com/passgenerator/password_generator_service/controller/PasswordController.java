package com.passgenerator.password_generator_service.controller;

import com.passgenerator.password_generator_service.services.PasswordGenerator;
import com.passgenerator.password_generator_service.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordController {

    private final PasswordService passwordService;

    @GetMapping("/generate")
    public ResponseEntity<String> generatePassword(
            @RequestParam int length,
            @RequestParam(defaultValue = "true") boolean upper,
            @RequestParam(defaultValue = "true") boolean lower,
            @RequestParam(defaultValue = "true") boolean digits,
            @RequestParam(defaultValue = "true") boolean symbols) {

        return ResponseEntity.ok(PasswordGenerator.generatePassword(length, upper, lower, digits, symbols));
    }


    @PostMapping("/check")
    public ResponseEntity<String> checkPasswordStrength(@RequestParam String password) {
        String result = passwordService.evaluatePassword(password);
        return ResponseEntity.ok(result);
    }
}
