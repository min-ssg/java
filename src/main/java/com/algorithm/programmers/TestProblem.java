package com.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProblem {

    private static int[] x = new int[4];
    private static int[] y = new int[4];

    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> mapList = new HashMap<>();
        mapList.get(0).toArray(new Integer[100]);
        List<List<Integer>> list = new ArrayList<>();

        list.stream().map(l -> l.stream().toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        list.get(0).toArray();

        String[][] array = list.stream()
                .map(l -> l.stream().toArray(String[]::new))
                .toArray(String[][]::new);
    }

    public static int[] solution(int[][] v) {
        int[] answer = new int[2];

        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();

        for (int[] v1 : v) {
            if (xMap.containsKey(v1[0])) {
                xMap.put(v1[0], xMap.get(v1[0]) + 1);
            } else {
                xMap.put(v1[0], 1);
            }

            if (yMap.containsKey(v1[1])) {
                yMap.put(v1[1], yMap.get(v1[1]) + 1);
            } else {
                yMap.put(v1[1], 1);
            }
        }

        for (Integer key : xMap.keySet()) {

            if (xMap.get(key) == 1) {
                answer[0] = key;
            }
        }

        for (Integer key : yMap.keySet()) {

            if (yMap.get(key) == 1) {
                answer[1] = key;
            }

        }

        return answer;
    }
}
