import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            
            int N = scan.nextInt();
            long B = scan.nextLong();
            
            int[][] A = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    A[i][j] = scan.nextInt() % 1000;
                }
            }
            
            Solution10830 solution = new Solution10830();
            String  answer = solution.solve(N,B,A);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class FastReader {
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
        
        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}

class Solution10830 {
    StringBuilder sb = new StringBuilder();
    private int N;
    
    Map<Long, int[][]> map = new HashMap<>();
    
    public String solve(int n, long b, int[][] A) {
        this.N = n;
        
        map.put(1L,A);
        
        int[][] X = square(b);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(X[i][j]).append(' ');   
            }
            sb.append('\n');
        }
        return sb.toString();       
    }
    
    private int[][] square(long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        
        long a = n % 2 != 0 ? n / 2 + 1 : n / 2;
        long b = n / 2;
        int[][] x = product(square(a), square(b));
        map.put(n,x);
        return x;
    }
    
    private int[][] product(int[][] A, int[][] B) {
        int[][] X = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                X[i][j] = operate(i,j,A,B);
            }
        }
        
        return X;
    }
    
    private int operate(int x, int y, int[][] A, int[][] B) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[x][i] * B[i][y];
            sum %= 1000;
        }
        return sum;
    }
}