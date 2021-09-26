package com.algorithm.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 제목: 단지 번호 붙이기
 * 난이도: 실버1
 * @author Minseok
 * @since 2021.09.26
 */
public class Main_Baekjoon_2667 {

    public static int N;
    public static boolean[][] VISITED;
    public static int[][] MAP;

    public static List<Integer> HOUSE = new ArrayList<>();

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());
            VISITED = new boolean[N][N];
            MAP = new int[N][N];


            for (int row = 0; row < N; row++) {
                String line = br.readLine();
                System.out.println(line);
                for (int col = 0; col < N; col++) {
                    MAP[row][col] = line.charAt(col) - '0';
                }
            }
            for (int[] array : MAP) {
                System.out.println(Arrays.toString(array));
            }


            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    int count = 0;
                    if ((count = DFS(row, col)) > 0) {
                        HOUSE.add(count);
                    }
                }
            }

            System.out.println(HOUSE.size());
            Collections.sort(HOUSE);
            for (int number : HOUSE) {
                System.out.println(number);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int DFS(int row, int col) {
        if (MAP[row][col] == 0
         || VISITED[row][col]) {
            return 0;
        }

        int current = 1; // 위의 조건을 통과했다는 것은 이미 하나의 단지를 형성했다는 것을 의미한다.
        VISITED[row][col] = true;

        for (int index = 0; index < 4; index++) {

            if (row + dx[index] < 0 || row + dx[index] >= N
            ||  col + dy[index] < 0 || col + dy[index] >= N) {
                continue;
            }

            current += DFS(row + dx[index], col + dy[index]);
        }

        return current;
    }
}
