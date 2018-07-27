package ua.edu.uipa.math.lib.random;

import java.util.Random;

/**
 * Contains methods of different type generators
 *
 * @author oleksii.slavik
 */
public class Generator {

    /**
     * default minimal value of id
     */
    private static final long ID_LEFT_LIMIT = 0L;

    /**
     * default maximum value of id
     */
    private static final long ID_RIGHT_LIMIT = 100L;

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
     * Create random id
     *
     * @return random id
     */
    public static long id() {
        return nextLong(ID_LEFT_LIMIT, ID_RIGHT_LIMIT);
    }

    /**
     * Create current timestamp
     *
     * @return current timestamp
     */
    public static long timestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Create random email
     *
     * @param prefix email prefix
     * @param domain email domain
     * @return random email
     */
    public static String email(String prefix, String domain) {
        return prefix + nextString(STRING_LENGTH) + '@' + domain;
    }

    /**
     * Create random {@link String} with given length
     *
     * @param length length of {@link String}
     * @return {@link String}
     */
    public static String nextString(int length) {
        length = length < 1 ? STRING_LENGTH : length;
        char[] buffer = new char[length];
        char[] symbols = SYMBOLS.toCharArray();

        for (int i = 0; i < length; i++) {
            buffer[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }

    /**
     * Create random {@link Long} from range
     *
     * @param leftLimit  minimal value
     * @param rightLimit maximum value
     * @return random {@link Long}
     */
    public static long nextLong(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    /**
     * Create random {@link Integer} from range
     *
     * @param leftLimit  minimal value
     * @param rightLimit maximum value
     * @return random {@link Integer}
     */
    public static int nextInt(int leftLimit, int rightLimit) {
        return leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
    }
}
