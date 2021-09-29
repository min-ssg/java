package com.algorithm.programmers.weekly;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Programmers_위클리4주차_직업군추천하기 {

    private static final Map<String, Map<String, Integer>> tableMap = new HashMap<>();
    private static final Map<String, Integer> scoreMap = new HashMap<>();

    public String solution(String[] table, String[] languages, int[] preference) {

        init(table);
        int length = languages.length;
        for (Map.Entry<String, Map<String, Integer>> entry: tableMap.entrySet()) {
            int score = 0;
            String job = entry.getKey();
            for (int i = 0; i < length; i++) {
                score += entry.getValue().getOrDefault(languages[i], 0) * preference[i];
            }
            scoreMap.put(job, score);
        }

        System.out.println(scoreMap.toString());

        return scoreMap.entrySet().stream().max((e1, e2) -> {
            if (e1.getValue() < e2.getValue()) {
                return -1;
            } else if (e1.getValue() > e2.getValue()) {
                return 1;
            }

            return e1.getKey().compareTo(e2.getKey());
        }).get().getKey();
    }

    private void init(String[] table) {

        for (String domain: table) {
            String[] languages = domain.split(" ");

            int length = languages.length;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 1; i < length; i++) {
                map.put(languages[i], length - i);
            }

            tableMap.put(languages[0], map);
        }
    }
}
