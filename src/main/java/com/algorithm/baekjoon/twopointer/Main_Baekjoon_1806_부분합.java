package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 제목 : 부분합
 * 난이도: 골드4
 * 시간제한: 0.5
 * 분류: two pointer
 */
public class Main_Baekjoon_1806_부분합 {

    static FastReader scan = new FastReader();
    static int N, S; // 10<=N<100_000, 0<=S<=100_000_000
    static int[] A;
    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        S = scan.nextInt();
        A = new int[N+1];

        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {

        int answer = N+1, sum = 0, R = 0; // answer = N + 1로 한 이유는 처음부터 모든 합의 길이이기 때문이다.

        for (int L = 1; L <= N; L++) {
            sum -= A[L-1];

            while (R + 1 <= N && sum < S) {
                R++;
                sum += A[R];
            }

            if (sum >= S) {
                answer = Math.min(answer, R - L + 1);
            }
        }

        if (answer == N+1) {
            answer = 0;
        }

        System.out.println(answer);
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
