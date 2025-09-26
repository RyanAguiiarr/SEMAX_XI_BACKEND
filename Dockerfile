# ESTÁGIO 1: Build da Aplicação com Maven
# Usamos uma imagem com o JDK completo para compilar o código.
FROM eclipse-temurin:21-jdk-jammy AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia os arquivos essenciais do Maven para o contêiner.
# O wrapper (mvnw) é a forma recomendada de executar o Maven.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# --- CORREÇÃO APLICADA AQUI ---
# Damos permissão de execução para o script do Maven Wrapper.
RUN chmod +x ./mvnw

# Baixa todas as dependências do projeto.
# Usamos ./mvnw em vez de mvn.
RUN ./mvnw dependency:go-offline

# Copia todo o código-fonte do projeto para o diretório de trabalho.
COPY src ./src

# Compila a aplicação e gera o arquivo .jar, pulando os testes.
# Usamos ./mvnw em vez de mvn.
RUN ./mvnw package -DskipTests


# ESTÁGIO 2: Imagem Final de Execução
# Usamos uma imagem JRE, que é menor e mais segura, apenas para rodar a aplicação.
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final.
# O nome do JAR deve corresponder ao <artifactId> e <version> do seu pom.xml.
COPY --from=builder /app/target/SEMAC_BACKEND-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 para que a aplicação possa receber tráfego.
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado.
ENTRYPOINT ["java", "-jar", "app.jar"]

