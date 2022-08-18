import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int E = scan.nextInt();

            int[][] inputs = new int[E][3];

            for (int i = 0; i < E; i++) {
                for (int j = 0; j < 3; j++) {
                    inputs[i][j] = scan.nextInt();
                }
            }

            int condition1 = scan.nextInt();
            int condition2 = scan.nextInt();

            Solution1504 solution = new Solution1504();
            int answer = solution.solve(N,E,inputs,condition1, condition2);
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

class Solution1504 {

    private int N;
    private List<Edge>[] graph;

    private static final int INF = 987654321;

    public int solve(int n, int e, int[][] inputs, int condition1, int condition2) {
        this.N = n;
        this.graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] input : inputs) {
            int from = input[0];
            int to = input[1];
            int cost = input[2];

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        int[] cost1 = dijkstra(n,1);
        int[] costV1 = dijkstra(n,condition1);
        int[] costV2 = dijkstra(n,condition2);

        int answer = Math.min(cost1[condition1] + costV1[condition2] + costV2[n], cost1[condition2] + costV2[condition1] + costV1[n]);

        if (answer >= INF || answer < 0) return -1;
        return answer;
    }

    private int[] dijkstra(int n, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        int[] COST = new int[n + 1];
        Arrays.fill(COST, INF);
        COST[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.node;
            int cost = edge.cost;

            if (COST[node] < cost) continue;
            for (Edge nextEdge : graph[node]) {
                int nextNode = nextEdge.node;
                int nextCost = nextEdge.cost;

                if (COST[nextNode] > cost + nextCost) {
                    COST[nextNode] = cost + nextCost;
                    pq.add(new Edge(nextNode, cost + nextCost));
                }
            }
        }

        return COST;
    }

    private class Edge implements Comparable<Edge> {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
