package com.tilemazes.utils;

public class Time {

    public static final long SECOND = 1000000000L;

    public static long get() {
        return  System.nanoTime();
    }

    public static float getSecond() {
        return (float) System.nanoTime()/SECOND;
    }

}
