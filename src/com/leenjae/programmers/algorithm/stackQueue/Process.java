package com.leenjae.programmers.algorithm.stackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 프로그래머스 - 스택/큐 - level2
 * 프로세스
 *
 * @author leenjae96
 * @date 25.11.27
 */
public class Process {
    public int solution(int[] priorities, int location) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> checkList = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            checkList.add(priority);
            if (i == location) {
                priority = priority + 100;
            }
            queue.add(priority);
        }
        int answer = 0;

        while (!queue.isEmpty()) {
            int priority = queue.poll();
            if (priority > 100) {
                priority = priority - 100;
                if (isProcessible(checkList, priority)) {
                    answer++;
                    break;
                }
                priority = priority + 100;
                queue.add(priority);
                continue;
            }

            if (isProcessible(checkList, priority)) {
                answer++;
            } else {
                queue.add(priority);
            }
        }
        return answer;
    }

    boolean isProcessible(List<Integer> priorities, Integer priority) {
        for (int i = 0; i < priorities.size(); i++) {
            if (priorities.get(i) > priority) {
                return false;
            }
        }
        priorities.remove(priority);
        return true;
    }
}
