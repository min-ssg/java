package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 수 고르기
 * 난이도: 골드5
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 분류: 정렬, 두 포인터
 */
public class Main_Baekjoon_2230_수고르기 {

    static FastReader scan = new FastReader();
    static int N,M;
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {
        Arrays.sort(A);
        int min = Integer.MAX_VALUE;
        for (int L = 0, R = 0; L < N; L++) {

            while (R < N) {
                int diff = A[R] - A[L];
                if (diff >= M) {
                    break;
                }
                R++;
            }
            if (R == N) continue;

            min = Math.min(min, A[R] - A[L]);
        }

        System.out.println(min);
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
