FROM maven:3.8.1-openjdk-17-slim

WORKDIR /app

COPY . .


RUN chmod -R 777 /app

CMD ["mvn", "clean", "test"]
