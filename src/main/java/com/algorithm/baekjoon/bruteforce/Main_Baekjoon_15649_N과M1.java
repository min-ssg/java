package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 중복없이, 순서대로 나열 수열
 */
public class Main_Baekjoon_15649_N과M1 {

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
                .filter(index -> Arrays.stream(NUMBERS).noneMatch(number->number == index))
                .forEach(index -> {
                    NUMBERS[i] = index;
                    recursive(i+1);
                    NUMBERS[i] = 0;
                });
    }
}
