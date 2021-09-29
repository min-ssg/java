package com.algorithm.programmers.kakao2022blindtest;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Programming2 {

    public int solution() {

        boolean[] isPrime = new boolean[1_000_000_001];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for (int i = 2; i <= 100_000; i++) {
            for (int j = i+i; j <= 1_000_000_000; j += i) {
                if (isPrime[j]) {
                    isPrime[j] = false;
                }
            }
        }

        int n = 1;
        int k = 3;

        Predicate<String> isPrimeTest = string -> {
            if (Long.parseLong(string) > 1_000_000) return true;
            int i = Integer.parseInt(string);
            return isPrime[i];
        };

        for (n = 1; n <= 1_000_000; n++) {
            for (k = 3; k <= 10; k++) {

                try {
                    String string = stringToNSystem(n, k);
                    //                System.out.println(string);
                    //                System.out.println(Arrays.toString(string.split("0+")));
                    int answer = Arrays.stream(string.split("0+"))
                            .filter(isPrimeTest)
                            .toArray().length;
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("n = " + n);
                    System.out.println("k = " + k);
                }
            }
        }

        return 0;
    }

    public String stringToNSystem(int n, int k) {
        int temp = n;
        StringBuilder sb = new StringBuilder();
        while (k <= temp) {
            sb.append(temp % k);
            temp /= k;
        }
        if (temp != 0) {
            sb.append(temp);
        }
        sb.reverse();
        return sb.toString();
    }
}
