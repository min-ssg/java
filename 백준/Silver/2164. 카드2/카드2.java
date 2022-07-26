import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                queue.add(i);
            }

            int lastCard = getLastCard(queue);
            System.out.println(lastCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getLastCard(Queue<Integer> queue) {

        while (queue.size() != 1) {
            queue.poll();
            int top = queue.poll();
            queue.add(top);
        }

        return queue.poll();
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
