package com.algorithm.entity;

import java.util.List;

public class Vertex {

    private String index;
    private List<Vertex> children;
    private int value;
    private Vertex leftChild;
    private Vertex rightChild;

    public Vertex(String index) {
        this.index = index;
    }
    public Vertex(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public String getIndex() {
        return index;
    }

    public Vertex getChild(int index) {
        return children.get(index);
    }

    public void addChild(Vertex child) {
        children.add(child);
    }

    public int size() {
        return children.size();
    }

    public int getValue() {
        return value;
    }

    public void setRightChild(Vertex rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Vertex leftChild) {
        this.leftChild = leftChild;
    }

    public Vertex getLeftChild() {
        return leftChild;
    }

    public Vertex getRightChild() {
        return rightChild;
    }
}
