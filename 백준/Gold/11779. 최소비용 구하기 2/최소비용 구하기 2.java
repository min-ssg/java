import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int numberOfCity = scan.nextInt();
            int numberOfBus = scan.nextInt();

            int[][] busInfo = new int[numberOfBus][3];
            for (int i = 0; i < numberOfBus; i++) {
                for (int j = 0; j < 3; j++) {
                    busInfo[i][j] = scan.nextInt();
                }
            }

            int start = scan.nextInt();
            int end = scan.nextInt();

            Solution11779 solution = new Solution11779();
            solution.solve(numberOfCity, numberOfBus, busInfo, start, end);
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

class Solution11779 {

    StringBuilder sb = new StringBuilder();

    List<Edge>[] graph;
    int[] COST;
    int[] routes;

    public void solve(int numberOfCity, int numberOfBus, int[][] busInfo, int start, int end) {
        initGraph(numberOfCity, busInfo);
        this.COST = new int[numberOfCity + 1];
        this.routes = new int[numberOfCity + 1];
        Arrays.fill(COST, Integer.MAX_VALUE);

        dijkstra(start, end);
        List<Integer> path = getPath(start, end);
        sb.append(COST[end]).append('\n');
        sb.append(path.size()).append('\n');
        sb.append(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(sb.toString());
    }

    private List<Integer> getPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        int city = end;
        path.add(end);
        while (city != start) {
            path.add(routes[city]);
            city = routes[city];
        }

        Collections.reverse(path);
        return path;
    }

    private void dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        COST[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int city = edge.city;
            int cost = edge.cost;

            if (COST[city] < cost) continue; // 현재 위치에서 city까지의 거리가 최단거리가 아닌 경우, 계산할 의미가 없음.
            for (Edge nextEdge : graph[city]) {
                int nextCity = nextEdge.city;
                int nextCost = nextEdge.cost;

                if (COST[nextCity] > cost + nextCost) {
                    COST[nextCity] = cost + nextCost;
                    routes[nextCity] = city; // 다음 city로 가기 위해 현재 city를 거쳐간다는 의미.
                    pq.add(new Edge(nextCity, cost + nextCost));
                }
            }
        }
    }

    private void initGraph(int numberOfCity, int[][] busInfo) {
        this.graph = new ArrayList[numberOfCity + 1];
        for (int i = 1; i <= numberOfCity; i++) this.graph[i] = new ArrayList<>();

        for (int[] info : busInfo) {
            int s = info[0];
            int e = info[1];
            int cost = info[2];

            graph[s].add(new Edge(e, cost));
        }
    }

    /**
     * 현재 위치에서 city까지의 cost를 담는 structor class
     */
    private class Edge implements Comparable<Edge> {
        int city;
        int cost;

        public Edge(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}