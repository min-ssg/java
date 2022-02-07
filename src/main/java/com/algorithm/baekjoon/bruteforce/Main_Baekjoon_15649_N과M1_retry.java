package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * System.out.print로 결과를 출력하면 시간초과가 발생할 수 있음.
 */
public class Main_Baekjoon_15649_N과M1_retry {

    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] selected, used;

    public static void main(String[] args) {
        input();
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
        used = new int[N+1];
    }

    private static void recurrentFunction(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
//            Arrays.stream(selected,1,M+1).forEach(
//                    select -> System.out.print(select + " ")
//            );
//            System.out.println();
            return;
        }

        for (int candidate = 1; candidate <= N; candidate++) {

//            if (isUsed(candidate, k)) continue;
            if (used[candidate] == 1) continue;

            selected[k] = candidate; used[candidate] = 1;
            recurrentFunction(k+1);
            selected[k] = 0; used[candidate] = 0; // k번째 숫자에 사용을 안함.
        }
    }

    private static boolean isUsed(int candidate, int k) {
        return Arrays.stream(selected,1,k).anyMatch(select -> select == candidate);
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
