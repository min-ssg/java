import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            
            int N = scan.nextInt();
            Set<Integer> set = new HashSet<>();
            
            for (int i = 0; i < N; i++) {
                int A = scan.nextInt();
                set.add(A);
            }
            
            TreeSet<Integer> treeSet = new TreeSet<>();
            treeSet.addAll(set);
            StringBuilder sb = new StringBuilder();
            for (int a : treeSet) {
                sb.append(a).append(' ');
            }
            
            System.out.println(sb.toString());
            
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