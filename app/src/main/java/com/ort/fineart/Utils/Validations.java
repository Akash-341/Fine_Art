package com.ort.fineart.Utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    public static boolean isValidEmail(CharSequence target) {
        return (target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public static boolean isValidMobileNumber(CharSequence target) {
        if (target == null || target.length() != 10) {
            return false;
        }

        String regex = "^[1-9][0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(target).matches();
    }

    public static boolean isValidPassword(String password) {
        // Check for minimum length
        if (password.length() < 8) {
            return false;
        }

        // Check for complexity (e.g., at least one digit, one uppercase letter, and one lowercase letter)
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
        }

        // Password must contain at least one digit, one uppercase letter, and one lowercase letter
        if (!hasDigit || !hasUpperCase || !hasLowerCase) {
            return false;
        }

        // Password meets all criteria
        return true;
    }


    public static boolean validatePin(String pin) {
        // Regular expression for Indian PIN code
        String regex = "^[1-9][0-9]{5}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(pin);

        // Check if the pin matches the pattern
        return matcher.matches();
    }




    public static boolean validateTextOnly(String input) {
        // Regex pattern to match only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }



}
