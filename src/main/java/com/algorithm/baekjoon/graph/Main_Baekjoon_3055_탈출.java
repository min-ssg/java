package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_3055_탈출 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int R = scan.nextInt();
            int C = scan.nextInt();
            StringBuilder sb = new StringBuilder();
            char[][] map = new char[R][C];

            for (int i = 0; i < R; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }

            System.out.println(sb.toString());

            Solution3055 solution3055 = new Solution3055();
            String answer = solution3055.solve(R,C,map);
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

class Solution3055 {

    StringBuilder sb = new StringBuilder();
    private int R,C;
    private Room[][] rooms;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public String solve(int r, int c, char[][] map) {
        this.R = r; // 1 <= R <= 50
        this.C = c; // 1 <= C <= 50
        this.rooms = new Room[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                rooms[i][j] = new Room(i,j,map[i][j],-1);
            }
        }

        return bfs();
    }

    private String bfs() {

        Queue<Room> hedgehogs = new LinkedList<>();
        Queue<Room> waters = new LinkedList<>();
        Queue<Room> Q_rooms = new LinkedList<>();

        Room target = null;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                switch (rooms[i][j].value) {
                    case 'D':
                        target = rooms[i][j];
                        break;
                    case 'S': // 고슴도치
                        rooms[i][j].distance = 0;
                        hedgehogs.add(rooms[i][j]);
                        break;
                    case '*':
                        rooms[i][j].distance = 0;
                        waters.add(rooms[i][j]);
                        break;
                }
            }
        }

        while (!waters.isEmpty()) {
            Q_rooms.add(waters.poll());
        }

        while (!hedgehogs.isEmpty()) {
            Q_rooms.add(hedgehogs.poll());
        }

        while (!Q_rooms.isEmpty()) {
            Room room = Q_rooms.poll();

            int x = room.x;
            int y = room.y;
            int value = room.value;
            int distance = room.distance;

            for (int i = 0; i < 4; i++) {

                if (x + dx[i] < 0 || x + dx[i] >= R
                ||  y + dy[i] < 0 || y + dy[i] >= C) continue;

                Room nextRoom = rooms[x + dx[i]][y + dy[i]];

                if (value == '*') {
                    if (nextRoom.value != '.' || nextRoom.distance != -1) continue;

                    nextRoom.value = '*';
                    nextRoom.distance = distance + 1;

                } else if (value == 'S') {
                    if (nextRoom.value == '*' || nextRoom.value == 'X') continue;

                    if (nextRoom.distance != -1) continue;

                    nextRoom.value = 'S';
                    nextRoom.distance = distance + 1;
                }

                Q_rooms.add(nextRoom);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(rooms[i][j].value).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
        return target.distance == -1 ? "KAKTUS" : String.valueOf(target.distance);
    }

    private void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(rooms[i][j].value).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private class Room {
        int x,y,distance;
        char value;

        public Room(int x, int y, char value, int distance) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ") = " + distance;
        }
    }
}
