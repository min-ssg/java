package com.algorithm.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준: 군대탈출하기
 * 골드1
 * BFS
 * 너비 우선 탐색
 * 이분 탐색
 */

public class Main_Baekjoon_14948_군대탈출하기 {

    // 1 <= N, M <= 100
    private static int N;
    private static int M;

    private static int[] dx = {-1, 0, 1,  0};
    private static int[] dy = { 0, 1, 0, -1};

    private static int[][] MAP = new int[101][101];
    private static int[][][] VISITED = new int[101][101][2];
    private static PriorityQueue<State> PQ = new PriorityQueue<>();

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            String[] NM = bf.readLine().split(" ");
            N = Integer.parseInt(NM[0]);
            M = Integer.parseInt(NM[1]);

            for (int i = 0; i < N; i++) {
                int[] levels = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = levels[j];
                }
            }

        } catch (IOException e) {

        }
    }
}

class State implements Comparable<State> {
    private final int x;
    private final int y;
    private final int max;
    private final int dir;
    private final boolean canJump;

    public State(int x, int y, int max, int dir, boolean canJump) {
        this.x = x;
        this.y = y;
        this.max = max;
        this.dir = dir;
        this.canJump = canJump;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMax() {
        return max;
    }

    public int getDir() {
        return dir;
    }

    public boolean isCanJump() {
        return canJump;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.max, o.max);
    }
}
