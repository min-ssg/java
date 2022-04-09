package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 사탕게임
 * 3085
 * 난이도 : 실버 3
 * 유형 : 브루트포스
 * 봄보니 게임의 구현이 즉, 사탕 개수를 세는 걸 구현하는게 조금 어려움.
 */
public class Main_Baekjoon_3085_사탕게임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            char[][] map = new char[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            Solution3085 solution3085 = new Solution3085();
            int answer = solution3085.solve(N, map);
            System.out.println(answer);
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

class Solution3085 {

    private int N;
    char[][] map;

    public int solve(int n, char[][] map) {
        this.N = n;
        this.map = map;
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                // 행
                swapX(i,j);
                result = Math.max(result, numberOfCandy());
                swapX(i,j);
                // 열
                swapY(j,i);
                result = Math.max(result, numberOfCandy());
                swapY(j,i);
            }
        }

        return result;
    }

    private int numberOfCandy() {

        int result = 1;
        // 행
        for (int i = 0; i < N; i++) {
            int temp = 1;
            for (int j = 1; j < N; j++) {
                if (map[i][j-1] == map[i][j]) {
                    temp++;
                } else {
                    result = Math.max(result, temp);
                    temp = 1;
                }
            }
            result = Math.max(result, temp);
        }

        // 열
        for (int i = 0; i < N; i++) {
            int temp = 1;
            for (int j = 0; j < N - 1; j++) {
                if (map[j+1][i] == map[j][i]) {
                    temp++;
                } else {
                    result = Math.max(result, temp);
                    temp = 1;
                }
            }
            result = Math.max(result, temp);
        }

        return result;
    }

    private void swapX(int i, int j) {
        char temp = map[i][j];
        map[i][j] = map[i][j+1];
        map[i][j+1] = temp;
    }

    private void swapY(int i, int j) {
        char temp = map[i][j];
        map[i][j] = map[i+1][j];
        map[i+1][j] = temp;
    }
}
