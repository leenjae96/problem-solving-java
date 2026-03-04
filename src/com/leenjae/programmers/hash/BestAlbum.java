package com.leenjae.programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 해시 - level3
 * 베스트앨범
 *
 * @author leenjae96
 */
public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        int length = genres.length;
        HashMap<String, Integer> forRankMap = new HashMap<>();

        HashMap<String, Integer> orderMap1 = new HashMap<>();
        HashMap<String, Integer> orderMap2 = new HashMap<>();
        for (int i = 0; i < length; i++) {
            String genre = genres[i];
            int playCount = plays[i];

            forRankMap.put(genre, forRankMap.getOrDefault(genre, 0) + playCount);

            if (orderMap1.containsKey(genre)) {
                int firstPlayOrder = orderMap1.get(genre);
                int secondPlayOrder = orderMap2.get(genre);
                if (playCount > plays[firstPlayOrder]) {
                    orderMap1.put(genre, i);
                    orderMap2.put(genre, firstPlayOrder);
                } else { // 2번째에 넣거나 말거나
                    if (firstPlayOrder == secondPlayOrder || playCount > plays[secondPlayOrder]) {
                        orderMap2.put(genre, i);
                    }
                }
            } else {
                orderMap1.put(genre, i);
                orderMap2.put(genre, i);
            }

        }

        List<String> sortedKeys = forRankMap.keySet().stream()
                .sorted((o1, o2) -> forRankMap.get(o2) - forRankMap.get(o1))
                .collect(Collectors.toList());
        List<Integer> answerList = new ArrayList<>();
        for (String genre : sortedKeys) {
            Integer play1 = orderMap1.getOrDefault(genre, null);
            Integer play2 = orderMap2.getOrDefault(genre, null);

            answerList.add(play1);
            if (play1 != play2) {
                answerList.add(play2);
            }
        }

        int[] answer = new int[answerList.size()];
        for (
                int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
