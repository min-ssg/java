package com.algorithm.programmers;

import java.util.Arrays;

public class Main_Programmers_2021_WEB_DEV_MATCHING_로또_최고_순위_최저_순위 {

    public static void main(String[] args) {
//        int[] lottos = {44, 1, 0, 0, 31, 25};
//        int[] win_nums = {31, 10, 45, 1, 6, 19};
        int[] lottos = {0, 0, 0, 0, 0, 0};
        int[] win_nums = {38, 19, 20, 40, 15, 25};

        int[] answer = solution(lottos, win_nums);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {

        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int correct = 0;
        int incorrect = 0;
        int NULL = 0;

        boolean[] lottoBool = new boolean[46];

        for (int num : win_nums) {
            lottoBool[num] = true;
        }

        for (int num : lottos) {
            if (num == 0) {
                NULL += 1;
            } else if (lottoBool[num]) {
                correct += 1;
            } else {
                incorrect += 1;
            }
        }

        answer[1] = rank[correct];
        answer[0] = rank[correct + NULL];
        System.out.println("correct = " + correct);
        System.out.println("incorrect = " + incorrect);
        System.out.println("NULL = " + NULL);

        return answer;
    }
}
