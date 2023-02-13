import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main  {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int M = scan.nextInt();
            int N = scan.nextInt();

            char[][] map = new char[N][M];

            for (int i = 0; i < N; i++) {
                map[i] = scan.nextLine().toCharArray();
            }

            Solution6087 solution = new Solution6087();
            int answer = solution.solve(N,M,map);
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FastReader {
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

class Solution6087 {

    private int N,M;

    private static final int INF = 987_654_321;

    private final int[] dx = {-1,0,1,0};
    private final int[] dy = {0,1,0,-1};

    public int solve(int n, int m, char[][] map) {
        this.N = n;
        this.M = m;

        Node start = findStartNode(map);
        Node end = findEndNode(map);

        return bfs(start, end, map);
    }

    private int bfs(Node start, Node end, char[][] map) {
        int[][][] visited = createVisitedMap();
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            queue.add(new Node(start.x, start.y, i, 0));
            visited[start.x][start.y][i] = 0;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nCount = node.count;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == '*') continue;

                if (node.direction != i) nCount += 1;

                if (visited[nx][ny][i] > nCount) {
                    queue.add(new Node(nx, ny, i, nCount));
                    visited[nx][ny][i] = nCount;
                }
            }
        }

        return Arrays.stream(visited[end.x][end.y]).min().getAsInt();
    }

    private int[][][] createVisitedMap() {
        int[][][] visited = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                Arrays.fill(visited[i][j], INF);
        }
        return visited;
    }

    private Node findStartNode(char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'C') {
                    map[i][j] = '.';
                    return new Node(i,j);
                }
            }
        }

        return null;
    }

    private Node findEndNode(char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'C') {
                    map[i][j] = '.';
                    return new Node(i,j);
                }
            }
        }

        return null;
    }

    private class Node implements Comparable<Node> {
        int x;
        int y;
        int direction;
        int count;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node (int x, int y, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.count, other.count);
        }
    }
}

