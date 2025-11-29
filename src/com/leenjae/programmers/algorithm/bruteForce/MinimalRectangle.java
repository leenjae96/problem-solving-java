package com.leenjae.programmers.algorithm.bruteForce;

/**
 * 프로그래머스 - 완전탐색 - level1
 * 최소 직사각형
 *
 * @author leenjae96
 */
public class MinimalRectangle {
    public int solution(int[][] sizes) {
        int biggestWidth = 1; //always width >= height
        int biggestHeight = 1;
        for(int[] nameCard : sizes) {
            int w, h;
            // 전체를 순회하면서 넓은 쪽을 너비로 두고
            if(nameCard[0] < nameCard[1]) {
                w = nameCard[1];
                h = nameCard[0];
            } else {
                w = nameCard[0];
                h = nameCard[1];
            }
            // 너비와 높이가 이전 최고값보다 크면 교체
            if(w > biggestWidth) {
                biggestWidth = w;
            }
            if(h > biggestHeight) {
                biggestHeight = h;
            }
        }

        return biggestWidth * biggestHeight;
    }
}
