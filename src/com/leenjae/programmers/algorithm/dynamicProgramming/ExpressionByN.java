package com.leenjae.programmers.algorithm.dynamicProgramming;


import java.util.HashMap;
import java.util.HashSet;

/**
 * 프로그래머스 - 동적계획법 - level3
 * N으로 표현
 *
 * @author leenjae96
 * @date 25.11.29
 */
public class ExpressionByN {
    public int solution(int N, int number) {
        if (N == number) return 1;

        int answer;

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(N);
        map.put(1, set1);
        HashSet<Integer> set2 = new HashSet<>();
        set2.add(makeTheNumber(2, N));
        set2.add(2 * N);
        set2.add(0);
        set2.add(N * N);
        set2.add(1);
        if (set2.contains(number)) return 2;
        map.put(2, set2);

        for (answer = 3; answer < 9; answer++) {
            HashSet<Integer> tempSet = new HashSet<>();
            tempSet.add(makeTheNumber(answer, N));
            for (int i = 1; i < answer; i++) {
                tempSet.addAll(plus(i, answer - i, map));
                tempSet.addAll(minus(i, answer - i, map));
                tempSet.addAll(multiply(i, answer - i, map));
                tempSet.addAll(divide(i, answer - i, map));
            }
            if (tempSet.contains(number)) return answer;
            map.put(answer, tempSet);
        }

        return -1;
    }

    int makeTheNumber(int length, int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(N);
        }
        return Integer.parseInt(sb.toString());
    }

    HashSet<Integer> plus(int value1, int value2, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> set1 = map.get(value1);
        HashSet<Integer> set2 = map.get(value2);
        HashSet<Integer> resultSet = new HashSet<>();
        for (Integer i : set1) {
            for (Integer j : set2) {
                resultSet.add(i + j);
            }
        }
        return resultSet;
    }

    HashSet<Integer> minus(int value1, int value2, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> set1 = map.get(value1);
        HashSet<Integer> set2 = map.get(value2);
        HashSet<Integer> resultSet = new HashSet<>();
        for (Integer i : set1) {
            for (Integer j : set2) {
                resultSet.add(i - j);
            }
        }
        return resultSet;
    }

    HashSet<Integer> multiply(int value1, int value2, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> set1 = map.get(value1);
        HashSet<Integer> set2 = map.get(value2);
        HashSet<Integer> resultSet = new HashSet<>();
        for (Integer i : set1) {
            for (Integer j : set2) {
                resultSet.add(i * j);
            }
        }
        return resultSet;
    }

    HashSet<Integer> divide(int value1, int value2, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> set1 = map.get(value1);
        HashSet<Integer> set2 = map.get(value2);
        HashSet<Integer> resultSet = new HashSet<>();
        for (Integer i : set1) {
            for (Integer j : set2) {
                if(j == 0) continue;
                resultSet.add(i / j);
            }
        }
        return resultSet;
    }
}
