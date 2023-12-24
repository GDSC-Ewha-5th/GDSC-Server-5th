## 과정
### 1. Entity를 완성하고 test 해보기
<img width="663" alt="스크린샷 2023-12-24 오후 3 04 20" src="https://github.com/GDSC-Ewha-5th/GDSC-Server-5th/assets/78548833/a382738a-7483-4680-8934-0f1e0e2fc180">

- table이 생성된 것을 확인할 수 있다!

</br>

### 2. 코드 작성 (Controller 만들기)
- application.yml, RdsRepository.java, RdsService.java, BookForm.java, RdsController.java 파일 수정하기
- localhost:8080으로 확인한 화면

<img width="319" alt="스크린샷 2023-12-24 오후 5 09 01" src="https://github.com/GDSC-Ewha-5th/GDSC-Server-5th/assets/78548833/b2d92be2-672b-4e8a-9dc4-1a4346473163">

### 3. Controller를 API로 만들기
- Controller를 RestController로 수정
- 결과:
<img width="398" alt="스크린샷 2023-12-24 오후 5 16 47" src="https://github.com/GDSC-Ewha-5th/GDSC-Server-5th/assets/78548833/a22978a2-6a0a-4212-9db5-e18f60b31007">
- PostMan 사용 : get, post test



## 보충 (난관-해결..!)
### 쿼리란?
 - query란 단어의 뜻은 문의하다, 질문하다라는 뜻이고, 이러한 문의는 요청, 즉 '데이터베이스에 정보를 요청하는 일'을 말한다. 정보를 처리하는 과정에서 query를 보내면 이에 따른 정보를 DB로부터 가져온다.
 - 참고 : https://velog.io/@rgfdds98/SQL-query란-무엇일까

