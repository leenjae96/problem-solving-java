package com.leenjae.programmers.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 프로그래머스 - 해시 - level1
 * K번째 수
 *
 * @author leenjae96
 */
public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        List<Integer> answerList = new ArrayList<>();
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];
            int[] clone = array.clone();
            Arrays.sort(clone, i - 1, j);
            answerList.add(clone[i - 1 + k - 1]);
        }

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
