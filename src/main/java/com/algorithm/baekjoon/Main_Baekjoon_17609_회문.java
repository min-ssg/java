package com.algorithm.baekjoon;

import java.io.*;

// 투 포인터
public class Main_Baekjoon_17609_회문 {

    // 회문 (Palindrome)이란 "이효리" -> 거꾸로 해도 "이효리" 가 되는 단어를 말함.
    public static int N;
    public static int[] answers;

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));) {

            N = Integer.parseInt(bufferedReader.readLine());
            answers = new int[N];
            for (int i = 0; i < N; i++) {
                answers[i] = isPalindromeReturnInt(bufferedReader.readLine());
            }
            
            for (int answer : answers) {
                bufferedWriter.write(String.format("%d\n", answer));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int isPalindromeReturnInt(String sentence) {
        int count = 0;
        int left = 0;
        int right = sentence.length() - 1;

        while (left < right) {
            if (sentence.charAt(left) == sentence.charAt(right)) {
                left++;
                right--;
                continue;
            }

            if (sentence.charAt(left) != sentence.charAt(right-1) && sentence.charAt(left+1) != sentence.charAt(right)) {
                count = 2;
                break;
            }

            if (subPalindrome(sentence, left, right-1) || subPalindrome(sentence, left-1, right)) {
                count = 1;
            } else {
                count = 2;
            }

            break;
        }
        return count;
    }

    private static boolean subPalindrome(String sentence, int left, int right) {

        boolean isPalindrome = true;

        while (left < right) {
            if (sentence.charAt(left) == sentence.charAt(right)) {
                left++;
                right--;
                continue;
            }

            isPalindrome = false;
            break;
        }

        return isPalindrome;
    }
}