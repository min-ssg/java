package com.algorithm.programmers.highscorekit;

import java.util.HashSet;
import java.util.Set;

public class Main_Programmers_N으로표현 {

    private int N;
    Set<Integer>[] DP = new HashSet[9];

    public int solution(int N, int number) {
        this.N = N;

        if (N == number) {
            return 1;
        }

        for (int i = 0; i <= 8; i++) {
            DP[i] = new HashSet<>();
        }

        DP[1].add(N);

        //  1 <= N <= 9
        // 1 <= number <= 32000
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j <= i; j++) {

                for (int x : DP[j]) {
                    for (int y : DP[i-j]) {
                        DP[i].add(x + y);
                        DP[i].add(x - y);
                        DP[i].add(x * y);

                        if (x != 0 && y != 0) {
                            DP[i].add( x / y);
                        }
                    }
                }
                DP[i].add(NtoNumber(i));
            }

            if (DP[i].stream().anyMatch(value -> value == number)) {
                return i;
            }
        }

        return -1;
    }

    private Integer NtoNumber(int n) {
        return Integer.parseInt(String.valueOf(N).repeat(n));
    }
}
