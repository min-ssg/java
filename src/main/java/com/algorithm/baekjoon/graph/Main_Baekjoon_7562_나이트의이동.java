package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_7562_나이트의이동 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();

            int T = scan.nextInt();

            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                int[][] MAP = new int[N][N];
                int fromX = scan.nextInt();
                int fromY = scan.nextInt();
                int toX = scan.nextInt();
                int toY = scan.nextInt();

                Solution7562 solution7562 = new Solution7562();
                int answer = solution7562.solve(N,MAP,fromX,fromY,toX,toY);
                sb.append(answer).append('\n');
            }
            System.out.println(sb.toString());
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

class Solution7562 {

    private int N, fromX, fromY, toX, toY;
    private int[][] MAP;
    private boolean[][] visited;

    private int[] dx = {-1,-2,-2,-1,1,2,2,1};
    private int[] dy = {-2,-1,1,2,2,1,-1,-2};

    public int solve(int n, int[][] map, int fromX, int fromY, int toX, int toY) {
        this.N = n;
        this.MAP = map;

        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;

        this.visited = new boolean[N][N];

        return bfs(new Room(fromX,fromY,0));
    }

    private int bfs(Room start) {

        Queue<Room> queue = new LinkedList<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Room room = queue.poll();
            int x = room.x;
            int y = room.y;
            int order = room.order;

            for (int i = 0; i < 8; i++) { // 나이트가 이동할 수 있는 곳.

                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= N) continue;

                if (visited[x + dx[i]][y + dy[i]]) continue;

                if (x + dx[i] == toX && y + dy[i] == toY) {
                    return  order + 1;
                }

                queue.add(new Room(x + dx[i], y + dy[i], order + 1));
                visited[x + dx[i]][y + dy[i]] = true;
            }
        }

        return 0;
    }

    private class Room {
        int x, y, order;
        public Room(int x, int y, int order) {
            this.x = x;
            this.y = y;
            this.order = order;
        }
    }
}