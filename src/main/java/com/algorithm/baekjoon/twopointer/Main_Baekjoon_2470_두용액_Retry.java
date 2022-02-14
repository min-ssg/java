package com.algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_2470_두용액_Retry {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N+1];

            for (int i = 1; i <= N; i++) {
                A[i] = scan.nextInt();
            }

            Arrays.sort(A,1,N+1);

            Solution2470 solution2470 = new Solution2470();

            int[] answer = solution2470.solve(N, A);
            System.out.println(answer[0] + " " + answer[1]);

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

class Solution2470 {

    private int N;
    private int[] A;

    public int[] solve(int N, int[] A) {
        this.N = N;
        this.A = A.clone();

        int[] ret = new int[2];
        int min = Integer.MAX_VALUE;
        int L = 1;
        int R = N;

        while (L < R) {
            int sum = A[L] + A[R];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                ret[0] = A[L];
                ret[1] = A[R];
            }
            if (sum < 0) {
                L++;
            } else {
                R--;
            }
        }

        return ret;
    }
}
