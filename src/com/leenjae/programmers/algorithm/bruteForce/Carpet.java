package com.leenjae.programmers.algorithm.bruteForce;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 카페트
 *
 * @author leenjae96
 */
public class Carpet {
    public int[] solution(int brown, int yellow) {
        int width = 0, height = 0;
        for (int yellowHeight = 1; yellowHeight <= Math.sqrt(yellow); yellowHeight++) {
            if (yellow % yellowHeight != 0) {
                continue;
            }

            int yellowWidth = yellow / yellowHeight;
            if (brown == (yellowWidth * 2 + yellowHeight * 2 + 4)) {
                width = yellowWidth + 2;
                height = yellowHeight + 2;
                break;
            }
        }

        return new int[]{width, height};
    }
}
