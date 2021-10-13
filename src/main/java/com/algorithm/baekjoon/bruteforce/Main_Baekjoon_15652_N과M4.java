package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N과 M 4
 * 난이도 실버3
 * 시간제한 1초, 메모리제한 512MB
 * 완전탐색, 중복허용, 순서있음.
 */

public class Main_Baekjoon_15652_N과M4 {

    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    private static int[] NUMBERS;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            NUMBERS = new int[M+1];
            recursive(1);

            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursive(int index) {

        if (index == M+1) {
            for (int i = 1; i < M+1; i++){
                sb.append(NUMBERS[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (i < NUMBERS[index-1]) continue;
            NUMBERS[index] = i;
            recursive(index + 1);
            NUMBERS[index] = 0;
        }
    }
}



