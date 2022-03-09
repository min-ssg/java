package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_2251_물통 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int A = scan.nextInt();
            int B = scan.nextInt();
            int C = scan.nextInt();

            Solution2251 solution2251 = new Solution2251();
            solution2251.solve(A,B,C);
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

class Solution2251 {

    private int[] LIMIT = new int[3];
    private boolean[][][] visited;

//    List<State> status = new ArrayList<>();
    List<Integer> cStatus = new ArrayList<>();

    public void solve(int a, int b, int c) {
        LIMIT[0] = a;
        LIMIT[1] = b;
        LIMIT[2] = c;

        visited = new boolean[a+1][b+1][c+1];
        bfs(new State(new int[]{0,0,c}));
    }

    private void bfs(State start) {

        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        visited[start.volume[0]][start.volume[1]][start.volume[2]] = true;
        cStatus.add(start.volume[2]);
        while (!queue.isEmpty()) {
            State state = queue.poll();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;

                    State temp = null;
                    if ((temp = move(i,j,state)) != null) {
                        queue.add(temp);
                    }
                }
            }

            System.out.println(queue.toString());
        }

        Collections.sort(cStatus);

        System.out.println(cStatus.toString());
    }

    private State move(int from, int to, State state) {

        if (state.volume[from] == 0) {
            return null;
        }

        int[] newState = new int[3];
        for (int i = 0; i < 3; i++) {
            if (i == from || i == to) continue;
            newState[i] = state.volume[i];
        }
        if (LIMIT[to] < state.volume[from] + state.volume[to]) {
            newState[from] = state.volume[from] + state.volume[to] - LIMIT[to];
            newState[to] = LIMIT[to];
        } else {
            newState[from] = 0;
            newState[to] = state.volume[from] + state.volume[to];
        }

        if (visited[newState[0]][newState[1]][newState[2]]) return null;

        State nextState = new State(newState);
        if (nextState.volume[0] == 0) {
            cStatus.add(nextState.volume[2]);
        }
        visited[newState[0]][newState[1]][newState[2]] = true;
        return nextState;
    }

    private class State {
        int[] volume;

        public State(int[] volume) {
            this.volume = volume;
        }

        @Override
        public String toString() {
            return Arrays.toString(volume);
        }
    }
}
