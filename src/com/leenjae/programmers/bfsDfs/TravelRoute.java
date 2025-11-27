package com.leenjae.programmers.bfsDfs;

import java.util.*;

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level3
 * 여행경로
 *
 * @author leenjae96
 * @date 25.11.14
 */
public class TravelRoute {
    int SIZE;
    boolean VISITED[];
    String[][] TICKETS;

    List<String> ANSWERLIST;

    public String[] solution(String[][] tickets) {
        SIZE = tickets.length;
        VISITED = new boolean[SIZE + 1];
        ANSWERLIST = new ArrayList<>();
        Arrays.sort(tickets, Comparator.comparing(o -> o[1]));

        TICKETS = tickets;

        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", path);
        return ANSWERLIST.toArray(new String[SIZE]);
    }

    void dfs(String from, List<String> path) {
        if (!ANSWERLIST.isEmpty()) {
            return;
        }
        if (path.size() == SIZE + 1) {
            ANSWERLIST.addAll(path);
        }

        for (int i = 0; i < SIZE; i++) {
            if (VISITED[i] || !TICKETS[i][0].equals(from)) {
                continue;
            }
            path.add(TICKETS[i][1]);
            VISITED[i] = true;

            dfs(TICKETS[i][1], path);

            path.remove(path.size() - 1);
            VISITED[i] = false;
        }
    }
}
