import java.io.*;
import java.util.*;

public class Main {
    
    static int[][] dp;
    static List<Integer>[] graph;
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            
            int N = scan.nextInt();
            graph = new ArrayList[N + 1];
            dp = new int[N + 1][2];
            
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < N - 1; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                
                graph[x].add(y);
                graph[y].add(x);
            }
            
            dfs(1);
            System.out.println(Math.min(dp[1][0], dp[1][1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void dfs(int x) {
        dp[x][0] = 1;
        
        for (int child : graph[x]) {
            if (dp[child][0] != 0) continue;
            
            dfs(child);
            dp[x][1] += dp[child][0];
            dp[x][0] += Math.min(dp[child][0], dp[child][1]);
        }
    }
    
    static class FastReader {
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
    }
}