package com.leenjae.programmers.hash;

import java.util.HashMap;

/**
 * 프로그래머스 - 해시 - level1
 * 완주하지 못한 선수
 *
 * @author leenjae96
 */
public class UnfinishedRunner {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();
        for (String one : completion) {
            map.put(one, map.getOrDefault(one, 0) + 1);
        }

        for (String one : participant) {
            Integer integer = map.get(one);
            if (integer == null) {
                answer = one;
                break;
            } else if (integer == 1) {
                map.remove(one);
            } else {
                map.put(one, integer - 1);
            }
        }
        return answer;
    }
}
