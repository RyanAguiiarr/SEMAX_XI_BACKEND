# ESTÁGIO 1: Build da Aplicação com Maven
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copia o conteúdo da pasta aninhada correta para o diretório de trabalho
COPY SEMAC_BACKEND/SEMAC_BACKEND/ .

# Dá permissão de execução para o script do Maven Wrapper
RUN chmod +x ./mvnw

# Baixa todas as dependências do projeto
RUN ./mvnw dependency:go-offline

# Compila a aplicação e gera o arquivo .jar, pulando os testes
RUN ./mvnw package -DskipTests

# ESTÁGIO 2: Imagem Final de Execução
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final
COPY --from=builder /app/target/SEMAC_BACKEND-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Render irá usar (geralmente definida pela variável PORT)
EXPOSE $PORT

# Comando para iniciar a aplicação
# Remove a configuração manual da datasource URL - deixe o Spring usar as variáveis de ambiente
ENTRYPOINT ["java", "-jar", "app.jar"]