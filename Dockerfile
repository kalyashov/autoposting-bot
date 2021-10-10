ARG DOCKER_REGISTRY="registry.redhat.io"

FROM ${DOCKER_REGISTRY}/openjdk/openjdk-11-rhel8

COPY build/libs/*.jar autoposting-bot.jar

ENTRYPOINT java -Dfile.encoding=UTF-8 -jar autoposting-bot.jar
EXPOSE 8080