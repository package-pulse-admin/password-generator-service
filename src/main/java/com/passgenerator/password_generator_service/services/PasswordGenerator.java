package com.passgenerator.password_generator_service.services;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?";
    private static final Random random = new SecureRandom();

    public static String generatePassword(int length, boolean useUpper, boolean useLower, boolean useDigits, boolean useSymbols) {
        StringBuilder charPool = new StringBuilder();
        if (useUpper) charPool.append(UPPERCASE);
        if (useLower) charPool.append(LOWERCASE);
        if (useDigits) charPool.append(DIGITS);
        if (useSymbols) charPool.append(SYMBOLS);

        if (charPool.length() == 0) throw new IllegalArgumentException("At least one character type must be selected");

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }
        return password.toString();
    }
}
