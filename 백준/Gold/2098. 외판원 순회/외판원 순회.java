import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] W = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    W[i][j] = scan.nextInt();
                }
            }

            Solution2098 solution = new Solution2098();
            int answer = solution.solve(N,W);

            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
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
    }
}

class Solution2098 {
    private static final int INF = 987_654_321;
    private int N;
    private int allVisited;
    private int[][] W, cache;
    public int solve(int N, int[][] W) {
        this.N = N;
        this.W = W;
        this.allVisited = (1 << N) - 1; // bit mask
        this.cache = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        return findShortestPath(0,1);
    }

    public int findShortestPath(int last, int nVisited) {
        if (nVisited == allVisited) {
            return W[last][0] == 0 ? INF : W[last][0];
        }

        if (cache[last][nVisited] != -1) return cache[last][nVisited];

        int distance = INF;
        for (int city = 0; city < N; city++) {
            // and 연산에서 0이 아니면 이미 방문한 도시임.
            // W[last][city]가 0이면 해당 길이 없다는 것.
            if ((nVisited & (1 << city)) != 0 || W[last][city] == 0) continue;

            distance = Math.min(distance, findShortestPath(city, (nVisited | (1 << city))) + W[last][city]);
        }

        cache[last][nVisited] = distance;
        return distance;
    }
}
