package com.passgenerator.password_generator_service.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public final class PasswordStrength {

    public static String assessStrength(String password) {
        if (password == null || password.isBlank()) return "Weak";

        int score = 0;
        int length = password.length();

        if (length >= 16)        score += 3;
        else if (length >= 12)   score += 2;
        else if (length >= 8)    score += 1;
        else                     score += 0;

        boolean hasLower   = password.chars().anyMatch(Character::isLowerCase);
        boolean hasUpper   = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit   = password.chars().anyMatch(Character::isDigit);
        boolean hasSymbol  = password.chars()
                .anyMatch(c -> "!@#$%^&*()_+-=[]{}|;':\",./<>?`~".indexOf(c) >= 0);

        if (hasLower && hasUpper) score += 1;
        if (hasDigit)             score += 1;
        if (hasSymbol)            score += 1;

        if (length >= 20 && hasLower && hasUpper && hasDigit && hasSymbol)
            score += 1;

        return switch (score) {
            case 0, 1, 2         -> "Weak";
            case 3, 4            -> "Fair";
            case 5               -> "Strong";
            default              -> "Very Strong";
        };
    }
}
