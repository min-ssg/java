package com.algorithm.bfs;

import com.algorithm.entity.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_2178_미로탐색_S1 {

    public static int N;
    public static int M;
    public static boolean VISIT[][];
    public static int MAP[][];

    public static List<Point> queue = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            VISIT = new boolean[N][M];
            MAP = new int[N][M];

            for (int i = 0; i < N; i++) {
                char[] row = bf.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    MAP[i][j] = row[j] == '1' ? 1 : 0;
                }
            }

            VISIT[0][0] = true;
            Point origin = new Point(0, 0, 1);
            queue.add(origin);
            System.out.println(bfs());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int bfs() {

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Point point = queue.get(0);
            queue.remove(0);

            System.out.println(point);
            int x = point.getX();
            int y = point.getY();

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N || y + dy[i] < 0 || y + dy[i] >= M) {
                    continue;
                }

                if (VISIT[x+dx[i]][y+dy[i]] || MAP[x+dx[i]][y+dy[i]] == 0) {
                    continue;
                }

                if (x+dx[i] == N-1 && y+dy[i] == M-1) {
                    return point.getIndex() + 1;
                }
                System.out.println(String.format("X: %d, Y: %d", x+dx[i], y+dy[i]));
                queue.add(new Point(x+dx[i], y+dy[i], point.getIndex()+1));
                VISIT[x+dx[i]][y+dy[i]] = true;
            }
        }

        return -1;
    }
}
