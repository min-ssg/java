package com.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_1325_효율적인해킹_S2 {

    public static int N;
    public static int M;
    public static Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    public static int[] dp;
    public static boolean [] visit;

    public static void main(String[] args) {
        // graph

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N+1];
            visit = new boolean[N+1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());

                int key = Integer.parseInt(st.nextToken());
                int index = Integer.parseInt(st.nextToken());
                if (!graph.containsKey(key)) {
                    graph.put(key, new ArrayList<>());
                }
                graph.get(key).add(index);
            }

            for (int i = 1; i <= N; i++) {
                dfs(i);
                Arrays.fill(visit, false);
            }

            System.out.println(Arrays.toString(dp));

            int max = Arrays.stream(dp).max().getAsInt();

            for (int i = 1; i <= N; i++) {
                if (max == dp[i]) {
                    System.out.print(i + " ");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dfs(int key) {

        if (visit[key]) {
            return;
        }

        dp[key]++;
        visit[key] = true;
        List<Integer> list = graph.getOrDefault(key, new ArrayList<>());
        for (Integer nextKey: list) {
            dfs(nextKey);
        }
    }
}
