package com.leenjae.programmers.algorithm.bfsDfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level2
 * 게임 맵 최단거리
 *
 * @author leenjae96
 * @date 25.11.13
 */
public class GameMapShortestRoute {
    public int solution(int[][] maps) {
        int height = maps.length;
        int width = maps[0].length;

        int[] rDirection = {0, 1, 0, -1};
        int[] cDirection = {-1, 0, 1, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + rDirection[i];
                int nextCol = col + cDirection[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= height || nextCol >= width) continue;
                if(maps[nextRow][nextCol] != 1) continue;

                maps[nextRow][nextCol] = maps[row][col] + 1;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
        return maps[height-1][width-1] == 1 ? -1 : maps[height-1][width-1];
    }
}
