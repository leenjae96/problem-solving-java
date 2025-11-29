package com.leenjae.programmers.algorithm.greedy;

import java.util.HashSet;

/**
 * 프로그래머스 - 그리디 - level1
 * 체육복
 *
 * @author leenjae96
 * @date 25.11.27
 */
public class GymClothes {
    public int solution(int n, int[] lost, int[] reserve) {
        HashSet<Integer> lostSet = new HashSet<>();
        HashSet<Integer> remainSet = new HashSet<>();
        for (int one : reserve) {
            remainSet.add(one);
        }
        for (int one : lost) {
            if (remainSet.contains(one)) {
                remainSet.remove(one);
            } else {
                lostSet.add(one);
            }
        }
        int answer = n - lostSet.size();
        for (int one : lostSet) {
            if (remainSet.contains(one - 1)) {
                answer++;
                remainSet.remove(one - 1);
            } else if (remainSet.contains(one + 1)) {
                answer++;
                remainSet.remove(one + 1);
            }
        }
        return answer;
    }
}
