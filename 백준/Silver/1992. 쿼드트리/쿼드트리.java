import java.awt.print.Paper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            Solution1992 solution1992 = new Solution1992();
            String answer = solution1992.solve(N,map);
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

class Solution1992 {

    StringBuilder sb = new StringBuilder();

    private int[][] map;

    public String solve(int n, int[][] map) {

        this.map = map;

        Pixel start = new Pixel(0,0,n);
        recurrentFunction(start);
        return sb.toString();
    }

    private void recurrentFunction(Pixel pixel) {
        int x = pixel.x;
        int y = pixel.y;

        if (isDifferent(pixel)) {

            int half = pixel.size / 2;

            sb.append('(');
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    recurrentFunction(new Pixel(x + half * i, y + half * j, half));
                }
            }
            sb.append(')');
            return;
        }

        sb.append(map[x][y]);
    }

    private void divide(Pixel pixel) {

    }

    private boolean isDifferent(Pixel pixel) {
        int x = pixel.x;
        int y = pixel.y;
        int size = pixel.size;
        int first = map[x][y];
        
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (first != map[i][j]) return true;
            }
        }
        
        return false;
    }

    private class Pixel {
        int x;
        int y;
        int size;

        public Pixel(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
}