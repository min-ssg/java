import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Kruskal
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            Solution9370 solution = new Solution9370();
            int T = scan.nextInt();
            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                int M = scan.nextInt();
                int L = scan.nextInt();

                int s = scan.nextInt();
                int g = scan.nextInt();
                int h = scan.nextInt();

                int[][] R = new int[M][3];
                int[] C = new int[L];
                for (int j = 0; j < M; j++) {
                    R[j][0] = scan.nextInt();
                    R[j][1] = scan.nextInt();
                    R[j][2] = scan.nextInt();
                }

                for (int j = 0; j < L; j++) {
                    C[j] = scan.nextInt();
                }

                sb.append(solution.solve(N,M,L,R,C,s,g,h)).append('\n');
            }

            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader (BufferedReader br) {
            this.br = br;
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}

class Solution9370 {

    private List<Edge>[] graph;

    public String solve(int N, int M, int L, int[][] R, int[] C, int s, int g, int h) {
        initializeGraph(N, R);

        int[] costOfS = dijkstra(N,s);
        int[] costOfG = dijkstra(N,g);
        int[] costOfH = dijkstra(N,h);

        return IntStream.of(C).sorted()
                .filter(c -> costOfS[c] == costOfS[g] + costOfG[h] + costOfH[c] || costOfS[c] == costOfS[h] + costOfH[g] + costOfG[c])
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    private int[] dijkstra(int N, int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        pq.add(new Edge(s,0));
        cost[s] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (cost[edge.node] < edge.value) continue;

            for (Edge nextEdge : graph[edge.node]) {
                if (cost[nextEdge.node] > nextEdge.value + edge.value) {
                    cost[nextEdge.node] = nextEdge.value + edge.value;
                    pq.add(new Edge(nextEdge.node, cost[nextEdge.node]));
                }
            }
        }

        return cost;
    }

    private void initializeGraph(int N, int[][] R) {
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] rel : R) {
            int x = rel[0];
            int y = rel[1];
            int value = rel[2];

            graph[x].add(new Edge(y,value));
            graph[y].add(new Edge(x,value));
        }
    }

    private class Edge implements Comparable<Edge> {
        int node;
        int value;

        public Edge (int node, int value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Edge other) {
            return this.value - other.value;
        }
    }
}
