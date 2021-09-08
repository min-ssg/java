package com.algorithm.entity;

public class Point {

    private int x;
    private int y;
    private int index;
    private boolean visit;

    public Point(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, INDEX: %d", x, y, index);
    }
}
