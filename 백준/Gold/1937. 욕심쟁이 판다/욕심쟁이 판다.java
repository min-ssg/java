import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] map = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }
            
            Solution1937 solution = new Solution1937();
            int answer = solution.solve(N,map);
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

class Solution1937 {

    private int N;
    private int[][] map, visited;
    private final Queue<Point> queue = new LinkedList<>();

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int[][] map) {
        this.N = n;
        this.map = map;
        this.visited = new int[n][n];
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max,dfs(i,j));
            }
        }
        return max;
    }
    
    // dfs + dp
    private int dfs(int x, int y) {
        if (visited[x][y] > 0) return visited[x][y];
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N
            ||  y + dy[i] < 0 || y + dy[i] >= N) continue;
            if (map[x][y] >= map[x + dx[i]][y + dy[i]]) continue;
            visited[x][y] = Math.max(visited[x][y], 1 + dfs(x + dx[i], y + dy[i]));
        }
        return visited[x][y];
    }

    // bfs : timeout
    private void bfs(int x, int y) {
        queue.add(new Point(x,y));
        visited[x][y] = 1;

        while (!queue.isEmpty()) {
            Point point  = queue.poll();
            x = point.x;
            y = point.y;

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= N) continue;
                if (map[x][y] >= map[x + dx[i]][y + dy[i]]) continue;

                if (visited[x + dx[i]][y + dy[i]] < visited[x][y] + 1) {
                    visited[x + dx[i]][y + dy[i]] = visited[x][y] + 1;
                    queue.add(new Point(x + dx[i], y + dy[i]));
                }
            }
        }
    }

    private class Point {
        int x;
        int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}