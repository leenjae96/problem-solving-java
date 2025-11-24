package com.leenjae.programmers.bfsDfs;

import java.util.LinkedList;
import java.util.Queue;

public class PickingUpItems {
    boolean[][] VISITED = new boolean[101][101];
    int[][] MAP = new int[101][101];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        for (int[] rectInfo : rectangle) {
            int minX = rectInfo[0] * 2;
            int minY = rectInfo[1] * 2;
            int maxX = rectInfo[2] * 2;
            int maxY = rectInfo[3] * 2;

            //MAP 0 초기화x
            //MAP 1 길, 테두리
            //MAP -1 구멍
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    if (MAP[x][y] == -1) continue;

                    if (x == minX || x == maxX || y == minY || y == maxY) {
                        MAP[x][y] = 1;
                    } else {
                        MAP[x][y] = -1;
                    }
                }
            }
        }

        PositionNode positionNode = new PositionNode(characterX * 2, characterY * 2, 0);

        Queue<PositionNode> queue = new LinkedList<>();
        VISITED[positionNode.x][positionNode.y] = true;
        findNextAndAddToQueue(positionNode, queue);
        while (!queue.isEmpty()) {
            PositionNode pollingNode = queue.poll();
            if (pollingNode.x == itemX * 2 && pollingNode.y == itemY * 2) {
                answer = pollingNode.depth / 2;
                break;
            }
            VISITED[pollingNode.x][pollingNode.y] = true;
            findNextAndAddToQueue(pollingNode, queue);
        }

        return answer;
    }

    void findNextAndAddToQueue(PositionNode node, Queue<PositionNode> queue) {
        int x = node.x;
        int y = node.y;
        int depth = node.depth;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || ny < 1 || nx > 100 || ny > 100) continue;
            if (VISITED[nx][ny] || MAP[nx][ny] != 1) continue;
            queue.add(new PositionNode(nx, ny, depth + 1));
        }
    }
}

class PositionNode {
    int x, y;
    int depth;

    PositionNode(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}