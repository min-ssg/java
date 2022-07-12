import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int oil = scan.nextInt();

            int[][] map = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Taxi taxi = new Taxi(scan.nextInt(), scan.nextInt(), oil);
            Set<Customer> customers = new HashSet<>();
            for (int i = 1; i <= M; i++) {
                customers.add(new Customer(i, scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
            }

            Solution19238 solution = new Solution19238();
            int answer = solution.solve(map, taxi, customers);
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
class Taxi {
    int x;
    int y;
    int oil;
    int useOil;

    public Taxi(int x, int y, int oil) {
        this.x = x;
        this.y = y;
        this.oil = oil;
    }

    public void gotoCustomer(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.oil -= distance;
    }

    public void gotoDestination(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.oil -= distance;
        this.useOil = distance;
    }

    public void addOil() {
        this.oil += useOil * 2;
        this.useOil = 0;
    }
}

class Customer {
    int number;
    int x;
    int y;
    int dx;
    int dy;

    public Customer(int number, int x, int y, int dx, int dy) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "number=" + number +
                ", x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}

class State {
    int x;
    int y;
    int distance;
    int currentOil;

    public State(int x, int y, int distance, int currentOil) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.currentOil = currentOil;
    }

    @Override
    public String toString() {
        return "State{" +
                "x=" + x +
                ", y=" + y +
                ", distance=" + distance +
                ", currentOil=" + currentOil +
                '}';
    }
}
class Solution19238 {
    private int N;
    private int[][] MAP;
    Taxi taxi;
    Set<Customer> customers;

    private int[] nx = {-1,0,1,0};
    private int[] ny = {0,1,0,-1};

    public int solve(int[][] map, Taxi taxi, Set<Customer> customers) {
        initialize(map, taxi, customers);
        return process();
    }

    private void initialize(int[][] map, Taxi taxi, Set<Customer> customers) {
        this.MAP = map;
        this.taxi = taxi;
        this.customers = customers;
        this.N = map.length - 1;
    }

    private int process() {
        while (!customers.isEmpty()) {
            Customer customer = findCustomer();
            if (customer == null) return -1;

            // distance of destination
            int distance = bfs(customer.dx, customer.dy);
            if (distance == -1) return -1; // oil empty
            taxi.gotoDestination(customer.dx, customer.dy, distance);
            taxi.addOil();
            customers.remove(customer);
        }

        return taxi.oil;
    }

    // find customer that minimize distance;
    private Customer findCustomer() {
        Customer shortCustomer = null;
        int minDistance = Integer.MAX_VALUE;

        for (Customer customer : customers) {
            int distance = bfs(customer.x, customer.y);
            if (distance == -1) continue;

            if (minDistance > distance) {
                shortCustomer = customer;
                minDistance = distance;
            } else if (minDistance == distance) {
                if (compareXY(shortCustomer, customer) > 0) {
                    shortCustomer = customer;
                }
            }
        }
        if (shortCustomer != null) {
            taxi.gotoCustomer(shortCustomer.x, shortCustomer.y, minDistance);
        }

        return shortCustomer;
    }

    private int compareXY(Customer shortCustomer, Customer customer) {
        if (shortCustomer.x == customer.x) {
            return Integer.compare(shortCustomer.y, customer.y);
        }
        return Integer.compare(shortCustomer.x, customer.x);
    }

    private int bfs(int dx, int dy) {
        Queue<State> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new State(taxi.x, taxi.y, 0, taxi.oil));
        visited[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()) {
            State point = queue.poll();
            int x = point.x;
            int y = point.y;
            int distance = point.distance;
            int currentOil = point.currentOil;
            if (x == dx && y == dy) {
                return distance;
            }

            if (currentOil == 0) continue;

            for (int i = 0; i < 4; i++) {
                if (x + nx[i] < 1 || x + nx[i] > N
                        ||  y + ny[i] < 1 || y + ny[i] > N) continue;
                if (MAP[x + nx[i]][y + ny[i]] == 1 || visited[x + nx[i]][y + ny[i]]) continue;
                visited[x + nx[i]][y + ny[i]] = true;
                queue.add(new State(x + nx[i], y + ny[i], distance + 1, currentOil - 1));
            }
        }

        return -1;
    }
}