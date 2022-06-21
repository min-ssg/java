import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int L = scan.nextInt();
            int R = scan.nextInt();

            int[][] MAP = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            Solution16234 solution16234 = new Solution16234();
            int answer = solution16234.solve(N,L,R,MAP);
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

class Solution16234 {

    private int N,L,R;
    private int[][] MAP;
    private boolean[][] visited;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int l, int r, int[][] map) {
        initialize(n,l,r,map);

        int days = 0;

        while (true) {
            List<List<Country>> countryList = new LinkedList<>();
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    List<Country> countries = new LinkedList<>();
                    dfs(new Country(i, j), countries);
                    int size = countries.size();
                    if (size == 1) continue;

                    countryList.add(countries);
                }
            }

            if (countryList.isEmpty()) break;

            for (List<Country> countries : countryList) {
                int sum = countries.stream().mapToInt(country -> MAP[country.x][country.y]).sum();
                int size = countries.size();
                countries.forEach(country -> {MAP[country.x][country.y] = sum / size;});
            }

//            showMap();
            days += 1;
        }

        return days;
    }

    private void showMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(MAP[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private void dfs(Country country, List<Country> countries) {

        countries.add(country);
        visited[country.x][country.y] = true;
        int value = MAP[country.x][country.y];

        for (int i = 0; i < 4; i++) {
            if (country.x + dx[i] < 0 || country.x + dx[i] >= N
            ||  country.y + dy[i] < 0 || country.y + dy[i] >= N) continue;
            if (visited[country.x + dx[i]][country.y + dy[i]]) continue;
            int difference = Math.abs(value - MAP[country.x + dx[i]][country.y + dy[i]]);
            if (difference < L || difference > R) continue;

            Country nextCountry = new Country(country.x + dx[i], country.y + dy[i]);
//            System.out.println("difference = " + difference + " : " + nextCountry);
            dfs(nextCountry, countries);
        }
    }

    private void initialize(int n, int l, int r, int[][] map) {
        this.N = n;
        this.L = l;
        this.R = r;
        this.MAP = map;
    }

    private class Country {
        int x;
        int y;

        Country(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}