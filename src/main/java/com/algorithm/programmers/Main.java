package com.algorithm.programmers;

import java.util.Arrays;

public class Main {

    public static long[] binary = new long[50];

    public static void main(String[] args) {
        long answer = 0L;
        long answer1 = 0L;

        for (long number = 1_000_000_000_000_000L;number >=0 ;   number--) {

            if (number % 2 == 0) {
                answer = number + 1;
            } else {
                answer = getNextLongNumber(number);
            }
            answer1 =getNextMinLong(number);
            System.out.println(number + " : " + answer + " , " + answer1);
            if (answer != answer1) {
                break;
            }

        }
        System.out.println("answer = " + answer);
        System.out.println("answer1 = " + answer1);
        System.out.println("Finish!!!");
    }

    public static long getNextLongNumber(long number) {

        long result = number;
        long[] binaryArray = new long[50];
        int index = 0;

        while (number != 0) {
            long remain = number % 2;

            if (remain == 0) {
                break;
            }
            binaryArray[index++] = remain;

            number /= 2;
        }
        long left = calculateBinaryBit(index);
        long right = left / 2;
        return result + (left - right);
    }

    public static long getNextMinLong(long number) {
        long copy = number + 1;

        while (true) {
            long xor = number ^ copy;

            String bits = Long.toBinaryString(xor);

            if (isNextMinLongMember(bits)) {
                break;
            }

            copy++;
        }

        return copy;
    }

    public static long calculateBinaryBit(int index) {

        long product = 1L;
        for (int i = 1; i <= index; i++) {
            product *= 2;
        }

        return product;
    }

    public static boolean isNextMinLongMember(String bits) {

        int ret = 0;

        for (char bit : bits.toCharArray()) {
            if (bit == '1') {
                ret += 1;
            }
        }
        return ret <= 2;
    }
}