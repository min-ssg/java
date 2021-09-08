package com.algorithm.programmers.weekly;

import java.util.*;

/**
 * @Since 2021.09.07
 * @author Minseok
 * 복합정렬
 */
public class Programmers_위클리6주차_복서정렬하기 {

    public int[] solution(int[] weights, String[] head2head) {

        List<Boxer> list = new ArrayList<>();

        int length = weights.length;

        for (int i = 0; i < length; i++) {
            int moreWeightWins = 0;
            int wins = 0;
            int count = 0;
            int textLength = head2head[i].length();
            for (int j = 0; j < textLength; j++) {

                char winOrLose = head2head[i].charAt(j);

                if (winOrLose == 'N') continue;

                if (winOrLose == 'W') {
                    if (weights[i] < weights[j]) {
                        moreWeightWins += 1;
                    }
                    wins += 1;
                }

                count += 1;
            }
            System.out.println("wins = " + wins);
            System.out.println("count = " + count);
            list.add(new Boxer(i+1, weights[i], moreWeightWins,  getWinRate(wins, count)));
        }

        System.out.println(list.toString());

        Collections.sort(list, (boxer1, boxer2) -> {
           if (boxer1.getWinRate() < boxer2.getWinRate()) {
               return 1;
           }
           else if (boxer1.getWinRate() == boxer2.getWinRate()){
               if (boxer1.getMoreWeightWins() < boxer2.getMoreWeightWins()){
                   return 1;
               } else if (boxer1.getMoreWeightWins() == boxer2.getMoreWeightWins()) {
                   if (boxer1.getWeight() < boxer2.getWeight()) {
                       return 1;
                   } else if (boxer1.getWeight() == boxer2.getWeight()) {
                       return Integer.compare(boxer1.getIndex(), boxer2.getIndex());
                   }
               }
           }

           return -1;
        });

        return list.stream().mapToInt((boxer)->boxer.getIndex()).toArray();
    }

    public double getWinRate(int wins, int count) {

        if (count == 0) {
            return 0.0;
        }

        return (double) wins / count * 100;
    }
}
