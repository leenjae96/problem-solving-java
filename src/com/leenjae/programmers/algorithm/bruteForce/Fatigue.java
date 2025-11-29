package com.leenjae.programmers.algorithm.bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 피로도
 *
 * @date 25.11.14
 * @author leenjae96
 */
public class Fatigue {
    //DFS로 문제해결
    //던전의 갯수를 이미 앎으로 visited 에 방문 여부 체크해가며
    //가장 안까지 도달했을때 그 값을 max에 세팅.
    int MAX=0;
    boolean[] VISITED;

    public int solution(int k, int[][] dungeons) {
        VISITED = new boolean[dungeons.length];

        dfs(k, dungeons, 0);

        return MAX;
    }

    void dfs(int myFatigue, int[][] dungeons, int count) {
        for(int i=0; i<dungeons.length; i++) {
            if(VISITED[i]) continue;

            int needTo = dungeons[i][0];
            int willUse = dungeons[i][1];

            if(myFatigue < needTo) continue;

            VISITED[i] = true;
            dfs(myFatigue-willUse, dungeons, count + 1);
            VISITED[i] = false;
        }
        MAX = Math.max(MAX, count);
    }
}

/**
 * 프로그래머스 - 완전탐색 - level2
 * 피로도
 * @date 25.11.01
 * @author leenjae96
 */
class Fatigue2 {
    //주어진 던전의 길이를 가지고 그 안에서 갈 수 있는 모든 조합을 만듭니다. -> makeOrderList
    //조합을 만들 때 combine을 가지고 만듭니다
    //orderList를 다 돌며 max를 계산해줍니다.
    public int solution(int k, int[][] dungeons) {
        List<Integer> needFatigueList = new ArrayList<>();
        List<Integer> usingfFatigueList = new ArrayList<>();
        for (int[] dungeon : dungeons) {
            needFatigueList.add(dungeon[0]);
            usingfFatigueList.add(dungeon[1]);

        }
        List<String> orderList = makeOrderList(needFatigueList.size());

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

    List<String> makeOrderList(int length) {
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
