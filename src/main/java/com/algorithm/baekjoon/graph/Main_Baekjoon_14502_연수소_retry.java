package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_14502_연수소_retry {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // 3 <= N <= 8
            int M = scan.nextInt(); // 3 <= M <= 8

            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution14502Retry solution = new Solution14502Retry();
            int answer = solution.solve(N,M,map);
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

class Solution14502Retry {

    private int N,M, max = Integer.MIN_VALUE;
    int[][] map;
    ArrayList<Room> emptyRooms, virusRooms;
    Room[] selected = new Room[3];

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public int solve(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.map = map;

        emptyRooms = new ArrayList<>();
        virusRooms = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    emptyRooms.add(new Room(i,j));
                } else if (map[i][j] == 2) {
                    virusRooms.add(new Room(i,j));
                }
            }
        }

        recurrentFunction(0,0);

        return max;
    }

    private void recurrentFunction(int k, int index) {

        if (k == 3) {
            max = Math.max(max, bfs());
            return;
        }

        for (int i = index; i < emptyRooms.size(); i++) {
            Room emptyRoom =  emptyRooms.get(i);
            int x = emptyRoom.x;
            int y = emptyRoom.y;
            map[x][y] = 1;
            selected[k] = emptyRoom;
            recurrentFunction(k + 1, i + 1); // selected 다음으로, index에 +1 을 하면 recurrent가 됨.
            map[x][y] = 0;
            selected[k] = null;
        }
    }

    private int bfs() {

        int count = 0;
        Queue<Room> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (Room virusRoom : virusRooms) {
            queue.add(virusRoom);
            visited[virusRoom.x][virusRoom.y] = true;
        }

        while (!queue.isEmpty()) {
            Room room = queue.poll();
            int x = room.x;
            int y = room.y;


            // 상하좌우
            // 안전지역 계산.
            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                if (visited[x + dx[i]][y + dy[i]] || map[x + dx[i]][y + dy[i]] != 0) continue;

                visited[x + dx[i]][y + dy[i]] = true;
                queue.add(new Room(x + dx[i], y + dy[i]));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    count += 1;
                }
            }
        }

        return count;
    }

    private class Room {
        int x;
        int y;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }
}
