# Spring 과제

## 과정 1 : 인텔리제이 설치 및 jdk 17 설치, spring initializer를 이용하여 프로젝트 다운
- https://gymdev.tistory.com/72를 참고하여 환경변수 설정함.
#### 난관 : JAVAVIRTUALMACHINE 폴더에 jdk폴더를 넣고 싶어서 맥의 '폴더로 이동'기능을 사용! 
- https://support.apple.com/ko-kr/guide/mac-help/mchlp1236/mac
- 이런 것도 있구나. 맥이란 해도해도 신기해요! 언제쯤 맥OS를 점령할지...



## 과정 2 : 코드 작성
### 난관 1 : symbol not find 문제 
--> 필요한 라이브러리를 import했더니 'web'이라는 symbol를 찾지 못했다고 뜬다.
==> 해결 : https://www.goodsource.co.kr/125 참고
- build.grandle의 
    implementation 'org.springframework.boot:spring-boot-starter'를
    implementation 'org.springframework.boot:spring-boot-starter-web'으로 수정하고 빌딩하고 실행!


### 난관 2 : java: cannot find symbol
이번엔 email symbol을 찾지 못하고 에러를 낸다.
<img width="611" alt="스크린샷 2023-11-03 오후 11 37 56" src="https://github.com/GDSC-Ewha-5th/GDSC-Server-5th/assets/78548833/9c0bde23-b9b8-48a5-986f-71382695d518">

==> 해결 : 당연함... 내가 email 선언 안해줌... String email을 추가해줬다! ㅎㅎ
해결~


### 난관 3 : localhost:8080 을 실행했으나 메인 페이지가 나타나지 않았다.
<img width="631" alt="스크린샷 2023-11-03 오후 11 44 20" src="https://github.com/GDSC-Ewha-5th/GDSC-Server-5th/assets/78548833/23b76048-ced5-4ea0-8ed9-d41077fcc3ce">

- 참고 : https://devmango.tistory.com/97

아직 해결하지 못했습니다. 시험이 어제 끝나서 늦게 시작했습니다. 자정이 넘더라도 마저 수정하겠습니다. 죄송합니다..ㅠㅠㅠ




---
SampleController.java 소스 코드 :

```
    package mvcstudy.mvcstudy.controller;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    
    
    @Controller
    public class SampleController {
        @GetMapping("/")
        public String sample(Model model) {
            model.addAttribute("description",
                    "메인 페이지 입니다.");
            return "index";
        }
        @GetMapping("/members")
        public String members(Model model) {
            model.addAttribute("member1", "Yang");
            model.addAttribute("member2", "Dong");
            model.addAttribute("member3", "Seon");
            return "members";
        }
    
        @GetMapping("/members/new")
        public String showNewMember(@RequestParam(name=
                "name", defaultValue = "Guest")String name, String email,
                              Model model){
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            return "newMember";
        }
    }
```




