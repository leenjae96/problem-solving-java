package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 골드5(그리디)
 * 배
 *
 * @author leenjae96
 * @date 26.03.19
 */
public class P1092 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N, M;
    List<Integer> cranes;
    List<Integer> boxes;

    int solution() throws IOException {
        int time = -1;

        N = Integer.parseInt(br.readLine());
        cranes = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }
        cranes.sort(Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());
        boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }
        boxes.sort(Collections.reverseOrder());

        if (cranes.get(0) < boxes.get(0)) {
            return time;
        }

        //1. cranes 순회하면서
        //2. 그안에서 boxes 순회. 해당 박스가 crane에 달수있으면 box delete. 달수 없으면 다음 boxes.
        //3. boxes가 끝날때까지 못찾으면 시간 ++하고 크레인 처음부터.
        time = 0;
        while (!boxes.isEmpty()) {
            int boxIdx = 0;
            int craneIdx = 0;
            while (craneIdx < N && boxIdx < boxes.size()) {
                if (cranes.get(craneIdx) >= boxes.get(boxIdx)) {
                    boxes.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++;
                }
            }
            time++;
        }
        return time;
    }
}
