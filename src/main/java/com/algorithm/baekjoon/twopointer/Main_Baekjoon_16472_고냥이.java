package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_16472_고냥이 {

    static FastReader scan = new FastReader();

    static int N, KIND; // 1<= N <= 26
    static String str;
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        KIND = 0;
        str = scan.nextLine();
        A = new int[26];
    }

    private static void add(char c) {
        A[c - 'a']++;
        if (A[c - 'a'] == 1) {
            KIND++;
        }
    }


    private static void remove(char c) {
        A[c - 'a']--;
        if (A[c - 'a'] == 0) {
            KIND--;
        }
    }

    private static void solution() {

        int max = 0;
        int length = str.length();
        add(str.charAt(0));
        for (int L = 0, R = 1; R < length; R++) {
            add(str.charAt(R));
            while (KIND > N) {
                remove(str.charAt(L++));
            }
            System.out.println("L = " + L + ", R = " + R + ", KIND = " + KIND);
            max = Math.max(max, R-L);
        }


        System.out.println(max);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
