package com.example.services;

public class GeneralUtility {
    public static boolean isEmptyOrNull(String data) {
        return (data == null || data.isBlank());
    }

    public static boolean isValidLongNumber(String longNumber) {
        try {
            Long id = Long.parseLong(longNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
