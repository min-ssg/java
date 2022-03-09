package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_14502_연구소 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 행
            int M = scan.nextInt(); // 열
            int[][] MAP = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            Solution14502 solution14502 = new Solution14502();
            int answer = solution14502.solve(N, M, MAP);
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

class Solution14502 {

    private int N,M, MAX_SAFE_BOX_COUNTS;
    private int[][] MAP;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    List<Box> safeBoxes = new ArrayList<>();
    List<Box> virusBoxes = new ArrayList<>();

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map.clone();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0) {
                    safeBoxes.add(new Box(i,j));
                } else if (MAP[i][j] == 2) {
                    virusBoxes.add(new Box(i,j));
                }
            }
        }
        recurrentFunction(0,0);
        return MAX_SAFE_BOX_COUNTS;
    }

    private void recurrentFunction(int index, int selectedCounts) {
        if (selectedCounts == 3) { // 벽 3개
            // TODO bfs();
            MAX_SAFE_BOX_COUNTS = Math.max(MAX_SAFE_BOX_COUNTS, bfs());
            return;
        }

        if (index >= safeBoxes.size()) return;

        Box box = safeBoxes.get(index);
        // 세우거나 세우지 않거나
        MAP[box.x][box.y] = 1; // 벽을 세우자.
        recurrentFunction(index + 1, selectedCounts + 1);
        MAP[box.x][box.y] = 0; // 벽을 세우지 않음.
        recurrentFunction(index + 1, selectedCounts);
    }

    private int bfs() {
        Queue<Box> virusQueue = new LinkedList<>();
        boolean[][] infected = new boolean[N][M];

        for (Box virusBox : virusBoxes) {
            virusQueue.add(virusBox);
            infected[virusBox.x][virusBox.y] = true;
        }

        while (!virusQueue.isEmpty()) {
            Box virusBox = virusQueue.poll();

            int x = virusBox.x;
            int y = virusBox.y;
            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                if (MAP[x + dx[i]] [y + dy[i]] != 0) continue;

                if (infected[x+dx[i]][y+dy[i]]) continue;

                virusQueue.add(new Box(x + dx[i], y + dy[i]));
                infected[x + dx[i]][y + dy[i]] = true;
            }
        }

        return getSafeBoxCounts(infected);
    }

    private int getSafeBoxCounts(boolean[][] infected) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0 && !infected[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private class Box {
        int x, y;

        public Box(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}