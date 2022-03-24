package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2309_일곱난쟁 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int[] A = new int[9];
            
            for (int i = 0; i < 9; i++) {
                A[i] = scan.nextInt();
            }

            Solution2309 solution2309 = new Solution2309();
            solution2309.solve(A);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution2309 {

    StringBuilder sb = new StringBuilder();
    private int[] A;
    private boolean[] used = new boolean[101];
    private int[] selected = new int[7];
    private boolean isCheck = false;

    public void solve(int[] a) {
        this.A = a.clone();

        recurrentFunction(0);
        System.out.println(sb.toString());
    }

    private void recurrentFunction(int index) {

        if (index == 7) {

            int sum = summation();

            if (sum != 100) return;

            Arrays.sort(selected);

            for (int i = 0; i < 7; i++) {
                sb.append(selected[i]).append('\n');
            }

            isCheck = true;

            return;
        }

        for (int i = 0; i < 9; i++) {
            if (used[A[i]]) continue;

            used[A[i]] = true;
            selected[index] = A[i];
            recurrentFunction(index + 1);

            if (isCheck) return;

            used[A[i]] = false;
            selected[index] = 0;
        }
    }

    private int summation() {
        return Arrays.stream(selected).sum();
    }
}
