package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 두 수의 합
 * 3273
 * 난이도: 실버3
 * 시간제한 : 1초, 메모리 제한 : 128MB
 */
public class Main_Baekjoon_3273_두수의합 {

    static FastReader scan = new FastReader();
    static int N,X;
    static int[] A;

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void input() {
        N = scan.nextInt();

        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }

        X = scan.nextInt();
    }

    private static boolean findOther(int L, int R, int aj) {

        while (L <= R) {
            int middle = (L+R) / 2;

            if (A[middle] == aj) {
                return true;
            } else if (A[middle] < aj) {
                L = middle + 1;
            } else {
                R = middle - 1;
            }
        }

        return false;
    }

    private static void solution() {
        Arrays.sort(A);

        int count = 0;
        for (int i = 0; i < N; i++) {
            int aj = X - A[i];
            if (findOther(i, N-1, aj)) {
                System.out.println(A[i] + ", " + aj);
                count++;
            }
        }

        System.out.println(count);
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
