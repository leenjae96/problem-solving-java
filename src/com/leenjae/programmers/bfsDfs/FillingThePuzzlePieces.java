package com.leenjae.programmers.bfsDfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - BFS/DFS - level3
 * 퍼즐 조각 채우기
 *
 * @author leenjae96
 * @date 25.11.23
 */
public class FillingThePuzzlePieces {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    int[][] BOARD;
    int[][] TABLE;
    int LENGTH;

    public int solution(int[][] game_board, int[][] table) {
        TABLE = table;
        BOARD = game_board;
        LENGTH = table.length;
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                if (BOARD[x][y] == 0) BOARD[x][y] = 1;
                else BOARD[x][y] = 0;
            }
        }

        List<Shape> puzzles = new ArrayList<>();
        List<Shape> holes = new ArrayList<>();
        boolean[][] visitedTable = new boolean[LENGTH][LENGTH];
        boolean[][] visitedBoard = new boolean[LENGTH][LENGTH];
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                if (TABLE[x][y] == 1 && !visitedTable[x][y]) {
                    bfs(x, y, puzzles, visitedTable, TABLE);
                }

                if (BOARD[x][y] == 1 && !visitedBoard[x][y]) {
                    bfs(x, y, holes, visitedBoard, BOARD);
                }
            }
        }

        return fillTheHole(holes, puzzles);
    }

    int fillTheHole(List<Shape> holes, List<Shape> puzzles) {
        int answer = 0;
        boolean[] usePuzzle = new boolean[puzzles.size()];

        for (int i = 0; i < holes.size(); i++) {
            Shape hole = holes.get(i);
            for (int j = 0; j < puzzles.size(); j++) {
                if (usePuzzle[j]) continue;
                Shape puzzle = puzzles.get(j);

                if (check(hole, puzzle)) {
                    usePuzzle[j] = true;
                    answer += puzzle.size;
                    break;
                }
            }
        }
        return answer;
    }

    boolean check(Shape hole, Shape puzzle) {
        if (hole.size != puzzle.size) return false;

        for (int i = 0; i < 4; i++) {
            boolean check = true;
            for (int j = 0; j < hole.size; j++) {
                if (hole.positionList.get(j)[0] != puzzle.positionList.get(j)[0] ||
                        hole.positionList.get(j)[1] != puzzle.positionList.get(j)[1]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                return true;
            } else {
                //rotate문제가 있음.
                for (int j = 0; j < puzzle.size; j++) {
                    int tempX = puzzle.positionList.get(j)[0];
                    int tempY = puzzle.positionList.get(j)[1];
                    puzzle.positionList.get(j)[0] = tempY;
                    puzzle.positionList.get(j)[1] = -tempX;
                }
                puzzle.stabilize();
            }
        }
        return false;
    }

    void bfs(int x, int y, List<Shape> shapes, boolean[][] visited, int[][] board) {
        Shape shape = new Shape();
        visited[x][y] = true;
        shape.addPosition(0, 0);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        queue.add(y);
        while (!queue.isEmpty()) {
            int pollingX = queue.poll();
            int pollingY = queue.poll();
            for (int direction = 0; direction < 4; direction++) {
                int nx = pollingX + dx[direction];
                int ny = pollingY + dy[direction];
                if (nx < 0 || ny < 0 || nx >= LENGTH || ny >= LENGTH) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (board[nx][ny] == 0) continue;

                queue.add(nx);
                queue.add(ny);
                shape.addPosition(nx - x, ny - y);
            }
        }
        shape.size = shape.positionList.size();
        shape.stabilize();
        shapes.add(shape);
    }
}

class Shape {
    int size;

    List<int[]> positionList = new ArrayList<>();


    void addPosition(int x, int y) {
        positionList.add(new int[]{x, y});
    }

    void stabilize() {
        int minX = positionList.stream().map(i -> i[0]).min(Integer::compare).get();
        //int maxX = xList.stream().max(Integer::compare).get();
        int minY = positionList.stream().map(i -> i[1]).min(Integer::compare).get();
        //int maxY = yList.stream().max(Integer::compare).get();

        for (int[] position : positionList) {
            position[0] = position[0] + Math.abs(minX);
            position[1] = position[1] + Math.abs(minY);
        }

        positionList = positionList.stream().sorted((o1, o2) -> {
            if (o1[0] < o2[0]) return -1;
            else if(o1[0] == o2[0]) {
                if(o1[1] < o2[1]) return -1;
                else {return 1;}
            } else {
                return 1;
            }
        }).collect(Collectors.toList());
    }
}
