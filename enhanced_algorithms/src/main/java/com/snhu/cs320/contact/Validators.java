package com.snhu.cs320.contact;

final class Validators {
    private Validators() {}

    static String requireNonBlankMax(String value, int maxLen, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " must not be null/blank");
        if (value.length() > maxLen) throw new IllegalArgumentException(field + " must be <= " + maxLen + " characters");
        return value;
    }

    static String requirePhone(String value) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("phone must not be null/blank");
        if (value.length() != 10) throw new IllegalArgumentException("phone must be exactly 10 digits");
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) throw new IllegalArgumentException("phone must contain digits only");
        }
        return value;
    }

    static String normalizedLower(String s) {
        return (s == null) ? "" : s.trim().toLowerCase();
    }
}
