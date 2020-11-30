FROM adoptopenjdk/openjdk11
RUN apt-get update && apt-get install libgtk-3-0 libglu1-mesa xvfb -y && apt-get update
COPY app.jar app.jar
RUN apt-get install xvfb
ENV DISPLAY=:99
ADD run.sh /run.sh
RUN chmod a+x /run.sh
CMD /run.sh