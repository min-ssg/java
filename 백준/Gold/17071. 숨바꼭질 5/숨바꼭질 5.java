import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt();

            Solution17071 solution17071 = new Solution17071();
            int answer = solution17071.solve(N,K);
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

class Solution17071 {

    HashMap<Integer, Integer> BROTHER_MAP = new HashMap<>();

    public int solve(int n, int k) {

        if (n == k) return 0;

        int key = k;
        for (int i = 0; i <= 1000; i++) {
            key += i;
            BROTHER_MAP.put(key, i);
        }

        return bfs(n,k);
    }

    private int bfs(int n, int k) {
        Queue<Position> queue = new LinkedList<>();
        int [][] distances = new int[500_001][2];
        for (int i = 1; i <= 500_000; i++) {
            Arrays.fill(distances[i], -1);
        }
        distances[n][0] = 0;
        queue.add(new Position(n, 0));

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int x = position.x;
            int time = position.time;

            int flag = 1 - time % 2;

            if (x - 1 >= 0 && distances[x - 1][flag] == -1) {
                distances[x - 1][flag] = time;
                queue.add(new Position(x - 1, time + 1));
            }

            if (x + 1 <= 500_000 && distances[x + 1][flag] == -1) {
                distances[x + 1][flag] = time;
                queue.add(new Position(x + 1, time + 1));
            }

            if (x * 2 <= 500_000 && distances[x * 2][flag] == -1) {
                distances[x * 2][flag] = time;
                queue.add(new Position(x * 2, time + 1));
            }
        }

        int t = 0;
        int flag = 0;
        int answer = -1;

        while (k <= 500_000) {
            if (distances[k][flag] != -1) {
                if (distances[k][flag] <= t) {
                    answer = t;
                    break;
                }
            }

            flag = 1 - flag;
            t += 1;
            k += t;
        }


        return answer;
    }

    private class Position {
        int x;
        int time;

        public Position(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}