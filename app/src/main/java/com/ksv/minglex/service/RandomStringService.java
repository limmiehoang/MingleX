package com.ksv.minglex.service;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service("randomStringService")
public class RandomStringService {

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.next(16) % symbols.length];
        return new String(buf);
    }

    public static final String upper = "ABCDEF";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

//    public static final String alphanum = upper + lower + digits;
    public static final String alphanum = lower + digits;

    private final RandomNumberGenerator random;

    private final char[] symbols;

    private final char[] buf;

    public RandomStringService(int length, RandomNumberGenerator random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomStringService(int length, RandomNumberGenerator random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomStringService(int length) {
        this(length, new BlumBlumShub(512));
    }

    /**
     * Create session identifiers.
     */
    public RandomStringService() {
        this(21);
    }

}

