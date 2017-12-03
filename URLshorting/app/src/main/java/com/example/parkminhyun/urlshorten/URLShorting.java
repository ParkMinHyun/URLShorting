
package com.example.parkminhyun.urlshorten;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ParkMinHyun on 2017-12-03.
 */

public class URLShorting {

    private HashMap<String, String> keyMap;   // 입력 url map
    private HashMap<String, String> valueMap; // redirect하기 위한 map

    private String shortenDomain = "http://minhyun.com/";
    private Random myRand; // 랜덤 숫자를 위한 변수
    private char shortenKey[]; // 랜덤 문자 생성을 위한 변수

    private static final int keyLength = 8; // shortening 의 결과 값은 8문자

    public URLShorting() {
        keyMap = new HashMap<String, String>();
        valueMap = new HashMap<String, String>();
        myRand = new Random();

        shortenKey = new char[62];
        for (int i = 0; i < 62; i++) {
            int j = 0;

            // 0~9 index엔 숫자
            if (i < 10)
                j = i + 48;

            // 10~35 index엔 대문자(A~Z)
            else if (i > 9 && i <= 35)
                j = i + 55;

            // 36~62 index엔 소문자(a~z)
            else
                j = i + 61;

            shortenKey[i] = (char) j;
        }
    }

    // URL Sorting Method
    public String shortenURL(String url) {
        String shortURL = "";

        // url 유효 검사
        if (validateURL(url)) {

            // url substring 기능 ex) http://만 남도록
            String transformedURL = subStringURL(url);

            // 이미 key에 있는 url인지 확인
            if (valueMap.containsKey(url)) {
                shortURL = shortenDomain + "/" + valueMap.get(transformedURL);
            }
            // 등록된 url이 없으면 새로 생성
            else {
                shortURL = shortenDomain + "/" + getKey(transformedURL);
            }
        }

        return shortURL;
    }

    // URL 예외처리 검사 기능 Method - 아직 미구현
    public boolean validateURL(String url) {
        return true;
    }


    String subStringURL(String url) {

        // "http://" 문자 담기
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        // 마지막 '/' 포함 된 경우 삭제
        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);

        return url;
    }

    // url에 대한 key 부여 Method
    private String getKey(String url) {
        String key;

        // key 생성
        key = generateKey();
        // url shorten을 위한 hashmap에 key,url 담기
        keyMap.put(key, url);
        // redirect를 위한 hashmap에 url,key 담기
        valueMap.put(url, key);
        return key;
    }

    // Key 생성 Method
    private String generateKey() {
        String key = "";
        boolean flag = true;

        // 중복되는 shorten url이 없을 때 까지 반복
        while (flag) {
            key = "";
            // key값 생성
            for (int i = 0; i < keyLength; i++) {
                key += shortenKey[myRand.nextInt(62)];
            }
            // 만든 key가 없는 key일 경우 반복문 종료
            if (!keyMap.containsKey(key)) {
                flag = false;
            }
        }
        return key;
    }

}