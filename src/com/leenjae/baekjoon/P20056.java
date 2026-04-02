package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 마법사 상어와 파이어볼 - 골드4 (구현)
 *
 * @author leenjae96
 * @date 26.03.26
 */
public class P20056 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //모든 fireball을 저장하는 리스트에 저장.
        List<Fireball> everyFireball = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            everyFireball.add(new Fireball(r - 1, c - 1, m, s, d));
        }

        //fireball의 위치를 저장하는 map
        List<Fireball>[][] map = new ArrayList[N][N];
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int turn = 0; turn < K; turn++) {
            //fireball 위치 저장용 map 초기화
            for (int loopRow = 0; loopRow < N; loopRow++) {
                for (int loopCol = 0; loopCol < N; loopCol++) {
                    map[loopRow][loopCol] = new ArrayList<>();
                }
            }
            //이동 후 위치 map에 저장 (list->map)
            for (Fireball fb : everyFireball) {
                int speed = fb.speed % N;
                fb.row = (fb.row + dr[fb.direction] * speed + N) % N;
                fb.col = (fb.col + dc[fb.direction] * speed + N) % N;

                map[fb.row][fb.col].add(fb);
            }
            //사용한 list 초기화
            everyFireball.clear();

            //합치고 나눠지고 list에 저장(map -> list)
            for (int loopRow = 0; loopRow < N; loopRow++) {
                for (int loopCol = 0; loopCol < N; loopCol++) {
                    List<Fireball> existing = map[loopRow][loopCol];
                    if (existing.isEmpty()) continue;
                    if (existing.size() == 1) {
                        everyFireball.add(existing.get(0));
                        continue;
                    }

                    int row = 0;
                    int col = 0;
                    int totalMass = 0;
                    int totalSpeed = 0;
                    boolean same = true;
                    int before = -1;
                    for (Fireball f : existing) {
                        row = f.row;
                        col = f.col;
                        totalMass += f.mass;
                        totalSpeed += f.speed;
                        if (before == -1) {
                            before = f.direction;
                            continue;
                        }
                        if (same && before % 2 != f.direction % 2) {
                            same = false;
                        }
                        before = f.direction;
                    }

                    int count = existing.size();
                    if (totalMass / 5 != 0) {
                        for (int i = 0; i < 4; i++) {
                            if (same) {
                                everyFireball.add(new Fireball(row, col, totalMass / 5, totalSpeed / count, i * 2));
                            } else {
                                everyFireball.add(new Fireball(row, col, totalMass / 5, totalSpeed / count, i * 2 + 1));
                            }
                        }
                    }
                }
            }
        }
        int answer = 0;
        for (Fireball fb : everyFireball) {
            answer += fb.mass;
        }
        System.out.println(answer);
    }
}

class Fireball {
    int row, col, mass, speed, direction;

    Fireball(int row, int col, int weight, int speed, int direction) {
        this.row = row;
        this.col = col;
        this.mass = weight;
        this.speed = speed;
        this.direction = direction;
    }
}
