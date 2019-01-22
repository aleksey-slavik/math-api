package ua.edu.uipa.math.util;

import java.util.Random;

public class PasswordGenerator {

    /**
     * default length of string
     */
    private static final int STRING_LENGTH = 10;

    /**
     * default upper characters
     */
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * default lower characters
     */
    private static final String LOWER = UPPER.toLowerCase();

    /**
     * default digit characters
     */
    private static final String DIGITS = "0123456789";

    /**
     * default alphabet for strings creation
     */
    private static final String SYMBOLS = UPPER + LOWER + DIGITS;

    /**
     * {@link Random} object
     */
    private static Random random = new Random();

    /**
     * Create random password
     *
     * @return {@link String}
     */
    public static String generate() {
        char[] buffer = new char[STRING_LENGTH];
        char[] symbols = SYMBOLS.toCharArray();

        for (int i = 0; i < STRING_LENGTH; i++) {
            buffer[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }
}
