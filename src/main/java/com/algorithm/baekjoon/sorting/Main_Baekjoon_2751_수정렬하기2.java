package com.algorithm.baekjoon.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_Baekjoon_2751_수정렬하기2 {

    public static int N;

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            N = Integer.parseInt(bf.readLine());

            boolean[] isNumber = new boolean[2_000_001];

            for (int i = 0; i < N; i++) {
                int index = Integer.parseInt(bf.readLine()) + 1_000_000;

                isNumber[index] = true;
            }

            for (int i = 0; i < 2_000_001; i++) {
                if (!isNumber[i]) {
                    continue;
                }

                System.out.println(i - 1_000_000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
