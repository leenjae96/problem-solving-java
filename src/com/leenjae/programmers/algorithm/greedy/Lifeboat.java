package com.leenjae.programmers.algorithm.greedy;

import java.util.Arrays;

/**
 * 프로그래머스 - 그리디 - level2
 * 구명보트
 *
 * @author leenjae96
 * @date 25.11.28
 */
public class Lifeboat {
    public int solution(int[] people, int limit) {
        int answer = 0;
        //people 무게순 정렬.
        Arrays.sort(people);
        // 왼쪽과 오른쪽 끝에서부터 무게 합 측정해가며,
        int left = 0;
        int right = people.length - 1;
        while(left <= right) {
            if(left == right) {
                answer++;
                left++;
            } else if(people[left] + people[right] <= limit) {
                answer++;
                left++;
                right--;
            } else {
                answer++;
                right--;
            }
        }
        return answer;
    }
}
