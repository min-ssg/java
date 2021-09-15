package com.algorithm.programmers.weekly;

import java.util.*;

public class Programmers_위클리7주차_입실퇴실 {

    public int[] solution(int[] enter, int[] leave) {

        int length = enter.length;
        int[] answer = new int[length];

        Set<Integer> room = new HashSet<>();
        int index = 0;
        for (int number : leave) { // 2, 1, 3, 4

            while (index < length){ // 1,4,2,3
                int in = enter[index];

                // 집합에서 삭제.
                if (room.contains(number)) {
                    room.remove(number);
                    for (int i : room) {
                        answer[i-1]++;
                        answer[number-1]++;
                    }
                    break;
                }

                room.add(in);
                index++;
            }

            if (room.contains(number)) {
                room.remove(number);
                for(int i : room) {
                    answer[i-1]++;
                    answer[number-1]++;
                }
            }
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }
}
