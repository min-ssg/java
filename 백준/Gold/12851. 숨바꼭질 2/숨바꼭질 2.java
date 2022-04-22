import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            int N = scan.nextInt();
            int K = scan.nextInt();

            Solution12851 solution12851 = new Solution12851();
            int[] answer = solution12851.solve(N,K);
            sb.append(answer[0]).append('\n').append(answer[1]);
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

class Solution12851 {

    public int[] solve(int n, int k) {
        if (n >= k) return new int[]{n - k, 1};
        return bfs(n,k);
    }

    private int[] bfs(int n, int k) {

        int[] answer = new int[2];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);

        visited[n] = 0;
        queue.add(n);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int time = visited[x];

            if (visited[k] != -1 && visited[k] == time) break;

            if (x - 1 >= 0) {
                if (x - 1 == k) {
                    visited[k] = time + 1;
                    count += 1;
                } else if (visited[x - 1] == -1 || visited[x - 1] == time + 1) {
                    visited[x - 1] = time + 1;
                    queue.add(x - 1);
                }
            }

            if (x + 1 <= 100_000) {
                if (x + 1 == k) {
                    visited[k] = time + 1;
                    count += 1;
                } else if (visited[x + 1] == -1 || visited[x + 1] == time + 1){
                    visited[x + 1] = time + 1;
                    queue.add(x + 1);
                }
            }

            if (x * 2 <= 100_000) {
                if (x * 2 == k) {
                    visited[k] = time + 1;
                    count += 1;
                } else if (visited[x * 2] == -1 || visited[x * 2] == time + 1) {
                    visited[x * 2] = time + 1;
                    queue.add(x * 2);
                }
            }
        }

        answer[0] = visited[k];
        answer[1] = count;
        return answer;
    }
}