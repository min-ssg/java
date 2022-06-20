import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            DragonCurve[] dragonCurves = new DragonCurve[N];
            for (int i = 0; i < N; i++) {
                dragonCurves[i] = new DragonCurve(scan.nextInt(),scan.nextInt(), scan.nextInt(), scan.nextInt());
            }
            
            Solution15865 solution = new Solution15865();
            int answer = solution.solve(dragonCurves);
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

class Solution15865 {
    private boolean[][] MAP = new boolean[101][101];
    
    public int solve(DragonCurve[] dragonCurves) {
        for (DragonCurve dragonCurve : dragonCurves) {
            List<Point> points = new LinkedList<>();
            points.add(new Point(dragonCurve.x, dragonCurve.y));
            
            switch (dragonCurve.d) {
                case 0:
                    points.add(new Point(dragonCurve.x + 1, dragonCurve.y));
                    break;
                case 1:
                    points.add(new Point(dragonCurve.x , dragonCurve.y - 1));
                    break;
                case 2:
                    points.add(new Point(dragonCurve.x - 1, dragonCurve.y));
                    break;
                case 3:
                    points.add(new Point(dragonCurve.x, dragonCurve.y + 1));
                    break;
            }
            
            List<Point> dragonCurvePoints = createDragonCurve(points, 0, dragonCurve.g);
            
            for (Point point : dragonCurvePoints) {
                MAP[point.x][point.y] = true;
            }
        }
        
        return getRectangle();
    }
    
    private int getRectangle() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(MAP[i][j] && MAP[i][j + 1]
                && MAP[i + 1][j] && MAP[i + 1][j + 1]) count += 1;
            }
        }
        
        return count;
    }
    
    private List<Point> createDragonCurve(List<Point> points, int g, int G) {
        if (g == G) return points;
        
        int lastIndex = points.size() - 1;
        Point startPoint = points.get(lastIndex);
        List<Point> copyOfPoints = new LinkedList<>(points);
        for (int i = lastIndex - 1; i >= 0; i--) {
            Point point = copyOfPoints.get(i);
            points.add(new Point((point.y - startPoint.y) * -1 + startPoint.x, point.x - startPoint.x + startPoint.y));
        }
        return createDragonCurve(points, g + 1, G);
    }
    
    private class Point {
        int x;
        int y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

class DragonCurve{
    int x;
    int y;
    int d;
    int g;
    DragonCurve(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}