package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_2206_벽부수고이동하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            Solution2206 solution2206 = new Solution2206();
            int answer = solution2206.solve(N,M,map);
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
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution2206 {

    StringBuilder sb = new StringBuilder();
    private int N,M;
    private int[][] map;
    private int[][][] distance;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.map = map;
        this.distance = new int[N][M][2];

        int answer = bfs();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append('(').append(distance[i][j][0]).append(',').append(distance[i][j][1]).append(')').append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
        return answer;
    }

    private int bfs() {

        Queue<Room> queue = new LinkedList<>();
        distance[0][0][0] = 1;
        distance[0][0][1] = 1;
        queue.add(new Room(0,0, false));

        while (!queue.isEmpty()) {
            Room room = queue.poll();

            int x = room.x;
            int y = room.y;
            boolean crashed = room.crashed;
            System.out.println("current = (" + x + "," + y + ")");

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                Room next = new Room(x + dx[i], y + dy[i], crashed);

                if (crashed) {
                    if (map[x + dx[i]][y + dy[i]] == 1 ) continue;
                    if (distance[x + dx[i]][y + dy[i]][1] != 0  || distance[x + dx[i]][y + dy[i]][0] != 0) continue;
                    distance[x + dx[i]][y + dy[i]][1] = distance[x][y][1] + 1;
                } else {
                    if (map[x + dx[i]][y + dy[i]] == 1) {
                        if (distance[x + dx[i]][y + dy[i]][1] != 0) continue;
                        next.crashed = true;
                        distance[x + dx[i]][y + dy[i]][1] = distance[x][y][0] + 1;
                    } else {
                        if (distance[x + dx[i]][y + dy[i]][0] != 0) continue;
                        distance[x + dx[i]][y + dy[i]][0] = distance[x][y][0] + 1;
                    }
                }
                System.out.println("(" + (x + dx[i]) + "," + (y + dy[i]) + ")");
                queue.add(next);
            }
        }

        if (distance[N-1][M-1][0] != 0 && distance[N-1][M-1][1] != 0) {
            return Math.min(distance[N-1][M-1][0], distance[N-1][M-1][1]);
        } else if (distance[N-1][M-1][0] != 0) {
            return distance[N-1][M-1][0];
        } else if (distance[N-1][M-1][1] != 0){
            return distance[N-1][M-1][1];
        }

        return -1;
    }

    class Room {
        int x,y;
        boolean crashed;

        public Room (int x, int y,  boolean crashed) {
            this.x = x;
            this.y = y;
            this.crashed = crashed;
        }
    }
}
