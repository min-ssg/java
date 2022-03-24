package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Baekjoon_12886_돌그룹 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int A = scan.nextInt();
            int B = scan.nextInt();
            int C = scan.nextInt();

            Solution12886 solution12886 = new Solution12886();
            int answer = solution12886.solve(A,B,C);
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

class Solution12886 {

    private int sum, target;
    private boolean[][] visited = new boolean[1001][1001];

    Queue<Node> queue = new LinkedList<>();

    public int solve(int a, int b, int c) {
        this.sum = a + b + c;

        if (sum % 3 != 0) {
            return 0;
        }

        target = sum / 3;
        System.out.println("Target = " + target);

        if (a == target || b == target || c == target) return 1;

        return bfs(a,b);
    }

    private int bfs(int a, int b) {

        if (a < b) {
            queue.add(new Node(a, b));
        } else {
            queue.add(new Node(b, a));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int x = node.x;
            int y = node.y;

            if (x == target || y == target) return 1;

            if (x == y) {
                int c = sum - (x + y);
                if (x < c) {
                    nextNode(x,c);
                } else {
                    nextNode(c,x);
                }

                continue;
            }
            nextNode(x,y);
        }

        return 0;
    }

    private void nextNode(int x, int y) {
        int nextX = x * 2;
        int nextY = y - x;

        System.out.println("(" + nextX + "," + nextY + ")");
        if (nextX < nextY) {
            if (visited[nextX][nextY]) return;
            visited[nextX][nextY] = true;
            queue.add(new Node(nextX, nextY));
        } else {
            if (visited[nextY][nextX]) return;
            visited[nextY][nextX] = true;
            queue.add(new Node(nextY,nextX));
        }
    }

    private class Node {
        int x,y;
        boolean visited;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
