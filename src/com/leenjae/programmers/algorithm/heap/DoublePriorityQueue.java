package com.leenjae.programmers.algorithm.heap;

import java.util.PriorityQueue;

/**
 * 프로그래머스 - heap - level3
 * 이중 우선순위 큐
 *
 * @author leenjae96
 * @date 25.11.24
 */
public class DoublePriorityQueue {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (String operation : operations) {
            String[] command = operation.split(" ");
            switch (command[0]) {
                case "I":
                    minHeap.add(Integer.parseInt(command[1]));
                    maxHeap.add(Integer.parseInt(command[1]));
                    break;
                case "D":
                    if (command[1].equals("1")) {
                        Integer max = maxHeap.poll();
                        if (max != null) {
                            minHeap.remove(max);
                        }
                    } else {
                        Integer min = minHeap.poll();
                        if (min != null) {
                            maxHeap.remove(min);
                        }
                    }
                    break;
            }
        }

        int max = maxHeap.peek() == null ? 0 : maxHeap.peek();
        int min = minHeap.peek() == null ? 0 : minHeap.peek();

        return new int[]{max, min};
    }
}
