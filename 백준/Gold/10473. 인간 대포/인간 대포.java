import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String args[]) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            
            double startX = scan.nextDouble();
            double startY = scan.nextDouble();
            double endX = scan.nextDouble();
            double endY = scan.nextDouble();
            int N = scan.nextInt(); // 점의개수
            
            double[][] points = new double[N + 2][2];
            points[0][0] = startX; points[0][1] = startY;
            points[N + 1][0] = endX; points[N + 1][1] = endY;
            
            for (int i = 1; i <= N; i++) {
                points[i][0] = scan.nextDouble();
                points[i][1] = scan.nextDouble();
            }
            
            Solution solution = new Solution();
            double answer = solution.solve(N, points);
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
        
        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}

class Solution {
    
    private static final double SPEED = 5.0;
    
    private Map<Point, List<Edge>> graph;
    private Map<Point, Double> COST;
    
    public double solve(int N, double[][] inputs) {
        int size = N + 2;
        int lastIndex = N + 1;
        
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(inputs[i][0], inputs[i][1]);
        }
        
        this.graph = new HashMap<>();
        this.COST = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            graph.put(points[i], new ArrayList<>());
            COST.put(points[i], Double.MAX_VALUE);
        }
        
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                if (i == 0) graph.get(points[i]).add(new Edge(points[j], getWalkCost(points[i], points[j])));
                else graph.get(points[i]).add(new Edge(points[j], getCanonCost(points[i], points[j])));
            }
        }
        
        dijkstra(points[0]);
        
        return COST.get(points[lastIndex]);
    }
    
    private void dijkstra(Point start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0.0));
        COST.replace(start, 0.0);
        
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            Point point = edge.point;
            double cost = edge.cost;
            
            if (COST.get(point) < cost) continue;
            for (Edge nextEdge : graph.get(point)) {
                Point nextPoint = nextEdge.point;
                double nextCost = nextEdge.cost;
                
                if (COST.get(nextPoint) > cost + nextCost) {
                    COST.replace(nextPoint, cost + nextCost);
                    pq.add(new Edge(nextPoint, cost + nextCost));
                }
            }
        }
    }
    
    
    private double getDistance(Point point1, Point point2) {
        double dx = point1.x - point2.x;
        double dy = point1.y - point2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private double getWalkCost(Point point1, Point point2) {
        return getDistance(point1, point2) / SPEED;
    }
    
    private double getCanonCost(Point point1, Point point2) {
        double distance = getDistance(point1, point2);
        return Math.min(Math.abs(distance - 50.0) / SPEED + 2, distance / SPEED);
    }
    
    private class Edge implements Comparable<Edge> {
        Point point;
        double cost;
        
        public Edge(Point point, double cost) {
            this.point = point;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.cost, other.cost);
        }
    }
    
    private class Point {
        double x;
        double y;
        
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return String.format("(%s,%s)", x, y);
        }
    }
}