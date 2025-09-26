# ESTÁGIO 1: Build da Aplicação com Maven
# Usamos uma imagem com o JDK completo para compilar o código.
FROM eclipse-temurin:21-jdk-jammy AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o conteúdo da pasta aninhada correta para o diretório de trabalho.
# O caminho correto no repositório é SEMAC_BACKEND/SEMAC_BACKEND/
COPY SEMAC_BACKEND/SEMAC_BACKEND/ .

# Damos permissão de execução para o script do Maven Wrapper.
RUN chmod +x ./mvnw

# Baixa todas as dependências do projeto.
# Usamos ./mvnw em vez de mvn.
RUN ./mvnw dependency:go-offline

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

# --- CORREÇÃO APLICADA AQUI ---
# Comando para iniciar a aplicação quando o contêiner for executado.
# Usamos "sh -c" para poder adicionar o prefixo "jdbc:" à URL do banco de dados
# fornecida pela variável de ambiente do Render ($SPRING_DATASOURCE_URL).
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.datasource.url=jdbc:${SPRING_DATASOURCE_URL} app.jar"]

