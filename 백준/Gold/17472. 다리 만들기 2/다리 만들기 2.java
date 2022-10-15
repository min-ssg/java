import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[][] map = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution17472 solution = new Solution17472();
            int answer = solution.solve(N,M,map);
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

class Solution17472 {

    private int N,M;
    private int[] parent;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    ArrayList<Integer>[] graph;

    public int solve(int n, int m, int[][] map) {

        this.N = n;
        this.M = m;
        int index = 1;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i,j,map,visited,index++);
                }
            }
        }

        this.parent = new int[index];
        for (int i = 1; i < index; i++) {
            parent[i] = i;
        }

        List<Edge> edges = createEdges(map);
        Collections.sort(edges);
        this.graph = new ArrayList[index];
        for (int i = 1; i < index; i++) {
            graph[i] = new ArrayList<>();
        }

        int sum = 0;
        for (Edge edge : edges) {

            int s = edge.s.index;
            int e = edge.e.index;
            int distance = edge.distance;
            if (findParent(s) == findParent(e)) continue;
            // Union
            union(s,e);
            sum += distance;
        }
        int nx = parent[1];
        for (int i = 2; i < index; i++) {
            if (nx != findParent(parent[i])) {
                return -1;
            }
        }

        return sum;
    }

    private void union(int s, int e) {
        int ps = findParent(s);
        int pe = findParent(e);

        if (ps < pe) {
            parent[pe] = ps;
        } else {
            parent[ps] = pe;
        }
    }

    private int findParent(int x) {
        if (parent[x] == x) return x;
        int nx = findParent(parent[x]);
        return nx;
    }

    private List<Edge> createEdges(int[][] map) {
        List<Edge> edges = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int index = map[i][j];
                linkedEdge(i,j,index,map,edges);
            }
        }

        return edges;
    }

    private void linkedEdge(int x, int y, int index, int[][] map, List<Edge> edges) {
        Point start = new Point(x,y,index);
        int i = 0, count = 0;
        // Left
        for (i = y - 1, count = 0; i >= 0; i--, count++) {
            if (map[x][i] == 0) continue;
            if (map[x][i] == index) break;
            if (count < 2) break;
            edges.add(new Edge(start, new Point(x,i,map[x][i]), count));
            break;
        }

        // Up
        for (i = x - 1, count = 0; i >= 0; i--, count++) {
            if (map[i][y] == 0) continue;
            if (map[i][y] == index) break;
            if (count < 2) break;
            edges.add(new Edge(start, new Point(i,y,map[i][y]), count));
            break;
        }

        // Right
        for (i = y + 1, count = 0; i < M; i++, count++) {
            if (map[x][i] == 0) continue;
            if (map[x][i] == index) break;
            if (count < 2) break;
            edges.add(new Edge(start, new Point(x,i,map[x][i]), count));
            break;
        }

        // Down
        for (i = x + 1, count = 0; i < N; i++, count++) {
            if (map[i][y] == 0) continue;
            if (map[i][y] == index) break;
            if (count < 2) break;
            edges.add(new Edge(start, new Point(i,y,map[i][y]), count));
            break;
        }
    }

    private void bfs(int x, int y, int[][] map, boolean[][] visited, int index) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));
        visited[x][y] = true;
        map[x][y] = index;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;

            for (int i = 0; i < 4; i++) {
                if (x + dx[i] < 0 || x + dx[i] >= N
                ||  y + dy[i] < 0 || y + dy[i] >= M) continue;
                if (map[x + dx[i]][y + dy[i]] != 1 || visited[x + dx[i]][y + dy[i]]) continue;

                map[x + dx[i]][y + dy[i]] = index;
                visited[x + dx[i]][y + dy[i]] = true;
                queue.add(new Point(x + dx[i], y + dy[i]));
            }
        }
    }

    private class Point {
        int x;
        int y;
        int index;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point (int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    private class Edge implements Comparable<Edge> {
        Point s;
        Point e;
        int distance;

        public Edge (Point s, Point e, int distance) {
            this.s = s;
            this.e = e;
            this.distance = distance;
        }

        @Override
        public int compareTo (Edge other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}