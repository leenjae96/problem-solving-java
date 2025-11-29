package com.leenjae.programmers.algorithm.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 스택/큐 - level1
 * 같은 숫자는 싫어
 *
 * @author leenjae96
 * @date 25.11.25
 */
public class SameNumberHater {
    public int[] solution(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        int preValue = -1;
        for (int value : arr) {
            if (preValue == value) {
                continue;
            }
            preValue = value;
            queue.add(value);
        }

        return queue.stream().mapToInt(i->i).toArray();
    }
}
