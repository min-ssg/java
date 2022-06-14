import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준: 군대탈출하기
 * 골드2
 * BFS
 * 너비 우선 탐색
 */

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            // 1 <= N,M <= 100
            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] MAP = new int[N][M];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            Solution14948 solution14948 = new Solution14948();
            int answer = solution14948.solve(N,M,MAP);
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

class Solution14948 {
    private enum Direction {
        UP(-1,0), RIGHT(0,1), DOWN(1,0), LEFT(0,-1);

        private int x;
        private int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private int N, M;
    private int[][] MAP;
    private int[][][] states; // 해당 좌표에서 JUMP 했는지 안했는지.

    public int solve(int n, int m, int[][] map) {
        initialize(n, m, map);
        return bfs();
    }

    private void initialize(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map;

        this.states = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(states[i][j], -1);
            }
        }
    }

    private int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Point start = new Point(0,0, MAP[0][0], 1); // jumpable 0 : 점프 x, 1 : 점프 o
        pq.add(start);

        while (!pq.isEmpty()) {

            Point current = pq.poll();
            int x = current.x;
            int y = current.y;
            int currentValue = current.value;
            int isJumpable = current.jumpable;

            // 1. 이미 방문한적이 있고,(AND)
            // 2. 방문한 값 state[x][y][isJumpable] < currentValue이면 이미 최소이기 때문에 방문 이유가 없다.
            if (states[x][y][isJumpable] != -1 && states[x][y][isJumpable] < currentValue) continue;

            // 방문한 적이 없기 때문에, 방문하면 된다. 우선순위 큐로 인해 맨처음 방문이 최소를 보장함.
            states[x][y][isJumpable] = currentValue;

            // 끝점(N-1, M-1)에 도달하면 return
            if (x == N - 1 && y == M - 1) return currentValue;

            for (Direction direct : Direction.values()) {

                int[] nextXY = jump(x,y,direct);
                int nx = nextXY[0];
                int ny = nextXY[1];

                // 범위 벗어나면 continue;
                if (nx < 0 || nx >= N
                ||  ny < 0 || ny >= M) continue;

                // 이미 방문한 곳은 제외.
                if (states[nx][ny][isJumpable] != -1) continue;

                pq.add(new Point(nx, ny, Math.max(currentValue, MAP[nx][ny]), isJumpable));

                // 점프를 이미 했다면 패스 또는 점프 범위 벗어나면 탈락.
                nextXY = jump(nx, ny, direct);
                nx = nextXY[0];
                ny = nextXY[1];
                if (isJumpable == 0
                || nx < 0 || nx >= N
                || ny < 0 || ny >= M) continue;

                if (states[nx][ny][isJumpable] != -1) continue;

                pq.add(new Point(nx, ny, Math.max(currentValue, MAP[nx][ny]), 0));
            }
        }

        return -1;
    }

    private int[] jump(int x, int y, Direction direction) {
        return new int[]{x + direction.x, y + direction.y};
    }

    private class Point implements Comparable<Point> {
        int x;
        int y;
        int value;
        int jumpable; // 1 또는 0;

        public Point(int x, int y, int value, int jumpable) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.jumpable = jumpable;
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.value, other.value);
        }
    }
}
