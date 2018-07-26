package ua.edu.uipa.math.utils;

import java.util.Random;

public class Generator {

    private static final long ID_LEFT_LIMIT = 0L;
    private static final long ID_RIGHT_LIMIT = 100L;
    private static final int STRING_LENGTH = 10;
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = UPPER + LOWER + DIGITS;

    private static Random random = new Random();

    public static long id() {
        return nextLong(ID_LEFT_LIMIT, ID_RIGHT_LIMIT);
    }

    public static long timestamp() {
        return System.currentTimeMillis();
    }

    public static String email(String prefix, String domain) {
        return prefix + nextString(STRING_LENGTH) + '@' + domain;
    }

    public static String nextString(int length) {
        length = length < 1 ? STRING_LENGTH : length;
        char[] buffer = new char[length];
        char[] symbols = SYMBOLS.toCharArray();

        for (int i = 0; i < length; i++) {
            buffer[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }

    public static long nextLong(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
