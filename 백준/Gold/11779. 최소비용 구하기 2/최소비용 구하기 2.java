import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt(); // 도시의 개수
            int M = scan.nextInt();

            int[][] inputs = new int[M][3]; // start end weight;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < 3; j++) {
                    inputs[i][j] = scan.nextInt();
                }
            }
            int start = scan.nextInt();
            int end = scan.nextInt();

            Solution1916 solution = new Solution1916();
            solution.solve(N,M,inputs,start,end);
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

class Solution1916 {

    private int[] COST;

    public void solve(int n, int m, int[][] inputs, int start, int end) {

        List<Point>[] graph = new ArrayList[n + 1];
        int[] routes = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] input: inputs) {
            int s = input[0];
            int e = input[1];
            int w = input[2];

            graph[s].add(new Point(e, w));
        }
        StringBuilder sb = new StringBuilder();

        this.COST = new int[n + 1];
        Arrays.fill(COST, Integer.MAX_VALUE);
        COST[start] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point point = pq.poll();
            int target = point.end;
            int cost = point.cost;
            // 최단거리가 아닌 경우 PASS
            if (COST[target] < cost) continue;
            for (Point adjacent : graph[target]) {
                int next = adjacent.end;
                int nextCost = cost + adjacent.cost;
                if (nextCost < COST[next]) {
                    COST[next] = nextCost;
                    routes[next] = target;
                    pq.add(new Point(next, nextCost));
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int order = end;
        path.add(order);
        while (order != start) {
            path.add(routes[order]);
            order = routes[order];
        }

        Collections.reverse(path);
        String collect = path.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(COST[end]);
        System.out.println(path.size());
        System.out.println(collect);

    }

    private class Point implements Comparable<Point> {
        int end;
        int cost;

        Point(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}