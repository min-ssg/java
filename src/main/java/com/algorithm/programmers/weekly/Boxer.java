package com.algorithm.programmers.weekly;

public class Boxer {

    private final int index;
    private final int weight;
    private final int moreWeightWins;
    private final double winRate;

    public Boxer(int index, int weight, int moreWeightWins, double winRate) {
        this.index = index;
        this.weight = weight;
        this.moreWeightWins = moreWeightWins;
        this.winRate = winRate;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public int getMoreWeightWins() {
        return moreWeightWins;
    }

    public double getWinRate() {
        return winRate;
    }

    @Override
    public String toString() {
        return    "INDEX: " + index
                + "WEIGHT: " + weight
                + "MOREWEIGHTWINS: " + moreWeightWins
                + "WINRATE: "  + winRate;
    }
}
