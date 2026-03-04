package com.leenjae.programmers.stackQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 프로그래머스 - 스택/큐 - level2
 * 기능 구현
 *
 * @author leenjae96
 * @date 25.11.25
 */
public class FeatureDeveloping {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];

            int offset = ((100 - progress) % speed) == 0 ? 0 : 1;
            int needDay = ((100 - progress) / speed) + offset;
            queue.add(needDay);
        }
        int top = queue.poll();
        int count = 1;
        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            int need = queue.poll();
            if(need > top) {
                top = need;
                list.add(count);
                count =1;
            } else {
                count++;
            }
        }
        list.add(count);
        return list.stream().mapToInt(i -> i).toArray();
    }
}
