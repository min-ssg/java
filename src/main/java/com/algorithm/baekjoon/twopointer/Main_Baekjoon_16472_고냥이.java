package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 고냥이
 * 번호: 16472번
 * 난이도: 골드3
 * 시간제한: 1초
 */
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
        A = new int[26]; // 카운트배열.
    }

    private static void add(char c) {
        A[c - 'a']++;
        if (A[c - 'a'] == 1) { // 1이라면, 새로 추가된 것이기 때문에 KIND추가
            KIND++;
        }
    }


    private static void remove(char c) {
        A[c - 'a']--;
        if (A[c - 'a'] == 0) { // 0이라면, 없어진 것이기 때문에 KIND 감소
            KIND--;
        }
    }

    private static void solution() {

        int max = 0;
        int length = str.length();
        add(str.charAt(0));
        for (int L = 0, R = 1; R < length; R++) { // 기존과 반대로 R을 증가시킴.
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
