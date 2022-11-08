import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // 2 <= N <= 100_000;
            int M = scan.nextInt(); // 1 <= M <= 1_000_000;

            PriorityQueue<Edge> edges = new PriorityQueue<>();

            for (int i = 0; i < M; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int cost = scan.nextInt();

                edges.add(new Edge(x,y,cost));
            }

            Solution1647 solution = new Solution1647();
            int answer = solution.solve(N,M,edges);

            System.out.println(answer);
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

class Solution1647 {
    private int N,M;
    private int[] parent;
    private List<Edge> list = new ArrayList<>();

    public int solve(int N,int M, PriorityQueue<Edge> edges) {
        this.N = N;
        this.M = M;

        this.parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (find(edge.x) == find(edge.y)) continue;
            union(edge.x, edge.y);
            list.add(edge);
        }

        list.remove(list.size() - 1);
        return list.stream().mapToInt(edge -> edge.cost).sum();
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}

class Edge implements Comparable<Edge> {
    int x;
    int y;
    int cost;

    protected Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return "{x = " + x + ", y = " + y + ", cost = " + cost + "}";
    }
}