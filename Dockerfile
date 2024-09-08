FROM maven:3.8.1-openjdk-17-slim

WORKDIR /app

COPY . .

# Garante que o usuário Maven tenha permissões no diretório /app
RUN chown -R 1000:1000 /app

# Muda para o usuário não-root
USER 1000

CMD ["mvn", "clean", "test"]