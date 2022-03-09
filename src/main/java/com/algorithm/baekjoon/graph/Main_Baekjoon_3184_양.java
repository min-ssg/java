package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_3184_양 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int R = scan.nextInt();
            int C = scan.nextInt();
            char[][] MAP = new char[R][C];

            for (int i = 0; i < R; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < str.length(); j++) {
                    MAP[i][j] = str.charAt(j);
                }
            }

            Solution3184 solution3184 = new Solution3184();
            solution3184.solve(R,C,MAP);
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

        private String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution3184 {

    private int R, C;
    private char[][] MAP;
    private boolean[][] visited;

    // 상하좌우
    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    private int NUMBER_OF_SHEEP, NUMBER_OF_WOLVES;

    public void solve(int r, int c, char[][] map) {
        this.R = r;
        this.C = c;
        this.MAP = map;
        this.visited = new boolean[r][c];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] == '#' || visited[i][j]) continue;
                int[] sheepAndWolf = dfs(i,j);

                if (sheepAndWolf[0] <= sheepAndWolf[1]) {
                    NUMBER_OF_WOLVES += sheepAndWolf[1];
                } else {
                    NUMBER_OF_SHEEP += sheepAndWolf[0];
                }
            }
        }
        System.out.println(NUMBER_OF_SHEEP + " " + NUMBER_OF_WOLVES);
    }

    private int[] dfs(int x, int y) {

        visited[x][y] = true;

        int numberOfSheep = 0;
        int numberOfWolves = 0;

        if (MAP[x][y] == 'o') {
            numberOfSheep++;
        } else if (MAP[x][y] == 'v') {
            numberOfWolves++;
        }

        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= R
            ||  y + dy[i] < 0 || y + dy[i] >= C)  continue;

            if (MAP[x + dx[i]][y + dy[i]] == '#' || visited[x + dx[i]][y + dy[i]]) continue;

            int[] sheepAndWolf = dfs(x + dx[i], y + dy[i]);
            numberOfSheep += sheepAndWolf[0];
            numberOfWolves += sheepAndWolf[1];
        }

        return new int[]{numberOfSheep, numberOfWolves};
    }
}