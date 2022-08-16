import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            Solution4485 solution = new Solution4485();
            StringBuilder sb = new StringBuilder();
            final String prefix = "Problem";

            int index = 0;
            while (true) {
                int N = scan.nextInt();
                if (N == 0) break;
                index++;

                int[][] MAP = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        MAP[i][j] = scan.nextInt();
                    }
                }

                int answer = solution.solve(N, MAP);
                sb.append(String.format("Problem %d: %d\n", index, answer));
            }

            System.out.println(sb.toString());
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

class Solution4485 {

    private int N;
    private final int[] dx = {-1,0,1,0};
    private final int[] dy = {0,1,0,-1};

    private int[][] COST;

    public int solve(int n, int[][] map) {
        this.N = n;
        this.COST = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(COST[i], Integer.MAX_VALUE);
        }
        COST[0][0] = map[0][0];

        dijkstra(n, map);

        return COST[n-1][n-1];
    }

    private void dijkstra(int n, int[][] map) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0,0,COST[0][0]));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int x = edge.getX();
            int y = edge.getY();
            int cost = edge.getCost();

            if (COST[x][y] < cost) continue;

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= n
                ||  y + dy[i] < 0 || y + dy[i] >= n) continue;

                if (COST[x + dx[i]][y + dy[i]] > cost + map[x + dx[i]][y + dy[i]]) {
                    COST[x + dx[i]][y + dy[i]] = cost + map[x + dx[i]][y + dy[i]];
                    pq.add(new Edge(x + dx[i], y + dy[i], cost + map[x + dx[i]][y + dy[i]]));
                }
            }
        }
    }

    private class Edge implements Comparable<Edge> {
        private int x;
        private int y;
        private int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}