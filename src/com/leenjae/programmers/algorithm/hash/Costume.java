package com.leenjae.programmers.algorithm.hash;

import java.util.HashMap;

/**
 * 프로그래머스 - 해시 - level2
 * 의상
 *
 * @author leenjae96
 */
public class Costume {
    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 1) + 1);
        }

        for (int num : map.values()) {
            answer *= num;
        }

        return answer - 1;
    }
}
