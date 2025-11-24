package com.leenjae.programmers.bfsDfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level3
 * 여행경로
 *
 * @author leenjae96
 * @date 25.11.14
 */
public class TravelRoute {
    //아래는 여행최단경로
    Map<String, List<String>> airport = new HashMap<>();
    public String[] solution(String[][] tickets) {
        int length = tickets.length;
        for(String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];

            List<String> destinations = airport.get(from);
            if(destinations == null) {
                destinations = new ArrayList<>();
                airport.put(to, destinations);
            }
            destinations.add(to);
        }



        String[] answer = {};
        return answer;
    }

    void dfs(String before, String now, Map<String, List<String>> airport) {

    }
}
