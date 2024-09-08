# imagem base do Maven com Java 17
FROM maven:3.8.1-openjdk-17

WORKDIR /app
COPY . .

# Instalar Allure
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://github.com/allure-framework/allure2/releases/download/2.30.0/allure-2.30.0.zip \
    && unzip allure-2.30.0.zip -d /opt \
    && ln -s /opt/allure-2.30.0/bin/allure /usr/local/bin/allure \
    && rm allure-2.30.0.zip

# Baixar dependências do Maven
RUN mvn dependency:go-offline

# Comando para executar testes e gerar relatório
CMD ["sh", "-c", "mvn clean test && allure generate target/allure-results --clean -o allure-report"]