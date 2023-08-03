# wms-app
강의 실습 (이중석 - 스프링부트로 MVP 백엔드 API 빠르게 개발하기(WMS 편))

# 상품등록
- request 는 jakarta Valid 를 사용하여 요청을 검증한다.
- 도메인에서는 springframework util의 Assert를 사용하여 검증한다.
  - 생성될 당시에 검증하면, 생성된 당시에는 pure한 상태임을 보장
  - fast fail: 빨리 시도하고 빨리 실패. 서비스로 구현하기 전에 실패를 확인해서 검증한다. 서비스에서는 비즈니스 로직을 검증해야는데도 벅차다! 
- 