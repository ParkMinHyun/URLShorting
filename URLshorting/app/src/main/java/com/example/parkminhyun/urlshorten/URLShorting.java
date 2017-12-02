package com.example.parkminhyun.urlshorten;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ParkMinHyun on 2017-12-03.
 */

public class URLShorting{

        private HashMap<String, String> keyMap;
        private HashMap<String, String> valueMap;

        public URLShorting() {
            keyMap = new HashMap<String, String>();
            valueMap = new HashMap<String, String>();
        }

        public URLShorting(int keyLength, String newDomain) {
            this();
        }


}