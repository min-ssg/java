package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main_Baekjoon_15651_N과M3 {

    private static int N;
    private static int M;
    private static int[] NUMBERS;
    private static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            String[] inputs = bf.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);
            NUMBERS = new int[M+1];
            recursive(1);
            System.out.println(SB.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursive(int i) {
        if (i == M + 1) {
            IntStream.rangeClosed(1, M)
                    .forEach(index -> {
                        SB.append(NUMBERS[index]).append(' ');
                    });
            SB.append('\n');
            return;
        }

        IntStream.rangeClosed(1, N)
                .forEach(index -> {
                    NUMBERS[i] = index;
                    recursive(i+1);
                    NUMBERS[i] = 0;
                });
    }
}
