package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_10942_팰린드롬 {

    private static int N;
    private static int M;
    private static int[] numbers;

    private static boolean[][] isPalindromes;

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(bufferedReader.readLine());
            numbers = new int[N+1];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = 1;
            while (stringTokenizer.hasMoreTokens()) {
                numbers[i++] = Integer.parseInt(stringTokenizer.nextToken());
            }

            M = Integer.parseInt(bufferedReader.readLine());

            isPalindromes = new boolean[N+1][N+2];

            for (i = 1; i <= N; i++) {
                isPalindromes[i][i] = true;
            }

            for (i = 1; i < N; i++) {
                if (numbers[i] == numbers[i+1]) {
                    isPalindromes[i][i+1] = true;
                }
            }

            for (i = 2; i < N; i++) {
                for (int j = 1; j <= N - i; j++) {
                    System.out.println(String.format("(j, j+i) = (%d, %d)",j, (j+i)));
                    if (numbers[j] == numbers[j+i] && isPalindromes[j+1][j+i-1]) {
                        isPalindromes[j][j+i] = true;
                    }
                }
            }

            for (i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());

                System.out.println(String.format("(%d, %d) = %s", s, e, isPalindromes[s][e]));

                for (int j = s; j <= e; j++) {
                    System.out.print(numbers[j]);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
