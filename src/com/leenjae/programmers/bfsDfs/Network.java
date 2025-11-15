package com.leenjae.programmers.bfsDfs;

import java.util.*;

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level3
 * 네트워크
 *
 * @date 25.11.12
 * @author leenjae96
 */
public class Network {
    List<Integer>[] connections;
    public int solution(int n, int[][] computers) {
        connections = new List[n];
        for (int i = 0; i < n; i++) {
            connections[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    Integer left = i;
                    Integer right = j;

                    connections[left].add(right);
                    connections[right].add(left);
                }
            }
        }

        boolean[] reached = new boolean[n];
        Arrays.fill(reached, false);
        Queue<Integer> queue = new ArrayDeque<>();
        int answer = 0;
        for(int j = 0 ; j < n ; j ++) {
            if(reached[j]) continue;

            reached[j] = true;
            queue.add(j);
            while(!queue.isEmpty()) {
                int now = queue.poll();

                for(int i = 0 ; i < connections[now].size() ; i++) {
                    int next = connections[now].get(i);
                    if(reached[next]) continue;

                    reached[next] = true;
                    queue.add(next);
                }
            }

            answer++;
        }
        return answer;
    }
}
