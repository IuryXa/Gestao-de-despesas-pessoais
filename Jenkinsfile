FROM gradle:7.4.2-jdk11 as builder

WORKDIR /usr/src/app

COPY . .

RUN gradle clean assembleRelease

FROM openjdk:11-jre-slim

WORKDIR /usr/src/app

COPY --from=builder /usr/src/app/app/build/outputs/apk/release/app-release.apk .

CMD ["java", "-jar", "app-release.apk"]
