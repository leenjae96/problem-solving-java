package com.leenjae.programmers.algorithm.bruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 모음사전
 *
 * @author leenjae96
 */
public class VowelDictionary {
    HashSet<String> wordSet;
    String[] vowels;

    public int solution(String word) {
        vowels = new String[]{"A", "E", "I", "O", "U"};
        initSet();
        List<String> sortedList = wordSet.stream()
                .sorted()
                .collect(Collectors.toList());

        return sortedList.indexOf(word) + 1;
    }

    void initSet() {
        wordSet = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            wordSet.addAll(makeWord(i));
        }
    }

    List<String> makeWord(int length) {
        List<String> list = new ArrayList<>();
        if (length == 1) {
            return Arrays.asList(vowels);
        } else {
            for (String s : vowels) {
                for (String word : makeWord(length - 1)) {
                    list.add(s + word);
                }
            }
        }
        return list;
    }


    //DFS를 통한 방법.
    List<String> list = new ArrayList<>();
    void dfs(String str, int len) {
        if(len > 5) return;
        list.add(str);
        for(int i = 0; i < 5; i++) dfs(str + "AEIOU".charAt(i), len + 1);
    }
    public int solution2(String word) {
        dfs("", 0);
        return list.indexOf(word);
    }
}

