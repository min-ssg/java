package com.algorithm.baekjoon.sorting;

import java.io.*;

public class Main_Baekjoon_10989_수정렬하기3 {

    public static int N; // 1 <= N <= 10_000_000

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            N = Integer.parseInt(bf.readLine());

            int[] array = new int[10_001];

            for (int i = 0; i < N; i++) {
                int index = Integer.parseInt(bf.readLine());
                array[index]++;
            }

            for (int i = 1; i < 10_001; i++) {
                while (array[i]-- != 0) {
                    bw.write(i + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
