# 1단계: Build 단계
FROM gradle:8.7.0-jdk21-alpine AS build
WORKDIR /app

# 필요한 파일만 복사 (build 속도 최적화)
COPY build.gradle settings.gradle ./
COPY src ./src

# 의존성 캐싱
RUN gradle build --no-daemon

# 2단계: Run 단계
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 빌드된 jar 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 기본값 (필요없으면 지우기)
ENV SPRING_PROFILES_ACTIVE=dev

# 필요한 포트 열기 (Spring Boot 기본 8080)
EXPOSE 9999

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
