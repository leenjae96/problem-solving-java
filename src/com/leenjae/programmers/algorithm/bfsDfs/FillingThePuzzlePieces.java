package com.leenjae.programmers.algorithm.bfsDfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 프로그래머스 - BFS/DFS - level3
 * 퍼즐 조각 채우기
 *
 * @author leenjae96
 * @date 25.11.23 (저 아래 원래 풀었던 답안이 있음.)
 * 위 답안은 새로 푼 답안 11/29
 */
public class FillingThePuzzlePieces {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int height;
    int width;

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        //game_board와 table 을 0 < rows(height) , 0 < cols(width) 순회하며 도형을 찾는 함수를 만듭니다.
        List<Shape> shapes = findShapes(table, 1);
        List<Shape> holes = findShapes(game_board, 0);
        //도형은 dfs든 bfs든 상관없이 위치를 저장하는 방식으로 찾아내고
        // 도형을 저장할 때는 늘 0,0에 맞춘 방식(initialize)으로 저장합니다
        // table에서 먼저 도형을 찾아 저장해두고 ,
        // game_board에서 찾은 도형들을 회전시켜가며 table에서 찾은 도형과 같은지 비교합니다.
        // table도형은 list에 저장하고 list를 순회하며 찾고 list가 비어있으면 멈춥니다.
        for (Shape hole : holes) {
            Shape one = null;
            for (Shape shape : shapes) {
                if ((hole.size == 1 && shape.size == 1) || hole.canFillBy(shape)){
                    one = shape;
                    break;
                }
            }
            if (one != null) {
                answer += one.size;
                shapes.remove(one);

            }
        }
        return answer;
    }

    List<Shape> findShapes(int[][] board, int standard) {
        height = board.length;
        width = board[0].length;
        boolean[][] visited = new boolean[height][width];

        List<Shape> shapes = new ArrayList<>();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (board[x][y] != standard || visited[x][y]) {
                    continue;
                }
                Shape shape = digShape(x, y, board, visited, standard);
                shape.initialize();
                shapes.add(shape);
            }
        }
        return shapes;
    }

    Shape digShape(int x, int y, int[][] board, boolean[][] visited, int standard) {
        Shape shape = new Shape();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        visited[x][y] = true;
        shape.xPosition.add(x);
        shape.yPosition.add(y);
        while (!queue.isEmpty()) {
            int curX = queue.poll();
            int curY = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if ((nx < 0 || nx >= height || ny < 0 || ny >= width) ||
                        board[nx][ny] != standard || visited[nx][ny]) {
                    continue;
                }
                queue.add(nx);
                queue.add(ny);
                visited[nx][ny] = true;
                shape.xPosition.add(nx);
                shape.yPosition.add(ny);
            }
        }
        return shape;
    }
}

class Shape {
    List<Integer> xPosition = new ArrayList<>();
    List<Integer> yPosition = new ArrayList<>();
    int size;

    private int[][] position;

    void initialize() {
        //xPosition, yPosition 을 가지고 position과 size settting
        size = xPosition.size();
        int minX = xPosition.stream().min(Integer::compareTo).get();
        int maxX = xPosition.stream().max(Integer::compareTo).get();
        int minY = yPosition.stream().min(Integer::compareTo).get();
        int maxY = yPosition.stream().max(Integer::compareTo).get();

        position = new int[maxX - minX + 1][maxY - minY + 1];
        for (int i = 0; i < xPosition.size(); i++) {
            position[xPosition.get(i) - minX][yPosition.get(i) - minY] = 1;
        }
    }

    void rotate() {
        List<Integer> newX = new ArrayList<>();
        List<Integer> newY = new ArrayList<>();

        for (int i = 0; i < xPosition.size(); i++) {
            newY.add(-xPosition.get(i));
            newX.add(yPosition.get(i));
        }
        xPosition = newX;
        yPosition = newY;
        initialize();
    }

    boolean canFillBy(Shape shape) {
        boolean check = false;
        if (this.size != shape.size) {
            return check;
        }
        tryNext:
        for (int i = 0; i < 4; i++) {
            if (this.position.length != shape.position.length ||
                    this.position[0].length != shape.position[0].length) {
                rotate();
                continue;
            }
            for (int x = 0; x < this.position.length; x++) {
                for (int y = 0; y < this.position[0].length; y++) {
                    if (this.position[x][y] != shape.position[x][y]) {
                        rotate();
                        continue tryNext;
                    }
                }
            }
            return true;
        }
        return false;
    }
}


/*
class FillingThePuzzlePieces2 {
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

        List<Shape2> puzzles = new ArrayList<>();
        List<Shape2> holes = new ArrayList<>();
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

    int fillTheHole(List<Shape2> holes, List<Shape2> puzzles) {
        int answer = 0;
        boolean[] usePuzzle = new boolean[puzzles.size()];

        for (int i = 0; i < holes.size(); i++) {
            Shape2 hole = holes.get(i);
            for (int j = 0; j < puzzles.size(); j++) {
                if (usePuzzle[j]) continue;
                Shape2 puzzle = puzzles.get(j);

                if (check(hole, puzzle)) {
                    usePuzzle[j] = true;
                    answer += puzzle.size;
                    break;
                }
            }
        }
        return answer;
    }

    boolean check(Shape2 hole, Shape2 puzzle) {
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

    void bfs(int x, int y, List<Shape2> shapes, boolean[][] visited, int[][] board) {
        Shape2 shape = new Shape2();
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

class Shape2 {
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
*/
