package com.example.lesson10.utils;

import jakarta.validation.constraints.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static final Pattern validEmailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean validEmail(@NotNull String email) {
        return validEmailPattern.matcher(email).matches();
    }

    public static String fileExtension(@NotNull String originalName) {
        return originalName.substring(originalName.lastIndexOf(".") + 1);
    }
}
