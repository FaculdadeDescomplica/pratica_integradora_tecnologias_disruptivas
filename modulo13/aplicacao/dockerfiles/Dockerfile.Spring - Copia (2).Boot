FROM eclipse-temurin:11

#Definindo o diretorio de trabalho - onde fica o .jar da aplicacao
WORKDIR /app

#Instalando o bash
RUN /bin/sh -c "apt-get install bash"

RUN apt-get update && apt-get install -y procps

#Copiando o conteudo da pasta targe, que contem o .jar, para a pasta app
COPY ./backend/target .

#Apagando os arquivos, exceto o .jar e alterando o nome do .jar para application.jar
RUN bash -O extglob -c 'rm -rfv !(*.jar)' && mv *.jar application.jar 

RUN ls -la

RUN pwd

#Definindo o ponto de entrada/executando a aplicacao ao iniciar o backend
ENTRYPOINT ["java", "-jar", "application.jar"]