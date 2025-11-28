package com.leenjae.programmers.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 스택/큐 - level2
 * 주식 가격
 *
 * @author leenjae96
 * @date 25.11.28
 */
public class StockPrice {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];

        Queue<Integer> queue = new LinkedList<>();
        for (int curIndex = 0; curIndex < length; curIndex++) {
            int price = prices[curIndex];
            if (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    Integer remainIndex = queue.poll();
                    if (prices[remainIndex] > price) {
                        answer[remainIndex] = curIndex - remainIndex;
                    } else {
                        queue.add(remainIndex);
                    }
                }
            }
            queue.add(curIndex);
        }

        while(!queue.isEmpty()) {
            Integer index = queue.poll();
            answer[index] = length - index - 1;
        }
        return answer;
    }
}
