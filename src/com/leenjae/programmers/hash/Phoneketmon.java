package com.leenjae.programmers.hash;

import java.util.HashSet;

/**
 * 프로그래머스 - 해시 - level1
 * 폰켓몬
 *
 * @author leenjae96
 */
public class Phoneketmon {
    public int solution(int[] nums) {
        int numSize = nums.length;
        int peekableSize = numSize / 2;

        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int setSize = set.size();

        int answer = 0;
        if (peekableSize <= setSize) {
            answer = peekableSize;
        } else { //peekable > setSize
            answer = setSize;
        }

        return answer;
    }
}
