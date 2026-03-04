package com.leenjae.programmers;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparing(x -> x[1]));
        int answer = 1;
        int start = routes[0][0];
        int end = routes[0][1];
        int camPosition = end;
        for(int i = 1; i < routes.length; i++){
            start = routes[i][0];
            end = routes[i][1];
            if(start <= camPosition && camPosition <= end) {
                continue;
            } else {
                camPosition = end;
                answer++;
            }
        }

        return answer;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.solution(new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}})
        );
    }
}