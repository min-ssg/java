import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int r = scan.nextInt();
            int c = scan.nextInt();
            int k = scan.nextInt();

            int[][] A = new int[3 + 1][3 + 1];

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    A[i][j] = scan.nextInt();
                }
            }

            Solution17140 solution17140 = new Solution17140();
            int answer = solution17140.solve(r,c,k,A);
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

class Solution17140 {

    private int R,C,K, rowMax, colMax;
    private int[][] A;

    public int solve(int r, int c, int k, int[][] a) {
        initialize(r,c,k,a);
        return process();
    }

    private int process() {
        int answer = 0;

        while (A[R][C] != K) {

            if (answer == 100) {
                answer = -1;
                break;
            }

            if (rowMax >= colMax) {
                rowSorting();
            } else {
                colSorting();
            }

//            System.out.println("row max = " + rowMax);
//            System.out.println("col max = " + colMax);
//            currentPrint();
            answer += 1;
        }

        return answer;
    }

    private void currentPrint() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= rowMax; i++) {
            for (int j = 1; j <= colMax; j++) {
                sb.append(A[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    private void rowSorting() {

        Map<Integer,Integer> map = new HashMap<>();
        int rowMax = this.rowMax;
        int colMax = this.colMax;
        for (int i = 1; i <= rowMax; i++) {
            for (int j = 1; j <= colMax; j++) {
                if (A[i][j] == 0) continue;

                if (map.containsKey(A[i][j])) {
                    map.replace(A[i][j], map.get(A[i][j]) + 1);
                } else {
                    map.put(A[i][j], 1);
                }

                A[i][j] = 0;
            }

            int index = 1;
            List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
            map.clear();

            Collections.sort(entries, (e1, e2) -> {
                if (e1.getValue() == e2.getValue()) {
                    return Integer.compare(e1.getKey(), e2.getKey());
                }
                return Integer.compare(e1.getValue(), e2.getValue());
            });

//            System.out.println("row " + i + " : " + entries);

            for (Map.Entry<Integer,Integer> entry : entries) {
                if (index > 100) break;

                A[i][index++] = entry.getKey();
                A[i][index++] = entry.getValue();
            }

            if (colMax < index - 1) colMax = index - 1;
        }

        this.colMax = colMax;
    }

    private void colSorting() {
        Map<Integer,Integer> map = new HashMap<>();
        int rowMax = this.rowMax;
        int colMax = this.colMax;
        for (int j = 1; j <= colMax; j++) {
            for (int i = 1; i <= rowMax; i++) {
                if (A[i][j] == 0) continue;

                if (map.containsKey(A[i][j])) {
                    map.replace(A[i][j], map.get(A[i][j]) + 1);
                } else {
                    map.put(A[i][j], 1);
                }

                A[i][j] = 0;
            }
            int index = 1;
            List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
            map.clear();
//            System.out.println("col " + j + " : "  + entries);
            Collections.sort(entries, (e1, e2) -> {
                if (e1.getValue() == e2.getValue()) {
                    return Integer.compare(e1.getKey(), e2.getKey());
                }
                return Integer.compare(e1.getValue(), e2.getValue());
            });

            for (Map.Entry<Integer,Integer> entry : entries) {
                if (index > 100) break;

                A[index++][j] = entry.getKey();
                A[index++][j] = entry.getValue();
            }

            if (rowMax < index - 1) rowMax = index - 1;
        }

        this.rowMax = rowMax;
    }

    private void initialize(int r, int c, int k, int[][] a) {
        this.R = r;
        this.C = c;
        this.K = k;

        this.rowMax = 3;
        this.colMax = 3;

        this.A = new int[100 + 1][100 + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                A[i][j] = a[i][j];

            }
        }
    }
}

