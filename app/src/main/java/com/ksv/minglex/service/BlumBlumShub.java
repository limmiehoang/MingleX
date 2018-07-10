package com.ksv.minglex.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class BlumBlumShub implements RandomNumberGenerator {

    private static final BigInteger two = BigInteger.valueOf(2L);
    private static final BigInteger three = BigInteger.valueOf(3L);
    private static final BigInteger four = BigInteger.valueOf(4L);

    private BigInteger n;
    private BigInteger state;

    private static BigInteger generatePrime(int bits, Random rand) {
        BigInteger p;
        while(true) {
            p = new BigInteger(bits, 100, rand);
            if (p.mod(four).equals(three)) {
                break;
            }
        }
        return p;
    }

    public static BigInteger generateN(int bits, Random rand) {
        BigInteger p = generatePrime(bits/2, rand);
        BigInteger q = generatePrime(bits/2, rand);

        while(p.equals(q)) {
            q = generatePrime(bits/2, rand);
        }

        return p.multiply(q);
    }

    public BlumBlumShub(int bits) {
        this(bits, new Random());
    }

    public BlumBlumShub(int bits, Random rand) {
        this(generateN(bits, rand));
    }

    public BlumBlumShub(BigInteger n) {
        this(n, SecureRandom.getSeed((n.bitLength()/8)));
    }

    public BlumBlumShub(BigInteger n, byte[] seed) {
        this.n = n;
        setSeed(seed);

    }

    public void setSeed(byte[] seedBytes) {
        BigInteger seed = new BigInteger(1, seedBytes);
        state = seed.mod(n);
    }

    @Override
    public int next(int numBits) {
        int result = 0;
        for (int i = numBits; i != 0; i--) {
            state = state.modPow(two, n);
            result = (result << 1) | (state.testBit(0) == true ? 1 : 0);
        }
        return result;
    }

    public static void main(String[] args) {
        SecureRandom r = new SecureRandom();
        r.nextInt();

        int bitsize = 512;
        BigInteger nval = BlumBlumShub.generateN(bitsize, r);

        byte[] seed = new byte[bitsize/8];
        r.nextBytes(seed);

        BlumBlumShub bbs = new BlumBlumShub(nval, seed);

        for (int i= 0; i < 100; i++) {
            System.out.println(bbs.next(12));
        }
    }
}
