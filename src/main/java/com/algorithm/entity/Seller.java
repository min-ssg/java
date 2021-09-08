package com.algorithm.entity;

import java.util.Optional;

public class Seller {

    private String name;
    private int money;
    private Seller parent;

    public Seller(String name, Seller parent) {
        this.name = name;
        this.parent = parent;
    }

    public void setMoney(int money) {
        this.money = money * 10 / 100;
    }

    public void addMoney(int add) {

        if (parent != null) {
            this.money += add - (add * 10 / 100);
        } else {
            this.money += add;
        }
    };

    public int getMoney() {
        return money;
    }

    public String getName() {
        return Optional.ofNullable(name).orElse("-");
    }

    public Seller getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null ? true : false;
    }

    @Override
    public String toString() {
        return String.format("My Parent is %s", parent.getName());
    }
}
