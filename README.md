# 🚀 Petstore API Automation

Projeto de testes automatizados para a API Petstore, desenvolvida em Java com as bibliotecas RestAssured e JUnit. O gerenciamento de dependências com o Maven. O projeto utiliza o framework Allure para a geração de relatórios de testes e está configurado para rodar em containers Docker, além de se integrar ao GitHub Actions para CI/CD.

## 🧪 Testes

Os testes cobrem diferentes cenários da Petstore API, validando suas principais funcionalidades:

* **Criação de Pedidos (Order Creation):** Verifica a criação de novos pedidos de pets, garantindo que os campos obrigatórios (ID, quantidade, status) sejam fornecidos corretamente.
* **Atualização de Pets (Pet Update):** Valida a atualização de informações de pets, como nome, status e URLs de fotos, garantindo a consistência dos dados.
* **Consulta de Pets por ID Inexistente (Non-existent Pet Search):** Avalia o comportamento da API ao buscar um pet inexistente, retornando o erro esperado (404).
* **Consulta de Pets por Status (Pet Status Search):** Valida a busca de pets por status (disponível, pendente, vendido), assegurando que a API retorne os pets corretos.

Os cenários cobrem fluxos principais e tratam tanto de casos de sucesso quanto de erro.

## 🏛️ Arquitetura de Testes

A arquitetura de testes segue o padrão de camadas de serviço, facilitando a escalabilidade e manutenção:

* **Configurações (Config):** Centraliza as definições de URL base e parâmetros.
* **Modelos (Models):** DTOs usados nas requisições e respostas da API.
* **Serviços (Services):** Realizam as chamadas à API, encapsulando a lógica de comunicação com a API e abstraindo a implementação, facilitando a reutilização.
* **Testes (Tests):** Utilizam os serviços e modelos para garantir modularidade, reutilização e a validação correta dos cenários de teste.

...

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **JUnit**: Framework de testes utilizado em conjunto com o RestAssured
* **RestAssured**: Biblioteca para testes de API REST
* **Maven**: Gerenciamento de dependências e build do projeto
* **Allure**: Framework para geração de relatórios de testes
* **Docker**: Execução dos testes em containers
* **GitHub Actions**: Automação de CI/CD

...
## 🧑‍💻 Execução do Projeto

🔧 Pré-requisitos

*  Java Development Kit (JDK) 17 
*  Apache Maven 
*  Allure Command Line 


### 🔧 Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/phillipe77/Petstore-API-Automation.git
   cd Petstore-API-Automation
   ```

2. Execute os testes com Maven:
   ```bash
   mvn clean test
   ```

3. Gere o relatório Allure:
   ```bash
   allure serve allure-results
   ```

## ⚙️ Integração Contínua

Este projeto utiliza **GitHub Actions** para CI/CD. A cada push ou pull request na branch `master`, o workflow realiza:

1. Checkout do código
2. Build da imagem Docker
3. Execução dos testes no container Docker
4. Geração do relatório Allure
5. Upload do relatório como artefato
6. Publicação do relatório no GitHub Pages

O workflow completo está disponível em `.github/workflows/docker-allure.yml`.

## 🌐 Relatório Allure Online

O relatório Allure mais recente pode ser acessado no GitHub Pages:
🔗 [Relatório Allure - Petstore API Automation]()

## 📜 Licença

Este projeto está licenciado sob a **MIT License**. Confira o arquivo [LICENSE](LICENSE) para mais detalhes.