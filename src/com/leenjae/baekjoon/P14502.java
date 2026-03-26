package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 - 연구소 - 골드4 (그래프, 브루트포스, 백트래킹, DFS, BFS)
 *
 * @author leenjae96
 * @date 26.03.26
 */
    //solution2 의 경우 처음에 마구잡이로 풀이해서 3중 for문으로 해결.
    //solution 의 경우 백트래킹 DFS 로 좀 더 우~아 하게 해결.
public class P14502 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N, M, answer, totalEmpty;
    int[][] originMap;
    List<Node> viruses = new ArrayList<>();

    void solution() throws IOException {
        //맵 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
                if (originMap[i][j] == 0) totalEmpty++;
                if (originMap[i][j] == 2) viruses.add(new Node(i, j));
            }
        }
        buildWall(0, 0);

        System.out.println(answer);
    }

    //몇번째 벽인지, 현재 인덱스는 어딘지 를 전달받는다.
    void buildWall(int count, int start) {
        //3번째 벽까지 세워졌으면, 바이러스를 퍼트려본다.
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = start; i < N * M; i++) {
            int row = i / M;
            int col = i % M;

            if (originMap[row][col] == 0) {
                //백트래킹. 벽을 세우고 체크하고 지운다.
                originMap[row][col] = 1;
                buildWall(count + 1, i + 1);
                originMap[row][col] = 0;
            }
        }
    }

    void spreadVirus() {
        Queue<Node> q = new LinkedList<>();
        int[][] walledMap = new int[N][M];

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                walledMap[i][j] = originMap[i][j];
            }
        }

        int empty = totalEmpty - 3;

        for(Node virus : viruses) {
            q.offer(virus);
        }

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while(!q.isEmpty()) {
            Node cVirus = q.poll();
            for(int i = 0 ; i < 4 ; i++) {
                int nr = cVirus.row + dr[i];
                int nc = cVirus.col + dc[i];
                if(nr < 0 || nr >=N || nc < 0 || nc >= M ||
                        walledMap[nr][nc] == 2 ||
                        walledMap[nr][nc] == 1)
                    continue;
                walledMap[nr][nc] = 2;
                empty--;
                q.offer(new Node(nr, nc));
            }
        }
        answer = Math.max(answer, empty);
    }

    void solution2() throws IOException {
        //맵 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Node> viruses = new ArrayList<>();
        int[][] originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
                if (originMap[i][j] == 2) {
                    viruses.add(new Node(i, j));
                }
            }
        }
        int[] nr = {1, -1, 0, 0};
        int[] nc = {0, 0, 1, -1};
        int answer = 0;
        //3중for문 돌면서 벽 세우기
        for (int i = 0; i < N * M; i++) {
            if (originMap[i / M][i % M] == 1 || originMap[i / M][i % M] == 2) continue;

            for (int j = i + 1; j < N * M; j++) {
                if (originMap[j / M][j % M] == 1 || originMap[j / M][j % M] == 2) continue;

                for (int k = j + 1; k < N * M; k++) {
                    if (originMap[k / M][k % M] == 1 || originMap[k / M][k % M] == 2) continue;
                    int[][] walledMap = new int[N][M];
                    for(int r = 0; r < N; r++){
                        for(int c = 0; c < M; c++){
                            walledMap[r][c] = originMap[r][c];
                        }
                    }
                    walledMap[i / M][i % M] = 1;
                    walledMap[j / M][j % M] = 1;
                    walledMap[k / M][k % M] = 1;
                    //2 위치 기준으로 bfs하면서 2만들기
                    for (Node virus : viruses) {
                        Queue<Node> q = new LinkedList<>();
                        q.offer(virus);
                        while (!q.isEmpty()) {
                            Node cVirus = q.poll();
                            for (int n = 0; n < 4; n++) {
                                int row = cVirus.row + nr[n];
                                int col = cVirus.col + nc[n];
                                if (row < 0 || row >= N || col < 0 || col >= M ||
                                        walledMap[row][col] == 1 ||
                                        walledMap[row][col] == 2)
                                    continue;
                                walledMap[row][col] = 2;
                                q.offer(new Node(row, col));
                            }
                        }
                    }

                    int count = 0;
                    //끝나면 0갯수세기
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            if (walledMap[r][c] == 0) count++;
                        }
                    }
                    //max 갱신
                    answer = Math.max(answer, count);
                    //다시 벽세우기
                }
            }
        }
        System.out.println(answer);
    }
}

class Node {
    int row;
    int col;

    Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}