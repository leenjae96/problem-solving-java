package com.leenjae.programmers.hash;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 프로그래머스 - 해시 - level2
 * 전화번호목록
 *
 * @author leenjae96
 */
public class PhoneNumberList {
    public boolean solution(String[] phone_book) {
        if (phone_book.length == 1) return true;
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());

        int shortest = phone_book[0].length();

        HashSet<String> hashSet = new HashSet<>();
        for (String number : phone_book) {
            String slicedNumber = null;
            for (int i = shortest; i <= number.length(); i++) {
                slicedNumber = number.substring(0, i);
                if (hashSet.contains(slicedNumber)) {
                    return false;
                }
            }
            hashSet.add(slicedNumber);
        }
        return true;
    }
}
