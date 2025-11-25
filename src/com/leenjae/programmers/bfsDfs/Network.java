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

    public int solution2(int n, int[][] computers) {
        int answer = 0;

        // 1 visited[] 로 해당 computer 가 이미 체크 된지 확인
        // 2 해당 컴퓨터와 연결되어있는 computer 들 모두 queue에 추가
        // 3 queue에서 꺼내 2번작업
        // 4 queue작업이 끝나면 visited를 돌면서 아직 visited안한 것으로 다시 2번

        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            bfs(n, i, computers, visited);
            answer++;
        }

        return answer;
    }

    void bfs(int size, int start, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next = 0; next < size; next++) {
                if (computers[cur][next] == 0 || visited[next]) continue;
                queue.add(next);
                visited[next] = true;
            }
        }
    }
}
