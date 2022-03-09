package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_2667_단지번호붙이기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] A = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    A[i][j] = (int) (line.charAt(j) - '0');
                }
            }

            Solution2667 solution2667 = new Solution2667();
            solution2667.solve(N,A);
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

        public String nextLine() throws IOException {
            return br.readLine();
        }
    }
}

class Solution2667 {

    private StringBuilder sb = new StringBuilder();
    private int N;
    private int[][] A;
    private boolean[][] visited;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public void solve(int n, int[][] a) {
        this.N = n;
        this.A = a.clone();
        this.visited = new boolean[N][N];

        List<Integer> countList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 0 || visited[i][j]) continue;
                countList.add(dfs(i,j));
            }
        }

        sb.append(countList.size()).append('\n');
        Collections.sort(countList);
        for (int count : countList) {
            sb.append(count).append('\n');
        }

        System.out.println(sb.toString());
    }

    private int dfs(int x, int y) {
        int count = 1;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= N) continue;

            if (visited[x+dx[i]][y+dy[i]]) continue;

            if (A[x+dx[i]][y+dy[i]] == 0) continue;

            count += dfs(x + dx[i], y + dy[i]);
        }
        return count;
    }
}