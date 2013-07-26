package com.infibot.client.api.util;

public class Task {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(int min, int max) {
		sleep(Random.nextInt(min, max));
	}

}