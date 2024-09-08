# Use a imagem do Maven com base no Debian, que inclui apt-get
FROM maven:3.8.1-openjdk-17-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o conteúdo do projeto para o diretório de trabalho
COPY . .

# Instalar wget, unzip e Allure
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://github.com/allure-framework/allure2/releases/download/2.30.0/allure-2.30.0.zip \
    && unzip allure-2.30.0.zip -d /opt \
    && ln -s /opt/allure-2.30.0/bin/allure /usr/local/bin/allure \
    && rm allure-2.30.0.zip

# Comando para rodar os testes e gerar o relatório do Allure
CMD ["mvn", "clean", "test"]
