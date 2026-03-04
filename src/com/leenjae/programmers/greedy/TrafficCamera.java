package com.leenjae.programmers.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 프로그래머스 - (greedy 탐욕법) - level3
 * 단속카메라
 *
 * @author leenjae96
 * @date 26.03.04(복습)
 */
public class TrafficCamera {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int lastPosition = -30001;
        for(int[] route : routes) {
            int carIn = route[0];
            int carOut = route[1];
            if(carIn<=lastPosition) {
                continue;
            }
            lastPosition = carOut;
            answer++;
        }
        return answer;
    }
}
