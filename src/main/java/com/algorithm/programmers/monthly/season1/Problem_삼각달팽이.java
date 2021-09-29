package com.algorithm.programmers.monthly.season1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Problem_삼각달팽이 {

    private static int[][] triangle;
    private static int x = 0;
    private static int y = 0;
    private static int value = 0;

    public int[] solution(int n) {
        init(n);
        int size = n;
        int sum = 0;
        int downIndex = 0;
        int rightIndex = n-1;
        int upIndex = n-2;
        while (size >= 0) {
            goDown(size); // 6
            goRight(size-1); // 5
            goUp(size-2); // 4

            size -= 3;
        }

        return Arrays.stream(triangle).flatMapToInt(list -> Arrays.stream(list).filter(i -> i >= 1)).toArray();
    }

    private void init(int n) {
        triangle = new int[n][n];
    }

    public void goDown(int size) {
        for (int i = 0; i < size; i++) {
            triangle[x++][y] = value + 1;
            value += 1;
        }
        x--;
        y++;
        printXY();
    }

    public void goRight(int size) {
        for (int i = 0; i < size; i++) {
            triangle[x][y++] = value + 1;
            value += 1;
        }
        x--;
        y -= 2;
        printXY();
    }

    public void goUp(int size) {
        for (int i = 0; i < size; i++) {
            triangle[x--][y--] = value+1;
            value += 1;
        }
        x+=2;
        y++;
        printXY();
    }

    private void printXY() {
        System.out.println("x: " + x + ", y:" + y);
    }
}
