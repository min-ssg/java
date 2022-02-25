package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main_Baekjoon_15681_트리와쿼리 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[] values = new int[N+1];

            for (int i = 1; i <= N; i++) {
                values[i] = scan.nextInt();
            }

            RootedTree rootedTree = new RootedTree(N,values);
            for (int i = 1; i <= N-1; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                rootedTree.add(x,y);
            }
            rootedTree.dfs(rootedTree.getNode(1));
            System.out.println(rootedTree.getMaxValue());

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

class RootedTree {
    Node root;
    Node[] nodes;
    boolean[] visited;
    int[][] dp;

    public RootedTree(int N, int[] values) {
        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i, values[i]);
        }

        visited = new boolean[N+1];
        dp = new int[N+1][2];
    }

    public void add(int node1, int node2) {
        nodes[node1].add(nodes[node2]);
        nodes[node2].add(nodes[node1]);
    }

    public void dfs(Node node) {
        visited[node.getIndex()] = true;
        List<Node> children = node.getChildren();

        dp[node.getIndex()][0] = 0;
        dp[node.getIndex()][1] = node.getValue();
        for (Node child : children) {
            if (visited[child.getIndex()]) continue;
            dfs(child);

            dp[node.getIndex()][1] += dp[child.getIndex()][0];
            dp[node.getIndex()][0] += Math.max(dp[child.getIndex()][0], dp[child.getIndex()][1]);
        }
    }

    public Node getNode(int index) {
        return nodes[index];
    }

    public int getMaxValue() {
        return Math.max(dp[1][0], dp[1][1]);
    }
}

class Node {
    private final int index;
    private List<Node> children;
    private final int value;

    public Node (int index, int value) {
        this.index = index;
        this.value = value;
        this.children = new ArrayList<Node>();
    }

    public void add(Node child) {
        if (this.index == child.getIndex()) {
            return;
        }

        children.add(child);
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public List<Node> getChildren()  {
        return children;
    }
}