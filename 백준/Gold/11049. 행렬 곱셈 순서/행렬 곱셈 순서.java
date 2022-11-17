import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[][] A = new int[N][N];
            for (int i = 0; i < N; i++) {
                A[i][0] = scan.nextInt();
                A[i][1] = scan.nextInt();
            }
            
            Solution11049 solution = new Solution11049();
            int answer = solution.solve(N,A);
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
    }
}

class Solution11049 {
    private int N;
    private int[][] A;
    
    public int solve(int n, int[][] a) {
        this.N = n;
        this.A = a;
        
        Matrix[][] matrixes = new Matrix[n][n];
        
        for (int i = 0; i < n; i++) {
            int row = a[i][0];
            int col = a[i][1];
            matrixes[i][i] = new Matrix(row,col,0);
        }
        
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                Matrix m1 = null;
                Matrix m2 = null;
                int value = Integer.MAX_VALUE;
                for (int k = i; k < i + j; k++) {
                    m1 = matrixes[i][k];
                    m2 = matrixes[k + 1][i + j];
                    value = Math.min(value, Math.min(m1.row * m1.col * m2.col + m1.value + m2.value, m1.row * m2.row * m2.col + m2.value + m1.value));
                }
                matrixes[i][i + j] = new Matrix(m1.row, m2.col, value);
            }
        }
        
        return matrixes[0][n - 1].value;
    }
    
    private class Matrix {
        int row;
        int col;
        int value;
        
        public Matrix(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}