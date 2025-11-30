package com.leenjae.programmers.algorithm.dynamicProgramming;

import java.util.Arrays;

/**
 * 프로그래머스 - 동적계획법 - level3
 * 정수 삼각형
 *
 * @author leenjae96
 * @date 25.11.30
 */
public class IntegerTriangle {
    public int solution(int[][] triangle) {
        //floor(topDown)과 column 존재.
        //-floor 의 최대는 triangle 의 length, col 의 최대는 현재 floor 만큼
        int height = triangle.length;
        //최대값 저장 maxBoard
        int[][] maxBoard = new int[height][height];
        maxBoard[0][0] = triangle[0][0];
        for (int floor = 1; floor < height; floor++) {
            for (int col = 0; col <= floor; col++) {
                maxBoard[floor][col] = findMax(floor, col, triangle, maxBoard);
            }
        }
        //현재 위치(floor, col) 계산하는 함수
        //-자기 자신과 자기 자신으로 올수 있는 값 중 최대값을 선택하고 더해서 저장.
        return Arrays.stream(maxBoard[height - 1]).max().getAsInt();
    }

    int findMax(int floor, int col, int[][] triangle, int[][] maxBoard) {
        int myValue = triangle[floor][col];
        int max;
        if (col == 0) {
            max = myValue + maxBoard[floor - 1][col];
        } else if (col == floor) {
            max = myValue + maxBoard[floor - 1][col - 1];
        } else {
            max = myValue + Math.max(maxBoard[floor - 1][col - 1], maxBoard[floor - 1][col]);
        }
        return max;
    }
}
