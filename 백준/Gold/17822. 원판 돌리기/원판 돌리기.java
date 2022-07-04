import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int T = scan.nextInt();

            Map<Integer, LinkedList<Integer>> circle = new HashMap<>();
            List<Command> commands = new ArrayList<>();
            
            for (int i = 0; i < N; i++) {
                circle.put(i + 1, new LinkedList<>());
                for (int j= 0; j < M; j++) {
                    circle.get(i + 1).add(scan.nextInt());
                }
            }
            
            for (int i = 0; i < T; i++) {
                commands.add(new Command(scan.nextInt(), scan.nextInt(), scan.nextInt()));
            }

            Solution17822 solution17822 = new Solution17822();
            int answer = solution17822.solve(N, M, circle, commands);
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

class Solution17822 {

    private int N, M;
    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int m, Map<Integer, LinkedList<Integer>> circle, List<Command> commands) {
        this.N = n;
        this.M = m;
//        System.out.println("commands = " + commands);
        for (Command command : commands) {
//            System.out.println("COMMAND = " + command);
            execute(circle, command);
//            printCircle(circle);
            removeAdjacent(circle);
        }

//        printCircle(circle);

        return getAddCircleValue(circle);
    }

    private void execute(Map<Integer, LinkedList<Integer>> circle, Command command) {
        for (int i = command.x; i <= N; i += command.x) {
//            System.out.println("xi = " + i);
            LinkedList<Integer> values = circle.get(i);
            rotate(values, command.d, command.k);
        }
    }

    private void rotate(List<Integer> values, int d, int k) {
        for (int i = 0; i < k; i++) {
            if (d == 0) {
                int last = values.remove(values.size() - 1);
                values.add(0, last);
            } else {
                int first = values.remove(0);
                values.add(first);
            }
        }

//        System.out.println("rotated = " + values);
    }

    private void removeAdjacent(Map<Integer, LinkedList<Integer>> circle) {

        boolean hasRemove = false;

        for (Map.Entry<Integer, LinkedList<Integer>> entry : circle.entrySet()) {
            int key = entry.getKey();
//            System.out.println("entry values = " + entry.getValue());
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (entry.getValue().get(i) == 0) continue;
                hasRemove = bfs(key, i, entry.getValue().get(i), circle, hasRemove);
            }
//            printCircle(circle);
        }

        if (!hasRemove) {
//            System.out.println("Start average calculate!");
            calculateAverage(circle);
        }
    }

    private void calculateAverage(Map<Integer, LinkedList<Integer>> circle) {
        int count = 0;
        int sum = 0;
        for (Map.Entry<Integer, LinkedList<Integer>> entry : circle.entrySet()) {
            for (int value : entry.getValue()) {
                if (value == 0) continue;
                count+=1;
                sum += value;
            }
        }
        double average = sum / (double) count;
//        System.out.println("average = " + average);
        for (Map.Entry<Integer, LinkedList<Integer>> entry : circle.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                int value = entry.getValue().get(i);
                if (value == 0) continue;

                if (value > average) {
                    entry.getValue().set(i, value - 1);
                } else if (value < average) {
                    entry.getValue().set(i, value + 1);
                }
            }
        }
    }

    private boolean bfs(int i, int j, int current, Map<Integer, LinkedList<Integer>> circle, boolean hasRemove) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j,current});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];

//            System.out.println("x = " + x + ", y = " + y + ", current = " + current);
            for (int n = 0; n < 4; n++) {
                int nx = x + dx[n];
                int ny = y + dy[n];
                if (nx < 1 || nx > N) continue;

                if (ny < 0) {
                    ny = M - 1;
                } else if (ny >= M) {
                    ny = 0;
                }

                if (circle.get(nx).get(ny) == 0) continue;
                if (circle.get(nx).get(ny) != current) continue;

//                System.out.println("nx = " + nx + ", ny = " + ny + ", current = " + current);
                hasRemove = true;
                queue.add(new int[]{nx,ny,current});
                circle.get(x).set(y,0);
                circle.get(nx).set(ny, 0);
            }
        }

        return hasRemove;
    }

    private void printCircle(Map<Integer,LinkedList<Integer>> circle) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(i).append(':').append(circle.get(i)).append('\n');
        }

        System.out.println(sb.toString());
    }

    public int getAddCircleValue(Map<Integer, LinkedList<Integer>> circle) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += circle.get(i).stream().reduce(Integer::sum).get();
        }
        return sum;
    }
}

class Command {
    int x; // 번호.
    int d; // 방향, 0 : 시계, 1 : 반시계
    int k; // 칸수
    
    public Command(int x, int d, int k) {
        this.x = x;
        this.d = d;
        this.k = k;
    }

    @Override
    public String toString() {
        return "Command{" +
                "x=" + x +
                ", d=" + d +
                ", k=" + k +
                '}';
    }
}