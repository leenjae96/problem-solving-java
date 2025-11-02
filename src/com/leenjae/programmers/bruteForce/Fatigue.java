package com.leenjae.programmers.bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 피로도
 *
 * @author leenjae96
 */
public class Fatigue {
    public int solution(int k, int[][] dungeons) {
        List<Integer> needFatigueList = new ArrayList<>();
        List<Integer> usingfFatigueList = new ArrayList<>();
        for (int[] dungeon : dungeons) {
            needFatigueList.add(dungeon[0]);
            usingfFatigueList.add(dungeon[1]);

        }
        List<String> orderList = getOrderList(needFatigueList.size());

        int answer = 0;
        for (String order : orderList) {
            int myFatigue = k;
            int visit = 0;
            for (String index : order.split("")) {
                if (myFatigue >= needFatigueList.get(Integer.parseInt(index))) {
                    myFatigue = myFatigue - usingfFatigueList.get(Integer.parseInt(index));
                    visit++;
                } else {
                    break;
                }
            }
            if (visit > answer) {
                answer = visit;
            }
        }
        return answer;
    }

    List<String> getOrderList(int length) {
        List<String> orderList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            orderList.add(String.valueOf(i));
        }
        return combine(length, orderList);
    }

    List<String> combine(int length, List<String> list) {
        if (length == 1) {
            return list;
        }

        List<String> combineList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<String> copy = new ArrayList<>(list);
            String prefix = copy.get(i);
            copy.remove(i);

            combineList.addAll(combine(length - 1, copy).stream()
                    .map(x -> prefix + x)
                    .collect(Collectors.toList()));
        }
        return combineList;
    }
}
