package com.leenjae.programmers.heap;

import java.util.PriorityQueue;

/**
 * 프로그래머스 - heap - level3
 * 더 맵게
 *
 * @author leenjae96
 * @date 25.11.24
 */
public class Spicier {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : scoville) {
            pq.add(i);
        }

        int answer = 0;
        while(true) {
            Integer min1 = pq.poll();
            Integer min2 = pq.poll();
            if (min1 != null && min1 >= K) {
                break;
            }
            if (min2 == null) {
                answer = -1;
                break;
            }
            pq.add(min1 + min2 * 2);
            answer++;
        }

        return answer;
    }
}
