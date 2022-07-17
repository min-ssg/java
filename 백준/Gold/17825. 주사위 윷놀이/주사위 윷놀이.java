import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int[] commands = new int[10];

            for (int i = 0; i < 10; i++) {
                commands[i] = scan.nextInt();
            }

            Solution17825 solution17825 = new Solution17825();
            int answer = solution17825.solve(commands);
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

class Solution17825 {

    int[] MAP = {
            0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0, // 0 ~ 21 : 외곽
            10,13,16,19,25,30,35,40,0, // 22 ~ 30
            20,22,24,25,30,35,40,0, // 31 ~ 38
            30,28,27,26,25,30,35,40,0 // 39 ~ 47
    };

    private int max = Integer.MIN_VALUE;
    int[] selected = new int[10];
    int[] commands;

    public int solve(int[] commands) {
        this.commands = commands;
        recurrentFunction(0);
        return max;
    }

    private void recurrentFunction(int k) {
        if (k == 10) {
            max = Math.max(max, getScore());
            return;
        }

        for (int i = 0; i < 4; i++) {
            selected[k] = i;
            recurrentFunction(k + 1);
        }
    }

    private int getScore() {
        boolean[] visited = new boolean[MAP.length];
        int score = 0;
        int[] horses = new int[4];

        for (int i = 0; i < 10; i++) {
            int command = commands[i];
            int horse = selected[i];

            if (isFinish(horses[horse])) return 0;

            int next = nextPoint(horses[horse], command);
            if (isFinish(next)) {
                setVisited(visited, horses[horse], false);
                horses[horse] = next;
                continue;
            }

            if (visited[next]) return 0;
            setVisited(visited, horses[horse], false);
            setVisited(visited, next, true);

            horses[horse] = next;
            score += MAP[horses[horse]];
        }

        return score;
    }

    private void setVisited(boolean[] visited, int horse, boolean check) {
        if (horse == 20 || horse == 29 || horse == 37 || horse == 46) {
            visited[20] = check;
            visited[29] = check;
            visited[37] = check;
            visited[46] = check;
        } else if (horse == 26 || horse == 34 || horse == 43) {
            visited[26] = check;
            visited[34] = check;
            visited[43] = check;
        } else if (horse == 27 || horse == 35 || horse == 44) {
            visited[27] = check;
            visited[35] = check;
            visited[44] = check;
        } else if (horse == 28 || horse == 36 || horse == 45) {
            visited[28] = check;
            visited[36] = check;
            visited[45] = check;
        } else  {
            visited[horse] = check;
        }
    }

    private int nextPoint(int horse, int command) {
        int nextHorse = horse + command;

        if (horse < 21) {
            if (nextHorse >= 21) nextHorse = 21;
        } else if (horse < 30) {
            if (nextHorse > 30) nextHorse = 30;
        } else if (horse < 38) {
            if (nextHorse > 38) nextHorse = 38;
        } else if (horse < 47) {
            if (nextHorse >= 47) nextHorse = 47;
        }

        if (nextHorse == 5) return 22;
        if (nextHorse == 10) return 31;
        if (nextHorse == 15) return 39;
        return nextHorse;
    }

    private boolean isFinish(int horse) {
        return horse == 21 || horse == 30 || horse == 38 || horse == 47;
    }
}
