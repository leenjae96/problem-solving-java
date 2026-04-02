package com.leenjae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 배열 돌리기 - 골드5(구현)
 *
 * @author leenjae96
 * @date 26.03.26
 */
public class P16926 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        //배열 저장
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //방문 여부 확인
        boolean[][] visited = new boolean[N][M];

        //matrix -> 1차원 배열 저장할 list 생성
        List<int[]> lineList = new ArrayList<>();
        //작은 수/2 기준으로 배열 초기화
        int min = Math.min(N, M);
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int len = (rowCount * 2 + colCount * 2) - 4;
        for (int i = 0; i < min / 2; i++) {
            lineList.add(new int[len]);
            len -= 8;
        }

        int[] dr = new int[]{1, 0, -1, 0};
        int[] dc = new int[]{0, 1, 0, -1};
        //list에서 꺼낸 1차원 배열에 저장
        for (int i = 0; i < min / 2; i++) {
            int[] line = lineList.get(i);
            int index = 0;
            //진입점(0,0) 기준으로 한칸 위 지점을 넣어준다.
            int row = i - 1;
            int col = i;
            for (int direction = 0; direction < 4; direction++) {
                //아래 오른쪽 위 왼쪽 방향으로 가면서 저장
                row += dr[direction];
                col += dc[direction];
                while (row > -1 && col > -1 && row < N && col < M &&
                        !visited[row][col]) {
                    visited[row][col] = true;
                    line[index++] = matrix[row][col];
                    row += dr[direction];
                    col += dc[direction];
                }
                //현재 line에 벽에 도달하면 한칸 back
                row -= dr[direction];
                col -= dc[direction];
            }
        }

        List<int[]> rotatedLineList = new ArrayList<>();
        //list 순회하면서 해당 1차원 배열을 반시계로 돌리기
        for (int i = 0; i < min / 2; i++) {
            int[] line = lineList.get(i);
            int lineR = R % line.length;
            int[] rotatedLine = new int[line.length];
            for(int j = 0; j < line.length; j++){
                rotatedLine[j] = line[(line.length - lineR + j) % line.length];
            }
            rotatedLineList.add(rotatedLine);
        }
        //visited 초기화
        visited = new boolean[N][M];
        //list 순회하면서 해당 1차원 배열을 matrix에 저장.
        for (int i = 0; i < min / 2; i++) {
            int[] line = rotatedLineList.get(i);
            int index = 0;
            int row = i - 1;
            int col = i;
            for (int direction = 0; direction < 4; direction++) {
                row += dr[direction];
                col += dc[direction];
                while (row > -1 && col > -1 && row < N && col < M &&
                        !visited[row][col]) {
                    visited[row][col] = true;
                    matrix[row][col] = line[index++];
                    row += dr[direction];
                    col += dc[direction];
                }
                row -= dr[direction];
                col -= dc[direction];
            }
        }

        //출력.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
