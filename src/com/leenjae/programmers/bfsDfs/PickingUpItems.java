package com.leenjae.programmers.bfsDfs;

import java.util.LinkedList;
import java.util.Queue;

public class PickingUpItems {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //주의!! 모든것을 double size로 계산한다.
        //rectangle 갯수만큼 순회하며 map 을 그린다. 1길 2사각형내부
        int[][] map = drawMap(rectangle);
        //map에서 character 위치로부터 item위치까지 bfs.
        return bfs(map, characterX, characterY, itemX, itemY);
    }

    int[][] drawMap(int[][] rectangle) {
        int[][] map = new int[101][101];
        for (int[] one : rectangle) {
            int row1 = one[0] * 2;
            int col1 = one[1] * 2;
            int row2 = one[2] * 2;
            int col2 = one[3] * 2;

            for (int r = row1; r <= row2; r++) {
                for (int c = col1; c <= col2; c++) {
                    //테두리일때
                    if (r == row1 || r == row2 || c == col1 || c == col2) {
                        if (map[r][c] == 2) {
                            continue;
                        }
                        map[r][c] = 1;
                    }
                    //테두리 내부일때
                    else {
                        map[r][c] = 2;
                    }
                }
            }
        }
        return map;
    }

    int bfs(int[][] map, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean[][] visited = new boolean[101][101];

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(characterX, characterY, 0));
        visited[characterX][characterY] = true;
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            if(cur.x == itemX && cur.y == itemY) {
                answer = cur.moveCount/2;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if ((nx < 2 || nx > 100 || ny < 2 || ny > 100) ||
                        (visited[nx][ny] || map[nx][ny] != 1)) {
                    continue;
                }

                queue.add(new Position(nx, ny, cur.moveCount+1));
                visited[nx][ny] = true;
            }
        }
        return answer;
    }
}

class Position {
    int x;
    int y;
    int moveCount;

    Position(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
}