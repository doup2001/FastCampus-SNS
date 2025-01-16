# fc-notification

## 프로젝트 설명
- 인스타그램 알림센터를 참고하여 알림센터를 위한 마이크로서비스를 만들어봅니다.
- 알림 종류: 게시물 댓글 알림, 게시물 좋아요 알림, 팔로우 알림 

## 강의 수강 방법
- 강의를 들으면서 직접 바닥부터 만들어보는 것을 추천합니다.

## 사용 기술
- Java, Spring Boot, Kafka, Redis, Docker Compose, Junit5, Test Container

## 참고
- 마지막 강의에서 Consumer 서비스와 API 서비스를 동시에 실행하는 방법을 학습합니다.
  - 이 과정에서 MongoDB 와 Redis 를 Docker Compose 로 실행하는 방식으로 전환합니다.
- 그 이전까지는 서비스 실행 시 Test Containers 를 사용해 MongoDB 와 Redis 를 실행하고 사용합니다.
  - 이로 인해 Consumer 서비스와 API 서비스는 각각 다른 MongoDB 인스턴스를 사용합니다.

## 알림센터 서비스 실행 방법
1. Docker Desktop 실행
   - Docker Daemon 이 실행되면서 Container 를 실행할 준비를 합니다.
2. Docker Compose 로 Kafka, MongoDB, Redis 실행
   - 명령어: `docker compose -f docker-compose.yaml up -d`
   - 실행파일: [docker-compose.yaml](.docker%2Fdocker-compose.yaml)
   - 카프카 명령어 정리: [kafka-command.md](.kafka%2Fkafka-command.md)
   - - echo '{"type" :"ADD","postId" :1,"userId":2,"commentId":1}' | kafka-console-producer --broker-list localhost:9092 --topic comment
   - - echo '{"type" :"ADD","userId":2,"targerUserId" : 2 ,"createdAt":"2024-01-28T18:35:24.01Z"}' | kafka-console-producer --broker-list localhost:9092 --topic follow
   - - echo '{"type" :"ADD","postId" :1,"userId":2,"createdAt":"2024-01-28T18:35:24.01Z"}' | kafka-console-producer --broker-list localhost:9092 --topic like

3. Consumer 서비스 실행
   - `8081 포트`로 실행됩니다.
   - Swagger 주소: http://localhost:8081/swagger-ui/index.html
4. API 서비스 실행
   - `8080 포트`로 실행됩니다.
   - Swagger 주소: http://localhost:8080/swagger-ui/index.html

## 테스트 실행 방법
- `test profile` 이 활성화됩니다.
- Test Container 를 통해 실행된 MongoDB 와 Redis 인스턴스를 사용하여 독립적인 테스트 환경을 구축합니다.

## MongoDB Index
| 인덱스 생성 쿼리                                                                                               | 설명                                                      |
|------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| db.notifications.createIndex({ "deletedAt":1 }, { "name": "ix_deletedAt","expireAfterSeconds": 0 })        | 90일 지난 알림 삭제 시 사용하는 TTL 인덱스                         |
| db.notifications.createIndex({ userId: 1, occurredAt: -1 }, { name: "ix_userId_occurredAt" })              | 유저별 알림 목록 조회 시 사용하는 인덱스                            |
| db.notifications.createIndex({ userId: 1, lastUpdatedAt: -1 }, { name: "ix_userId_lastUpdatedAt" })        | 신규 알림 여부 조회 시 `latestUpdatedAt`을 구하기 위해 사용하는 인덱스 |
| db.notifications.createIndex({ type: 1, postId: 1 }, { name: "ix_type_postId" })                           | 좋아요 알림 생성/삭제 시 사용하는 인덱스                            |
| db.notifications.createIndex({ type: 1, commentId: 1 }, { name: "ix_type_commentId" })                     | 댓글 알림 삭제 시 사용하는 인덱스                                  |
| db.notifications.createIndex({ type: 1, userId: 1, followerId: 1 }, { name: "ix_type_userId_followerId" }) | 팔로우 알림 삭제 시 사용하는 인덱스                               |

## 강의 수강 이후 해보면 좋을 것들
- Controller, Service 클래스 테스트 코드 짜보기
- 예외처리 추가해보기
- latestUpdatedAt 값도 lastReadAt 과 함께 Redis 에 캐싱하도록 변경해보기
  - 신규 알림 여부 조회 API 는 Redis 만 조회하게 됨