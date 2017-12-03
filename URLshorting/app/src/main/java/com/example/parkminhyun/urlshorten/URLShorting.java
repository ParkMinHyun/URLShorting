
package com.example.parkminhyun.urlshorten;

import android.webkit.URLUtil;

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
    private static final int keyDomainLength = 12;

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
            String subString_transformedURL = transformedURL.substring(keyDomainLength, transformedURL.length());

            // 이미 key에 있는 url인지 확인
            if (valueMap.containsKey(transformedURL)) {
                shortURL = "(이미 입력한 URL) " + shortenDomain + valueMap.get(transformedURL);
            }
            // 이미 key에 있는 url인지 확인(redirect Url)
            else if (keyMap.containsKey(subString_transformedURL) && !valueMap.containsKey(transformedURL)) {
                shortURL = "(Redirect Url) " + keyMap.get(subString_transformedURL);
            }
            // 등록된 url이 없으면 새로 생성
            else {
                shortURL = shortenDomain + getUrlKey(transformedURL);
            }
        }

        if(shortURL == "")
            shortURL = "잘못된 형식의 URL 입니다.";

        return shortURL;
    }

    // URL 예외처리 검사 기능 Method
    public boolean validateURL(String url) {

        if (URLUtil.isValidUrl(url))
            return true;
        else if(!url.contains("minhyun.com") && !URLUtil.isValidUrl(url))
            return false;

        return true;
    }


    String subStringURL(String url) {

        // "http://" 문자 담기
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        // 마지막 '/' 포함 된 경우 삭제
        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);

        return url;
    }

    // url에 대한 key 부여 Method
    private String getUrlKey(String url) {
        String key;

        // key 생성
        key = createKey();
        // url shorten을 위한 hashmap에 key,url 담기
        keyMap.put(key, url);
        // redirect를 위한 hashmap에 url,key 담기
        valueMap.put(url, key);
        return key;
    }

    // Key 생성 Method
    private String createKey() {
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

    public String convertOriginalURL(String shortURL) {
        String originalURL = "";
        String key = shortURL.substring(shortenDomain.length() + 1);
        originalURL = keyMap.get(key);
        return originalURL;
    }
}