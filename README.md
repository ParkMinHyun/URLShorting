# URLShorting

## ● Simulation 
![simulation](https://user-images.githubusercontent.com/18719563/33522816-f4dc07cc-d839-11e7-9ef9-a917745483e6.gif)

## ● Code
`````````````
// 키 생성 초기화
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
`````````````````
````````````````
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
````````````````

`````````````
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
`````````````````
