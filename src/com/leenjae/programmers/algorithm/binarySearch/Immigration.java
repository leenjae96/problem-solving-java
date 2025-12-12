package com.leenjae.programmers.algorithm.binarySearch;

import java.util.Arrays;

/**
 * 프로그래머스 - 이진탐색 - level3
 * 입국심사
 *
 * @author leenjae96
 * @date 25.12.12
 */
public class Immigration {
    int[] TIMES;
    int N;

    public long solution(int n, int[] times) {
        // times 정렬
        Arrays.sort(times);
        TIMES = times;
        N = n;
        int minTime = times[0];
        long maxTime = ((long) (n / times.length) + 1) * times[times.length - 1];
        // 이분탐색
        // -예상 걸리는 시간을 times의 값 들의 몫을 구해 더하고 그게 n와 딱 맞으면 체크..
        return binarySearch(minTime, maxTime);
    }

    long binarySearch(long front, long end) {
        long answer = 0;
        while (front <= end) {
            long mid = (front + end) / 2;
            long count = 0;

            for (int time : TIMES) {
                count += (mid / time);
                if (count >= N) break;
            }

            if (count >= N) {
                answer = mid;
                end = mid - 1;
            } else {
                front = mid + 1;
            }
        }
        return answer;
    }
}
