import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            Solution1005 solution = new Solution1005();
            int T = scan.nextInt();

            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                int K = scan.nextInt();
                int[] D = new int[N + 1];
                for (int j = 1; j <= N; j++) {
                    D[j] = scan.nextInt();
                }
                int[][] R = new int[K][2];

                for (int j = 0; j < K; j++) {
                    R[j][0] = scan.nextInt();
                    R[j][1] = scan.nextInt();
                }

                int W = scan.nextInt();

                int answer = solution.solve(N, K, D, R, W);
                sb.append(answer).append('\n');
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

class Solution1005 {

    private int N,K,W;
    private int[] D, degrees;

    private List<Integer>[] graph;


    public int solve(int n, int k, int[] d, int[][] r, int w) {
        this.N = n;
        this.K = k;
        this.W = w;
        this.D = d;
        this.graph = new ArrayList[n + 1];
        this.degrees = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] rel : r) {
            int x = rel[0];
            int y = rel[1];

            graph[x].add(y);
            degrees[y] += 1; // 차수 하나씩 증가.
        }

        return bfs();
    }

    private int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        int[] cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (degrees[i] == 0) {
                queue.add(i);
                cost[i] = D[i];
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int next : graph[x]) {
                degrees[next] -= 1;
                if (cost[next] < cost[x] + D[next]) {
                    cost[next] = cost[x] + D[next];
                }
                if (degrees[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return cost[W];
    }
}