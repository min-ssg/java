package com.algorithm.programmers.weekly;

import java.util.Arrays;

public class Programmers_위클리8주차_모음사전 {

    public int solution(int[][] sizes) {

        return Arrays.stream(sizes).reduce((a1, a2) ->
                new int[]{
                    Math.max(Math.max(a1[0], a1[1]), Math.max(a2[0], a2[1])), Math.max(Math.min(a1[0], a1[1]), Math.min(a2[0], a2[1]))
                }).map(ints -> ints[0] * ints[1]).get();
    }
}
