# Spring 과제

## 과정 1 : 인텔리제이 설치 및 jdk 17 설치, spring initializer를 이용하여 프로젝트 다운
- https://gymdev.tistory.com/72를 참고하여 환경변수 설정함.
#### 난관 : JAVAVIRTUALMACHINE 폴더에 jdk폴더를 넣고 싶어서 맥의 '폴더로 이동'기능을 사용! 
- https://support.apple.com/ko-kr/guide/mac-help/mchlp1236/mac
- 이런 것도 있구나... 맥이란 신기한 동네입니다... 언제쯤 맥OS를 점령할지...



## 과정 2 : 코드 작성
### 난관 1 : symbol not find 문제 
--> 필요한 라이브러리를 import했더니 'web'이라는 symbol를 찾지 못했다고 뜬다.
==> 해결 : https://www.goodsource.co.kr/125 참고
- build.grandle의 
    implementation 'org.springframework.boot:spring-boot-starter'를
    implementation 'org.springframework.boot:spring-boot-starter-web'으로 수정하고 빌딩하고 실행!

## 과정 3 : 




