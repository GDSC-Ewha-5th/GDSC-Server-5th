# GDSC-Server-5th

GDSC 5기 `Server` 파트의 레포지토리입니다.  
맴버들이 스터디 과제를 업로드하고, 코드 리뷰를 하는 공간으로 사용됩니다.

---

## 📏 스터디 룰

- 스터디 출석은 스터디 시작 시간 30분 전까지는 정상 출석, 그 이후에는 결석
    ex) 1시부터 수업인 경우, 1시 29분까지는 정상 출석, 그 이후엔 결석
- 과제는 다음 스터디 날짜 전날 자정까지 마감
    ex) 9월 30일이 2주차 스터디 날이라면, 9월 29일 자정까지 마감

## 📌 과제 패널티

미수행 시 결석 1번으로 카운트됩니다.
단, 과제를 푸는 데 성공하지 못했더라도 풀기 위해 노력한 과정을 성의있게 서술해주시면 과제를 제출한 것으로 카운트합니다.

## 📒 과제 형식
- 마크다운 문서: 인증 사진+과제를 해결한 방법 또는 해결하려고 노력한 방법 서술
- 소스 코드: 소스 코드 또는 미완성된 소스 코드와 주석

## 🤙 과제 리뷰 방식
1. 본인 이름으로 `branch` 만들기(ex: 홍길동)
2. 본인 `branch`에 과제 업로드(commit & push) 후, `pull request` 날리기
3. 피드백(코드 리뷰) 후 main에 `merge`

## 📁 폴더 구조
과제하면서 다른 분들의 과제도 보시라고, 같은 주차 폴더 안에 모든 맴버들의 과제를 업로드합니다. 

```html
📁 1주차 Server S-Day 과제 <!--[과제 주차]-->
ㄴ 📄 홍길동_1주차_과제 <!--[이름]_[과제 주차]_과제-->
ㄴ 📄 김철수_1주차_과제 <!--[이름]_[과제 주차]_과제-->
ㄴ ...
```

## ✉️ 커밋 메시지

```html
[홍길동] 1주차 과제 <!--[[이름]] [과제 주차]-->
[홍길동] 1주차 과제 - complete <!--과제 풀기 완료시-->
[홍길동] 1주차 과제 - ing <!--아직 풀고 있는 중 또는 미완료-->
```

## 📬️ PR 형식

```html
#### 제출 상태
> 1주차 과제 complete <!--과제 풀기 완료시-->
> 1주차 과제 ing <!--아직 풀고 있는 중 또는 미완료-->

#### help
> 200 OK <!--complete의 경우 200 OK-->
> ~부분에서 에러 발생 <!--ing의 경우 해결 못한 부분을 간략히 설명-->
```