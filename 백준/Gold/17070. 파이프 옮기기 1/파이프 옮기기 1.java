import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[][] MAP = new int[N + 1][N + 1];
            
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }
            
            Solution17070 solution = new Solution17070();
            int answer = solution.solve(N,MAP);
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

class Solution17070 {
    private int N;
    int[][] MAP;
    public int solve(int n, int[][] map) {
        this.N = n;
        this.MAP = map;
        
        return dfs(new Pipe(new Point(1,2),0));
    }
    
    public int dfs(Pipe pipe) {
        if (pipe.head.x == N && pipe.head.y == N) {
            return 1;
        }
        
        int type = pipe.type;
        int count = 0;
        int hx = pipe.head.x;
        int hy = pipe.head.y;
        
        switch (type) {
            case 0:
                if (hy + 1 <= N && MAP[hx][hy + 1] == 0) {
                    count += dfs(new Pipe(new Point(hx, hy + 1), 0));
                }
                
                if (hx + 1 <= N && hy + 1 <= N
                &&  MAP[hx][hy + 1] == 0
                &&  MAP[hx + 1][hy + 1] == 0
                &&  MAP[hx + 1][hy] == 0) {
                    count += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
            case 1:
                if (hx + 1 <= N && MAP[hx + 1][hy] == 0) {
                    count += dfs(new Pipe(new Point(hx + 1, hy), 1));
                }
                
                if (hx + 1 <= N && hy + 1 <= N
                &&  MAP[hx][hy + 1] == 0
                &&  MAP[hx + 1][hy + 1] == 0
                &&  MAP[hx + 1][hy] == 0) {
                    count += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
            case 2:
                
                if (hy + 1 <= N && MAP[hx][hy + 1] == 0) {
                    count += dfs(new Pipe(new Point(hx, hy + 1), 0));
                }
                
                if (hx + 1 <= N && MAP[hx + 1][hy] == 0) {
                    count += dfs(new Pipe(new Point(hx + 1, hy), 1));
                }
                
                if (hx + 1 <= N && hy + 1 <= N
                &&  MAP[hx][hy + 1] == 0
                &&  MAP[hx + 1][hy + 1] == 0
                &&  MAP[hx + 1][hy] == 0) {
                    count += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
        }
        
        return count;
    }
    
    private class Pipe {
        Point head;
        int type;
        public Pipe(Point head, int type) {
            this.head = head;
            this.type = type;
        }
    }
    
    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}