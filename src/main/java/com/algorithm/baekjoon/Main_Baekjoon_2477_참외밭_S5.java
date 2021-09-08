package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_2477_참외밭_S5 {

    private static int N;
    private static List<Point> pointList = new ArrayList<>();
    private static StringTokenizer stringTokenizer;
    public static void main(String[] args) {

        int maxX = 0;
        int maxY = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(bufferedReader.readLine());

            Point origin = createPoint(bufferedReader.readLine());
            Point maxXPoint = null;
            Point maxYPoint = null;
            pointList.add(origin);
            if (origin.getDirection().equals("1") || origin.getDirection().equals("2")) {
                maxX = origin.getValue();
                maxXPoint = origin;
            } else if (origin.getDirection().equals("3") || origin.getDirection().equals("4")) {
                maxY = origin.getValue();
                maxYPoint = origin;
            }


            for (int i = 1; i < 6; i++) {

                Point point  = createPoint(bufferedReader.readLine());
                pointList.get(i-1).setNextPoint(point);
                if (point.getDirection().equals("1") || point.getDirection().equals("2")) {
                    if  (maxX < point.getValue()) {
                        maxX = point.getValue();
                        maxXPoint = point;
                    }
                } else if (point.getDirection().equals("3") || point.getDirection().equals("4")) {
                    if (maxY < point.getValue()) {
                        maxY = point.getValue();
                        maxYPoint = point;
                    }
                }
                pointList.add(point);
            }

            pointList.get(pointList.size()-1).setNextPoint(origin);
            System.out.println(String.format("MAX_X, MAX_X_DIRECTION = (%s, %s)", maxX, maxXPoint.getDirection()));
            System.out.println(String.format("MAX_Y, MAX_Y_DIRECTION = (%s, %s)", maxY, maxYPoint.getDirection()));
            Point startPoint = getStartPoint(maxXPoint, maxYPoint);
            Point doubleNextPoint = doubleNext(startPoint);
            int size = maxX * maxY - doubleNextPoint.getValue() * doubleNextPoint.getNextPoint().getValue();
            System.out.println(calculate(N, size));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Point createPoint(String readLine) {
        stringTokenizer = new StringTokenizer(readLine);
        String direction = stringTokenizer.nextToken();
        int value = Integer.parseInt(stringTokenizer.nextToken());

        return new Point(direction, value);
    }

    private static Point doubleNext(Point startPoint) {
        return startPoint.getNextPoint().getNextPoint();
    }

    private static Point getStartPoint(Point maxXPoint, Point maxYPoint) {

        String xDirection = maxXPoint.getDirection();
        String yDirection = maxYPoint.getDirection();
        Point startPoint = null;
        if (xDirection.equals("1") && yDirection.equals("4")) {
            startPoint = maxYPoint;
        } else if (xDirection.equals("2") && yDirection.equals("4")) {
            startPoint = maxXPoint;
        } else if (xDirection.equals("2") && yDirection.equals("3")) {
            startPoint = maxYPoint;
        } else if (xDirection.equals("1") && yDirection.equals("3")) {
            startPoint = maxXPoint;
        }

        return startPoint;
    }

    private static int calculate(int number, int size) {
        return number * size;
    }

    private static class Point {

        private final String direction;
        private final int value;
        private Point nextPoint;

        public Point(String direction, int value) {
            this.direction = direction;
            this.value = value;
        }

        public String getDirection() {
            return direction;
        }

        public int getValue() {
            return value;
        }

        public Point getNextPoint() {
            return nextPoint;
        }

        public void setNextPoint(Point nextPoint) {
            this.nextPoint = nextPoint;
        }

        @Override
        public String toString() {
            return String.format("{direction=%s, value=%s}", direction, value);
        }
    }
}
