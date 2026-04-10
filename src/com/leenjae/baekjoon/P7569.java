package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준- 토마토- 골드5(스택)
 *
 * @author leenjae96
 * @date 26.04.09
 */
public class P7569 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        int m, n, h;
        int[][][] box;
        int[][][] days;

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        box = new int[h][n][m];
        days = new int[h][n][m];

        Queue<Tomato> queue = new LinkedList<>();
        for (int height = 0; height < h; height++) {
            for (int row = 0; row < n; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < m; col++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    box[height][row][col] = tomato;
                    days[height][row][col] = tomato == 1 ? 0 : -1;
                    //익은 토마토인 경우에만 queue에 체크.
                    if(tomato == 1) queue.add(new Tomato(height, row, col));
                }
            }
        }

        //위층 아래층 과 현재층에서의 상하좌우 확인.
        int[] dh = {1, 0, 0, 0, 0, -1};
        int[] dr = {0, -1, 0, 1, 0, 0};
        int[] dc = {0, 0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            Tomato cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nh = cur.h + dh[i];
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(nh < 0 || nr < 0 || nc < 0 ||
                        nh >= h || nr >= n || nc >= m ||
                        box[nh][nr][nc] == -1 || box[nh][nr][nc] == 1) continue; //범위를 벗어나면 pass
                if(days[nh][nr][nc] == -1) { //아직 도착한 적이 없을 때만, 계산
                    days[nh][nr][nc] = days[cur.h][cur.r][cur.c] + 1;
                    queue.add(new Tomato(nh, nr, nc));
                }
            }
        }

        int answer = 0;
        for (int height = 0; height < h; height++) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    int day = days[height][row][col];
                    if(day == -1 && box[height][row][col] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    answer = Math.max(days[height][row][col], answer);
                }
            }
        }
        System.out.println(answer);
    }
}

class Tomato {
    int h;
    int r;
    int c;

    Tomato(int h, int r, int c) {
        this.h = h;
        this.r = r;
        this.c = c;
    }
}