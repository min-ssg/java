package com.algorithm.programmers;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TossBankTest {

    private static int serverCount;
    private static int memory;

    public static void main(String[] args) {

        int servers = 2;
        boolean sticky = true;
        int[] requests = {1,2,2,3,4,1};


        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.println(decimalFormat.format(1100000));

        int[][] result = solution(servers, sticky, requests);
        for (int i = 0; i < result.length; i++) {
            System.out.println("answer = " + Arrays.toString(result[i]));
        }

    }

    public static int[][] solution(int servers, boolean sticky, int[] requests) {

        serverCount = servers - 1;

        int server = 0;

        List<List<Integer>> listOfList = new ArrayList<>();
        for (int i = 0; i < servers; i++) {
            listOfList.add(new ArrayList<>());
        }

        listOfList.get(server).add(requests[0]);
        memory = requests[0];

        for (int i = 1; i < requests.length; i++) {
            int request = requests[i];
            server = nextServer(server, request, sticky);
            listOfList.get(server).add(request);
            memory = request;
        }

        int[][] answer = new int[servers][];

        for (int i = 0; i < servers; i++) {
            int size = listOfList.get(i).size();
            answer[i] = new int[size];
            for (int j = 0; j < size; j++) {
                answer[i][j] = listOfList.get(i).get(j);
            }
        }

        return answer;
    }

    public static int nextServer(int server, int request, boolean sticky) {
        System.out.println("request = " + request);
        System.out.println("server = " + server);
        if (sticky && memory == request) {
            return server;
        }

        if (server == serverCount) {
            return 0;
        }

        return server + 1;
    }
}
