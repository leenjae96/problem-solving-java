package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 - 골드4
 * 강의실 배정
 * 그리디
 *
 * @author leenjae96
 * @date 26.03.24
 */
public class P11000 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] courses = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            courses[i][0] = Integer.parseInt(st.nextToken());
            courses[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(courses, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();
        for (int[] course : courses) {
            if(!endTimeQueue.isEmpty() && endTimeQueue.peek() <= course[0]) {
                endTimeQueue.poll();
            }
            endTimeQueue.add(course[1]);
        }
        System.out.println(endTimeQueue.size());
    }
}
