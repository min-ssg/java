import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int M = scan.nextInt();
            int N = scan.nextInt();
            int H = scan.nextInt();

            int[][][] A = new int[H][N][M];
            List<int[]> matures = new ArrayList<>();
            int immature = 0;

            for (int k = 0; k < H; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        A[k][i][j] = scan.nextInt();
                        if (A[k][i][j] == 0) immature += 1;
                        else if (A[k][i][j] == 1) {
                            matures.add(new int[]{k,i,j});
                        }
                    }
                }
            }

            Solution7569 solution = new Solution7569();
            int answer = solution.solve(N,M,H,A,immature,matures);
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

class Solution7569 {

    private int N,M,H;
    private int[][][] A;
    private boolean[][][] visited;

    private int[] dx = {-1,0,1,0,0,0};
    private int[] dy = {0,1,0,-1,0,0};
    private int[] dz = {0,0,0,0,1,-1};

    public int solve(int N, int M, int H, int[][][] A, int immature, List<int[]> matures) {
        this.N = N;
        this.M = M;
        this.H = H;
        this.A = A;

        this.visited = new boolean[H][N][M];

        if (immature == 0) {
            return 0;
        }

        return bfs(immature, matures);
    }

    private int bfs(int immature, List<int[]> matures) {
        Queue<Point> queue = new LinkedList<>();
        for (int[] mature : matures) {
            int z = mature[0];
            int x = mature[1];
            int y = mature[2];

            queue.add(new Point(z,x,y,0));
            visited[z][x][y] = true;
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int z = point.z;
            int x = point.x;
            int y = point.y;
            int day = point.day;

            for (int i = 0; i < 6; i++) {
                if (z + dz[i] < 0 || z + dz[i] >= H
                ||  x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                if (A[z + dz[i]][x + dx[i]][y + dy[i]] == -1
                ||  visited[z + dz[i]][x + dx[i]][y + dy[i]]) continue;

                if (--immature == 0) return day + 1;

                queue.add(new Point(z + dz[i], x + dx[i] , y + dy[i], day + 1));
                visited[z + dz[i]][x + dx[i]][y + dy[i]] = true;
            }
        }

        return -1;
    }

    private class Point {
        int z;
        int x;
        int y;
        int day;

        public Point(int z, int x, int y, int day) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}