package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_18404_현명한나이트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[] knight = new int[]{scan.nextInt(), scan.nextInt()};

            Queue<Integer> targets = new LinkedList<>();

            for (int i = 1; i <= M; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                targets.add(x);
                targets.add(y);
            }

            Solution18404 solution18404 = new Solution18404();
            solution18404.solve(N,M,knight,targets);

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

class Solution18404 {

    StringBuilder sb = new StringBuilder();
    private int N,M;
    private Room KNIGHT;
    private int[][] DIST;
    private boolean[][] visited;

    private int[] dx = {-1,-2,-2,-1,1,2,2,1};
    private int[] dy = {-2,-1,1,2,2,1,-1,-2};

    public void solve(int n, int m, int[] knight, Queue<Integer> targets) {
        this.N = n;
        this.M = m;
        this.DIST = new int[N+1][N+1];
        this.visited = new boolean[N+1][N+1];
        this.KNIGHT = new Room(knight[0], knight[1]);
        bfs(KNIGHT);

        while (!targets.isEmpty()) {
            int x = targets.poll();
            int y = targets.poll();

            sb.append(DIST[x][y]).append(' ');
        }

        System.out.println(sb.toString());
    }

    private void bfs(Room knight) {
        Queue<Room> queue = new LinkedList<>();

        queue.add(knight);
        visited[knight.x][knight.y] = true;

        while (!queue.isEmpty()) {
            Room room = queue.poll();
            int x = room.x;
            int y = room.y;
            int order = DIST[x][y];

            for (int i = 0; i < 8; i++) {
                if (x + dx[i] < 1 || x + dx[i] > N
                ||  y + dy[i] < 1 || y + dy[i] > N) continue;

                if (visited[x + dx[i]][y + dy[i]]) continue;

                queue.add(new Room(x + dx[i], y + dy[i]));
                visited[x + dx[i]][y + dy[i]] = true;
                DIST[x + dx[i]][y + dy[i]] = order + 1;
            }
        }
    }

    private class Room {
        int x,y;
        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
