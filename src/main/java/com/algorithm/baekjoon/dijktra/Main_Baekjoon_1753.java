package com.algorithm.baekjoon.dijktra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_1753 {

    public static int V; // 1 <= V <= 20_000
    public static int E; // 1 <= E <= 300_000

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            String[] VE = bf.readLine().split(" ");
            V = Integer.parseInt(VE[0]);
            E = Integer.parseInt(VE[1]);

            Graph graph = new Graph(V);


            int id = Integer.parseInt(bf.readLine()); // start!

            for (int i = 0; i < E; i++) {

                int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph.add(info[0], info[1], info[2]);
            }

            graph.setStart(id);

            int[] shortestArray = graph.getShortestDistance();

            for (int i = 1; i <= V; i++) {
                int distance = shortestArray[i];
                if (distance == Integer.MAX_VALUE) {
                    System.out.println("INF");
                    continue;
                }
                System.out.println(distance);
            }
        } catch (IOException e) {
            e.printStackTrace();
       }
    }
}

class Graph {

    private final int[] shortestDistance; // 최단경로
    private final int size;               // 노드 갯수
    private final Map<Integer, List<NodeDistance>> edges;
    private final PriorityQueue<NodeDistance> pq = new PriorityQueue<>();

    public Graph(int size) {
        this.shortestDistance = new int[size+1];
        this.size = size;
        this.edges = new HashMap<>();
        init();
    }

    private void init() {
        for (int i = 1; i <= size; i++) {
            edges.put(i, new ArrayList<>());
            shortestDistance[i] = Integer.MAX_VALUE;
        }
    }

    public void setStart(int start) {
        shortestDistance[start] = 0;
        pq.offer(new NodeDistance(start, 0));
    }

    public void add(int from, int to, int distance) {
        edges.get(from).add(new NodeDistance(to, distance));
    }

    public int[] getShortestDistance() {

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int currentNode = current.getNode();
            int currentDistance = current.getDistance();
            int currentShortestDistance = shortestDistance[currentNode];

            if (currentShortestDistance < currentDistance) {
                continue;
            }
            // 인접노드 방문해서 최단경로 설정
            for (NodeDistance nd : edges.get(currentNode)) {
                int cost = currentShortestDistance + nd.getDistance();

                if (cost < shortestDistance[nd.getNode()]) {
                    shortestDistance[nd.getNode()] = cost;
                    pq.offer(new NodeDistance(nd.getNode(), cost));
                }
            }
        }

        return shortestDistance;
    }

}

class NodeDistance implements Comparable<NodeDistance> {
    private int node;
    private int distance;

    public NodeDistance(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public int getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return node + "=" + distance;
    }

    // 거리비용이 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(NodeDistance other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
