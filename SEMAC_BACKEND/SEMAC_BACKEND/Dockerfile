# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem que já contém o JDK 21 e o Maven para compilar o projeto.
FROM eclipse-temurin:21-jdk-jammy as builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o arquivo pom.xml primeiro para aproveitar o cache de camadas do Docker.
# As dependências só serão baixadas novamente se o pom.xml mudar.
COPY pom.xml .

# Baixa todas as dependências do projeto.
RUN mvn dependency:go-offline

# Copia todo o código-fonte do projeto para o diretório de trabalho.
COPY src ./src

# Compila a aplicação e gera o arquivo .jar, pulando os testes.
RUN mvn package -DskipTests

# Estágio 2: Imagem Final de Execução
# Usamos uma imagem mais leve, contendo apenas o Java Runtime Environment (JRE),
# que é suficiente para executar a aplicação.
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho para a execução.
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final.
# O JAR é renomeado para app.jar para facilitar a execução.
COPY --from=builder /app/target/SEMAC_BACKEND-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot.
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado.
ENTRYPOINT ["java", "-jar", "app.jar"]