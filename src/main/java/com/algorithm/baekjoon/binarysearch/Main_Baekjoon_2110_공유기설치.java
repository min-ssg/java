package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 제목: 공유기 설치
 * 난이도: 실버1
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 분류: Parametric Search
 */
public class Main_Baekjoon_2110_공유기설치 {

    static FastReader scan = new FastReader();

    static int N, C; // 2 <= C <= N <= 200_000
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();
        C = scan.nextInt();

        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    private static void solution() {

        // determination을 빠르게 하기 위해 정렬부터 한다.
        Arrays.sort(A);

        int left = 0;
        int right = 1_000_000_000;
        int answer = 0;
        while (left <= right) {

            int middle = (left + right) / 2;
            boolean isTrue = determine(middle);
            System.out.println("isTrue = " + isTrue);
            if (isTrue) {
                answer = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean determine(int distance) {
        // 제일 왼쪽 집부터 가능한 많이 설치하자.
        int count = 1;
        int next = A[0] + distance;
        System.out.println("distance = " + distance);
        for (int i = 1; i < N; i++) {
            if (next <= A[i]) { // A[i]에 설치가 가능한가?
                next = A[i] + distance;
                count++;
            }
        }

        return count >= C;
    }

    private static class FastReader {
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
