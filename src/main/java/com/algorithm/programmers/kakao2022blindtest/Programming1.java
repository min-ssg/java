package com.algorithm.programmers.kakao2022blindtest;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Programming1 {

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> reportMap = new HashMap<>();

        for (String id : id_list) {
            reportMap.put(id, 0);
            map.put(id, new ArrayList<>());
        }

        for (String id : report) {

            StringTokenizer stringTokenizer = new StringTokenizer(id, " ");
            String from = stringTokenizer.nextToken();
            String to = stringTokenizer.nextToken();

            if (!map.get(from).contains(to)) {
                map.get(from).add(to);
                reportMap.put(to, reportMap.get(to) + 1);
            }
        }
        
        System.out.println("Map : " + map);
        System.out.println("reportMap = " + reportMap);

        List<String> nameList = reportMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= k)
                .map(entry->entry.getKey())
                .collect(Collectors.toList());

        Predicate<String> matchName = name -> nameList.contains(name);
        int[] answer = Arrays.stream(id_list)
                .mapToInt(id->map.get(id).stream()
                        .filter(matchName)
                        .toArray().length
                )
                .toArray();


        System.out.println(answer);

        return answer;
    }
}
