package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main_Baekjoon_14889_스타트와링크 {

    static int N; // N은  무조건 짝수
    static int[] team, selected; // 1 이면 started, 0이면 linked
    static int min = Integer.MAX_VALUE;
    static int[][] S;

    public static void main(String[] args) {
        input();
        recurrentFunction(2);
        System.out.println(min);
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            N = scan.nextInt();
            team = new int[N + 1];
            team[1] = 1;
            selected = new int[N/2 + 1];
            selected[1] = 1;
            S = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    S[i][j] = scan.nextInt();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k) {
        if (k == N/2 + 1) {

            int startedAbility = 0;
            int linkedAbility = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;

                    if (team[i] == 1 && team[j] == 1) {
                        startedAbility += S[i][j];
                    }

                    if (team[i] == 0 && team[j] == 0) {
                        linkedAbility += S[i][j];
                    }
                }
            }

            min = Math.min(min, Math.abs(startedAbility - linkedAbility));

            return;
        }

        for (int i = selected[k-1] + 1; i <= N; i++) {
            selected[k] = i;
            team[i] = 1;
            recurrentFunction(k+1);
            selected[k] = 0;
            team[i] = 0;
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        StringTokenizer st;

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
