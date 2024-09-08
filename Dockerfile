FROM maven:3.8.1-openjdk-17-slim

WORKDIR /app

COPY . .

# Garantir que todo o diretório tenha permissões adequadas
RUN chmod -R 777 /app

CMD ["mvn", "clean", "test"]
