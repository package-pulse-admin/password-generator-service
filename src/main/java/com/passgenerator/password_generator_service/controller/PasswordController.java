package com.passgenerator.password_generator_service.controller;

import com.passgenerator.password_generator_service.services.PasswordGenerator;
import com.passgenerator.password_generator_service.services.PasswordService;
import com.passgenerator.password_generator_service.services.PasswordStrength;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
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


    @PostMapping("/check-auto")
    public ResponseEntity<String> checkPasswordStrength(@RequestParam String password) {
        String result = passwordService.evaluatePassword(password);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/check")
    public ResponseEntity<String> check(@RequestParam String password) {
        return ResponseEntity.ok(PasswordStrength.assessStrength(password));
    }
}
