import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            char[][] map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                map[i] = str.toCharArray();
            }

            Solution24463 solution24463 = new Solution24463();
            solution24463.solve(N,M,map);
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

class Solution24463 {

    StringBuilder sb = new StringBuilder();
    private int N,M;
    char[][] map;
    Point[][] points;

    int startX, startY, endX, endY;

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    public void solve(int n, int m, char[][] map) {
        this.N = n;
        this.M = m;
        this.map = map;
        this.points = new Point[N][M];

        boolean first = false;

        for (int i = 0; i < N; i++) {
            if (map[i][0] == '.') {
                if (!first) {
                    startX = i;
                    startY = 0;
                    first = true;
                } else {
                    endX = i;
                    endY = 0;
                    break;
                }
            }

            if (map[i][M - 1] == '.') {
                if (!first) {
                    startX = i;
                    startY = M - 1;
                    first = true;
                } else {
                    endX = i;
                    endY = M - 1;
                    break;
                }
            }
        }

        for (int col = 0; col < M; col++) {
            if (map[0][col] == '.') {
                if (!first) {
                    startX = 0;
                    startY = col;
                    first = true;
                } else {
                    endX = 0;
                    endY = col;
                    break;
                }
            }

            if (map[N - 1][col] == '.') {
                if (!first) {
                    startX = N - 1;
                    startY = col;
                    first = true;
                } else {
                    endX = N - 1;
                    endY = col;
                    break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '.') map[i][j] = '@';
            }
        }

        bfs();

        Point point = points[endX][endY];
        while (point != null) {
            map[point.x][point.y] = '.';
            point = point.from;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private void bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[startX][startY] = true;
        points[startX][startY] = new Point(startX, startY, null);
        queue.add(points[startX][startY]);

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;

                if (map[x + dx[i]][y + dy[i]] == '+' || visited[x + dx[i]][y + dy[i]]) continue;

                visited[x + dx[i]][y + dy[i]] = true;
                points[x + dx[i]][y + dy[i]] = new Point(x + dx[i], y + dy[i], point);
                queue.add(points[x + dx[i]][y + dy[i]]);
            }
        }
    }

    private class Point {
        int x;
        int y;
        Point from;

        public Point (int x, int y, Point from) {
            this.x = x;
            this.y = y;
            this.from = from;
        }
    }
}
