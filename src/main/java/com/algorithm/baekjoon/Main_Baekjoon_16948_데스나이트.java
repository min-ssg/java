package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_Baekjoon_16948_데스나이트 {

    public static final int[] rows = {-2, -2, 0, 0, 2, 2};
    public static final int[] cols = {-1, 1, -2, 2, -1, 1};
    public static List<Point> list = new ArrayList<>();
//    public static int N = 0;
    public static int N = 7;

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {

//            N = Integer.parseInt(bufferedReader.readLine());
//            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
//            System.out.println(stringTokenizer.countTokens());

            int[][] answer = new int[N][N];
//            Point start = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), 0);
            Point start = new Point(0, 0, 0);
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    boolean[][] visit = new boolean[N][N];
                    Point end = new Point(i, j, 0);
//                    System.out.println(end);
//                    System.out.println(bfs(start, end, visit));
                    answer[i][j] = bfs(start, end, visit);
                    list.clear();
                }

                System.out.println(Arrays.toString(answer[i]));
            }

//            Point end = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int bfs(Point start, Point end, boolean[][] visit) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) return 0;

        if (visitPoint(start, end, visit)) return 1;
//        System.out.println(list.toString());
        while (!list.isEmpty()) {
            Point currentPoint = list.get(0);
            list.remove(0);
            if (visitPoint(currentPoint, end, visit)) {
                return currentPoint.getVisitOrder() + 1;
            };
//            System.out.println(list.toString());
        }

        return -1;
    }

    public static boolean visitPoint(Point point, Point endPoint, boolean[][] visit) {

        int x = point.getX();
        int y = point.getY();
//        System.out.println(String.format("(%d, %d) is visited = %s", x, y, visit[x][y]));
//        if (visit[x][y]) return false;
//        visit[x][y] = true;
        int currentVisitOrder = point.getVisitOrder();

        for (int i = 0; i < rows.length; i++) {
            if ( 0 <= x + rows[i] && x + rows[i] < N && 0 <= y + cols[i] && y + cols[i] < N) {

                if (visit[x + rows[i]][y + cols[i]]) continue;

                if (endPoint.getX() == x + rows[i] && endPoint.getY() == y + cols[i]) {
//                    System.out.println("Arrivate at " + endPoint);
                    return true;
                }
                list.add(new Point(x + rows[i], y + cols[i], point.getVisitOrder()+1));
                visit[x + rows[i]][y + cols[i]] = true;
            }
        }

        return false;
    }
}

class Point {
    private int x;
    private int y;
    private int visitOrder;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int visitOrder) {
        this.x = x;
        this.y = y;
        this.visitOrder = visitOrder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVisitOrder(int order) {
        this.visitOrder = order;
    }

    public int getVisitOrder() {
        return visitOrder;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
