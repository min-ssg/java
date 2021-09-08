package com.algorithm.entity;

public class Graph {

    private Vertex root;
    private int numberOfLeftChildren;
    private int numberOfRightChildren;

    public Graph () {
        this.root = null;
        this.numberOfLeftChildren = 0;
        this.numberOfRightChildren = 0;
    }

    public void add(Vertex v) {

        if (root == null ) {
            root = v;
            return;
        }

        if (root.getValue() >= v.getValue()) {
            addLeft(v);
        } else {
            addRight(v);
        }

    }

    public void addLeft(Vertex v) {

        Vertex left = root;

        while (left != null) {
            if (left.getLeftChild() == null) break;
            if (left.getLeftChild().getValue() < v.getValue()) {
                break;
            }

            left = left.getLeftChild();
        }

        v.setLeftChild(left.getLeftChild());
        left.setLeftChild(v);
        numberOfLeftChildren++;
        balancing();
    }

    public void addRight(Vertex v) {

        Vertex right = root;

        while (right != null) {
            if (right.getRightChild() == null) break;
            if (right.getRightChild().getValue() >= v.getValue()) {
                break;
            }

            right = right.getRightChild();
        }

        v.setRightChild(right.getRightChild());
        right.setRightChild(v);
        numberOfRightChildren++;
        balancing();
    }

    public Vertex getRoot() {
        return root;
    }

    public void balancing() {
        int diff = numberOfLeftChildren - numberOfRightChildren;

        if (diff == 0) return;

        if (diff > 0) {
            Vertex left = root.getLeftChild();
            root.setLeftChild(null);
            left.setRightChild(root);
            root = left;

            numberOfLeftChildren--;
            numberOfRightChildren++;
        } else if (diff < -1){
            Vertex right = root.getRightChild();
            root.setRightChild(null);
            right.setLeftChild(root);
            root = right;

            numberOfLeftChildren++;
            numberOfRightChildren--;
        }


    }

    public void show() {
        prefix(root);
        System.out.println();
    }

    public void prefix(Vertex v) {

        if (v.getLeftChild() != null) {
            prefix(v.getLeftChild());
        }
        System.out.print(v.getValue() + " ");
        if (v.getRightChild() != null) {
            prefix(v.getRightChild());
        }
    }
}
