package com.example.lesson10.utils;

import jakarta.validation.constraints.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static String encode(@NotNull String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean check(@NotNull String rawPassword, @NotNull String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
