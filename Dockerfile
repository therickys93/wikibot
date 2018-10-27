FROM alpine
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_ALPINE_VERSION 8.181.13-r0
RUN apk add --no-cache bash openjdk8="$JAVA_ALPINE_VERSION"
ENV GRADLE_VERSION 3.2.1
ENV GRADLE_HOME /usr/local/gradle
ENV PATH ${PATH}:${GRADLE_HOME}/bin
WORKDIR /usr/local
RUN apk update && apk add bash libstdc++ && rm -rf /var/cache/apk/*
ADD . /app
WORKDIR /app
RUN ./gradlew clean stage
CMD /bin/bash run.sh
