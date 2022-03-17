package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_2178_미로탐색 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 2 <= N <= 100
            int M = scan.nextInt(); // 2 <= M <= 100
            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            Solution2178 solution2178 = new Solution2178();
            int answer = solution2178.solve(N,M,map);
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

        public String nextLine() throws IOException {
            return br.readLine();
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

class Solution2178 {

    private int N,M;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    Block[][] blocks;

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;

        blocks = new Block[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                blocks[i][j] = new Block(i,j,-1, map[i][j]);
            }
        }

        Block arrived = bfs();
        return arrived.getDistance();
    }

    private Block bfs() {

        Queue<Block> queue = new LinkedList<>();

        blocks[0][0].setVisited();
        blocks[0][0].setDistance(1);
        queue.add(blocks[0][0]); // 1,1에서 출발함.
        while (!queue.isEmpty()) {
            Block block = queue.poll();
            int x = block.x;
            int y = block.y;
            int distance = block.getDistance();
            for (int i = 0; i < 4; i++) { // 인접 블럭 찾기

                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                if (blocks[x + dx[i]][y + dy[i]].isVisited()
                ||  blocks[x + dx[i]][y + dy[i]].road == 0) continue;

                Block nextBlock = blocks[x+dx[i]][y+dy[i]];
                nextBlock.setDistance(distance + 1);
                nextBlock.setVisited();

                queue.add(nextBlock);
            }
        }

        return blocks[N-1][M-1];
    }

    private class Block {

        private int x,y, road;
        private boolean visited;
        private int distance;

        public Block(int x, int y, int distance, int road) {
            this.x = x;
            this.y = y;
            this.road = road;
            this.distance = distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public void setVisited() {
            this.visited = true;
        }

        public boolean isVisited() {
            return visited;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ") = " + distance;
        }
    }
}
