package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부등호
 * 실버2
 */
public class Main_Baekjoon_2529_부등호 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);

            int k = scan.nextInt();
            char[] sign = new char[k];

            for (int i = 0; i < k; i++) {
                sign[i] = scan.nextCharacter();
            }

            Solution2529 solution2529 = new Solution2529();

            solution2529.solve(k, sign);

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

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public char nextCharacter() throws IOException {
            return next().charAt(0);
        }
    }
}

class Solution2529 {

    private boolean[] used = new boolean[10];
    private int K;
    private char[] selected, sign;
    private long max = Long.MIN_VALUE;
    private long min = Long.MAX_VALUE;

    public void solve(int k, char[] sign) {

        this.K = k;
        this.sign = sign;
        selected = new char[k+1];

        recurrentFunction2(0);
        System.out.println(LPAD(max, k+1));
        System.out.println(LPAD(min, k+1));
    }

    private String LPAD(long number, int length) {
        String numberString = String.valueOf(number);
        if (numberString.length() == length) {
            return numberString;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - numberString.length(); i++) {
            sb.append('0');
        }
        sb.append(numberString);

        return sb.toString();
    }

    private void recurrentFunction(int k) {

        if (k == K+1) {
            if (validation()) {
                long value = getValue();
                max = Math.max(max, value);
                min = Math.min(min, value);
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (used[i]) continue;

            selected[k] = (char)(i + '0');
            used[i] = true;
            recurrentFunction(k+1);
            selected[k] = ' ';
            used[i] = false;
        }
    }

    private void recurrentFunction2(int k) {

        if (k == K+1) {
            long value = getValue();
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (used[i]) continue;

            if (validation(k, i)) {
                selected[k] = (char)(i + '0');
                used[i] = true;
                recurrentFunction2(k+1);
                selected[k] = ' ';
                used[i] = false;
            }
        }
    }

    private boolean validation() {
        return false;
    }

    private boolean validation(int k, int i) {

        if (k == 0) return true;

        if (sign[k-1] == '<' && !(selected[k-1] < i + '0')) {
            return false;
        }

        if (sign[k-1] == '>' && !(selected[k-1] > i + '0')) {
            return false;
        }

        return true;
    }

    private long getValue() {
        return Long.parseLong(String.valueOf(selected));
    }
}