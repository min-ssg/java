package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 백준 : N과M(3)
 * brute force
 * 순서 있음
 */
public class Main_Baekjoon_15651_N과M3_refactor {

    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] selected;

    // 코드를 모듈화해서 작성하면 코드 관리 및 수정이 편하다.
    public static void main(String[] args) {
        input(); // 입력코드

        // 1번째 원소부터 M번째 원소를 조건에 맞는 모든 방법을 찾아줘
        recurrentFunction(1);
        System.out.println(sb.toString());
    }

    private static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    // Recurrence Function(재귀함수)
    // 만약 M 개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것!
    // 아직 M 개를 고르지 못했다. => k번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
    static void recurrentFunction(int k) {
        if (k == M + 1) { // M개를 전부 고름
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 1~N까지 k번 원소로 한 번씩 정하고,
        // 매번 k+1번부터 M번 원소로 재귀호출 해주기
        for (int candidate = 1; candidate <= N; candidate++) {
            selected[k] = candidate;
            // k+1 번 ~ M번을 모두 탐색하는 일을 해야하는 상황
            recurrentFunction(k+1);
            selected[k] = 0;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while ( st == null || !st.hasMoreElements()) {
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
