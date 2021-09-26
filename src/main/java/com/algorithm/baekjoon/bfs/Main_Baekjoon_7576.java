package com.algorithm.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

/**
 * 제목: 토마토
 * 난이도: 실버1
 * @author Minseok
 * @since 2021.09.26
 */
public class Main_Baekjoon_7576 {

    public static int N; // 2 <= N <= 1_000
    public static int M; // 2 <= M <= 1_000

    public static int[][] MAP;
    public static boolean[][] VISITED;
    //public static List<Box> LIST = new ArrayList<>();
    public static Queue<Box> LIST = new LinkedList<>();

    // 동남서북
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] nm = br.readLine().split(" ");
            N = Integer.parseInt(nm[1]); // 행
            M = Integer.parseInt(nm[0]); // 열

            MAP = new int[N][M];
            VISITED = new boolean[N][M];

            for (int row = 0; row < N; row++) {
                String[] boxes = br.readLine().split(" ");
                for (int col = 0; col < M; col++) {
                    MAP[row][col] = Integer.parseInt(boxes[col]);

                    if (MAP[row][col] == 1) {
                        LIST.add(new Box(row, col, 0));
                    } else if (MAP[row][col] == -1) {
                        VISITED[row][col] = true;
                    }
                }
            }

            for (int[] array: MAP) {
                System.out.println(Arrays.toString(array));
            }

            int minDate = BFS();

            System.out.println(minDate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int BFS() {

        int distance = 0;

        System.out.println(LIST.toString());

        while (!LIST.isEmpty()) {

            Box box = LIST.poll();
            // Box box = LIST.get(0);
            // box.remove(0); ArrayList를 사용할 경우, 리스트의 변경이 잦을 수록 성능에 악영향을 미침.
            int x = box.getX();
            int y = box.getY();
            distance = box.getDistance();
            VISITED[x][y] = true;
            System.out.println(box);
            for (int index = 0; index < 4; index++) {
                if (x + dx[index] < 0 || x + dx[index] >= N
                ||  y + dy[index] < 0 || y + dy[index] >= M) {
                    continue;
                }

                // 이미 방문했다면 패스
                if (VISITED[x + dx[index]][y + dy[index]]) {
                    continue;
                }
                LIST.add(new Box(x + dx[index], y + dy[index], distance + 1));
                VISITED[x + dx[index]][y + dy[index]] = true;
            }
        }

        if (!checkAllVisited()) {
            return -1;
        }

        return distance;
    }

    private static boolean checkAllVisited() {
        for (boolean[] boolArray : VISITED) {
            for (boolean bool : boolArray) {
                if (bool == false) {
                    return false;
                }
            }
        }

        return true;
    }
}

class Box {
    int x;
    int y;
    int distance;

    public Box(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + "), " + distance;
    }
}
