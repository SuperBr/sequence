FROM frekele/java
VOLUME /tmp
ADD app.jar app.jar
ADD lib lib
RUN bash -c 'touch app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar app.jar" ]