
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

}