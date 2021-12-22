package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 수열
 * 난이도: 실버3
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 분류: 투 포인터
 */
public class Main_Baekjoon_2559_수열 {

    static FastReader scan = new FastReader();
    static int N,K; // 2<= N <= 100_000, 1<= K <=N
    static int[] A; // 온도를 담는 배열

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt(); // 총 기간(일)
        K = scan.nextInt(); // 연속된 일

        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {

        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += A[i];
        }

        int max = sum;

        for (int L = 0, R = K; R < N; L++, R++) {
            sum -= A[L];
            sum += A[R];

            if (max < sum) {
                max = sum;
            }
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
    }
}
