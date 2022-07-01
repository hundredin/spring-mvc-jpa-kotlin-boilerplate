# Boilerplate

## Using
- Kotlin 1.6.21
- JDK 17
- Spring Framework
  - Spring Boot Web
  - Spring Boot Data JPA
- Test Framework
  - KoTest
  - Mockk
- Database Migration
  - liquibase 

## Run spring-boot application
```shell
./gradlew bootRun
```

## Coding Convention
- [ktlint](https://github.com/jlleitschuh/ktlint-gradle)
- [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle)

```shell
# 아래 명령어를 수행하여 git commit 전에 코드 포맷이 될 수 있도록 권장
./gradlew addKtlintFormatGitPreCommitHook 
```