package com.ksv.minglex.service;

public class LinearCongruentialGenerator implements RandomNumberGenerator {

    private int multiplier;
    private int increment;
    private int limit;

    private int state;



    public LinearCongruentialGenerator() {
        this.multiplier = 4567;
        this.increment = 23;
        setSeed();

    }

    public void setSeed() {
        this.state = 1;
    }

    @Override
    public int next(int numBits) {
        limit = (int) Math.pow(2, numBits) - 1;
        state = (state*multiplier + increment) % limit;
        return state;
    }

    public static void main(String[] args) {
        LinearCongruentialGenerator lcg = new LinearCongruentialGenerator();
        for (int i = 0; i < 100; i++)
            System.out.println(lcg.next(8));
    }
}
