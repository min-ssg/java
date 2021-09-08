package com.algorithm.programmers.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Programmers_위클리2주차_상호평가 {

    public String solution(int[][] scores) {

        List<Integer> averageScores = new ArrayList<>();
        int length = scores.length;

        for (int j = 0; j < length; j++) {
            int sum = 0;
            int count = 0;
            int max = 0;
            int min = 100;
            int maxCount = 0;
            int minCount = 0;
            int maxIndex = -1;
            int minIndex = -1;

            for (int i = 0; i < length; i++) {
                if (scores[i][j] > max) {
                    max = scores[i][j];
                    maxIndex = i;
                }

                if (scores[i][j] < min) {
                    min = scores[i][j];
                    minIndex = i;
                }

                sum += scores[i][j];
                count += 1;
            }

            for (int i = 0; i < length; i++) {
                if (max == scores[i][j]) {
                    maxCount += 1;
                }

                if (min == scores[i][j]) {
                    minCount += 1;
                }
            }

            if (max != min) {
                if (maxCount == 1 && j == maxIndex) {
                    sum -= max;
                    count -= 1;
                } else if (minCount == 1 && j == minIndex) {
                    sum -= min;
                    count -= 1;
                }
            }

            averageScores.add(sum / count);
        }

        String answer = averageScores.stream()
                                     .map(score -> {
                                        if (score >= 90)
                                            return "A";
                                        else if (score >= 80)
                                            return "B";
                                        else if (score >= 70)
                                            return "C";
                                        else if (score >= 50)
                                            return "D";
                                        return "F";
                                     }).collect(Collectors.joining());
        System.out.println("scores = " + averageScores);
        System.out.println("answer = " + answer);

        return answer;
    }
}
