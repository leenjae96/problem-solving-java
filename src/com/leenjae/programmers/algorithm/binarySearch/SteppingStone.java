package com.leenjae.programmers.algorithm.binarySearch;

import java.util.Arrays;

/**
 * 프로그래머스 - 이진탐색 - level3
 * 징검다리
 *
 * @author leenjae96
 * @date 25.12.12
 */
public class SteppingStone {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (left <= right) {
            int mid = (left + right) / 2;
            int deleteCount = 0;
            int prevRock = 0;
            for (int rock : rocks) {
                if ((rock - prevRock) < mid) {
                    deleteCount++;
                } else {
                    prevRock = rock;
                }
            }
            if (distance - prevRock < mid) {
                deleteCount++;
            }

            if (deleteCount > n) {
                right = mid - 1;
            } else if (deleteCount <= n) {
                left = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }
}
