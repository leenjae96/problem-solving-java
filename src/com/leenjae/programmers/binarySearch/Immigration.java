package com.leenjae.programmers.binarySearch;

import java.util.Arrays;

/**
 * 프로그래머스 - 이진탐색 - level3
 * 입국심사
 *
 * @author leenjae96
 * @date 26.03.06
 */
class Solution2 {
    public long solution(int n, int[] times) {
        // times 정렬
        Arrays.sort(times);
        // answer최댓값 세팅 .. max * n;
        long front = times[0];
        long rear = (long) times[times.length - 1] * n + 1;
        // answer(시간) 기준 binarySearch.
        while(front < rear) {
            // - mid 내에 사람들을 다 뺄 수 있는가?
            long mid = (front + rear)/2;
            long count = 0;
            for(int time : times) {
                if(mid < time) {
                    break;
                }
                count += (mid / time);
            }

            if(count < n) {
                front = mid+1;
            } else {
                rear = mid;
            }
        }
        return rear;
    }
}



/**
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
