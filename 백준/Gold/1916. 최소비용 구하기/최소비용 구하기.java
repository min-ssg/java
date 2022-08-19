import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] inputs = new int[M][3];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < 3; j++) {
                    inputs[i][j] = scan.nextInt();
                }
            }
            int A = scan.nextInt();
            int B = scan.nextInt();

            Solution1916_2 solution = new Solution1916_2();
            int answer = solution.solve(N, M, inputs, A, B);
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

class Solution1916_2 {

    Map<Node, List<Edge>> graph = new HashMap<>();

    public int solve(int n, int m, int[][] inputs, int a, int b) {
        Node[] nodes = new Node[n + 1];
        IntStream.rangeClosed(1,n).forEach(i -> nodes[i] = new Node(i));

        // graph 완성하기.
        for (int[] input : inputs) {
            if (!graph.containsKey(nodes[input[0]])) {
                graph.put(nodes[input[0]], new ArrayList<>());
            }
            graph.get(nodes[input[0]]).add(new Edge(nodes[input[1]], input[2]));
        }

        return dijkstra(n, nodes[a], nodes[b]);
    }

    private int dijkstra(int n, Node a, Node b) {

        int[] COST = new int[n + 1];
        Arrays.fill(COST, Integer.MAX_VALUE);
        //PriorityQueue<Edge> pq = new PriorityQueue<>(graph.get(a));
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(a, 0));
        COST[a.index] = 0;
        //graph.get(a).forEach(edge -> COST[edge.node.index] = edge.cost);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            Node node = edge.node;
            int cost = edge.cost;

            if (node == b) break;
            if (COST[node.index] < cost) continue;
            if (graph.get(node) == null) continue;
            for (Edge nextEdge : graph.get(node)) {
                Node nextNode = nextEdge.node;
                int nextCost = nextEdge.cost;

                if (COST[nextNode.index] > cost + nextCost) {
                    COST[nextNode.index] = cost + nextCost;
                    pq.add(new Edge(nextNode, cost + nextCost));
                }
            };
        }

        return COST[b.index];
    }

    private class Node {
        int index;

        public Node (int index) {
            this.index = index;
        }

//        @Override
//        public String toString() {
//            return String.valueOf(index);
//        }
    }

    private class Edge implements Comparable<Edge> {
        Node node;
        int cost;

        public Edge(Node node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]",node, cost);
        }
    }
}