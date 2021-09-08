package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 골드4
 * dfs
 */

public class Main_Baekjoon_1520_내리막길 {

    public static int[] rows = {-1, 0, 1, 0};
    public static int[] cols = {0, 1, 0, -1};
    public static int N;
    public static int M;
    public static Vertex[][] vertexArrays;

    public static List<Point> list = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());

            int start = 10000;
            int[][] map = new int[N][M];
            vertexArrays = new Vertex[N][M];

            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < M; j++) {
                    int value = Integer.parseInt(stringTokenizer.nextToken());
                    vertexArrays[i][j] = new Vertex(i, j, -1, value);
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(vertexArrays[i][j].getValue() + " ");
                }
                System.out.println();
            }

            int answer = dfs(vertexArrays[0][0]);

            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.toString(vertexArrays[i]));
            }
            System.out.println("answer = " + answer);
            System.out.println(vertexArrays[0][0].getWeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int dfs(Vertex vertex) {

        int x = vertex.getX();
        int y = vertex.getY();
        int weight = vertex.getWeight();
        if (weight != -1) return weight;

        if (x == (N-1) && y == (M-1)) return 1;
        vertex.addWeight(1);
        for (int i = 0; i < 4; i++) {
            if (0 <= x + rows[i] && x + rows[i] < N && 0 <= y + cols[i] && y + cols[i] < M) {
                Vertex nextVertex = vertexArrays[x + rows[i]][y + cols[i]];
                if (vertex.getValue() > nextVertex.getValue()) {
                    System.out.println(String.format("from: %s, %s = %s , to : %s, %s = %s", vertex.getX(), vertex.getY(), vertex.getWeight(), nextVertex.getX(), nextVertex.getY(), nextVertex.getWeight()));
                    vertex.addWeight(dfs(nextVertex));
                }
            }
        }

        return vertex.getWeight();
    }
}

class Vertex {
    private int x;
    private int y;
    private int weight;
    private int value;

    public Vertex(int x, int y, int weight, int value) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public void addWeight(int add) {
        this.weight += add;
    }

    @Override
    public String toString() {
        return String.format("%s", weight);
    }
}
