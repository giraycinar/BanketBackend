package com.bilgeadam.banket.utility;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@#$%^&+=*!";

    public static String generatePassword() {

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        int passwordLength = 13;

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        int remainingLength = passwordLength - 4;

        for (int i = 0; i < remainingLength; i++) {
            String allChars = UPPER + LOWER + DIGITS + SPECIAL_CHARACTERS;
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(passwordLength);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }

        return password.toString();
    }
}
