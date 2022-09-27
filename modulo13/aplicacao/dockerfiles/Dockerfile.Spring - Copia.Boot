FROM eclipse-temurin:11

#Definindo o diretorio de trabalho - onde fica o .jar da aplicacao
#WORKDIR /app

RUN bash -O extglob -c 'rm -rfv /app/*'

COPY ./backend/target ./app/target

#RUN ls -la app/target

#RUN pause

#Deletando o conteudo copiado da pasta target para a pasta app, exceto o .jar
#RUN /bin/bash shopt -s extglob
#RUN /bin/bash rm -rf !(*.jar)
#RUN /bin/bash shopt -u extglob

#RUN /bin/bash -c shopt -s extglob  
#RUN /bin/bash -c rm -rfv !(".jar")
#RUN /bin/bash -c shopt -u extglob  

RUN bash -O extglob -c 'rm -rfv /app/target/!(*.jar)' && mv /app/target/*.jar /app/target/application.jar && ls -la /app/target

#RUN ls -la /app

#Alterando o nome do .jar para application.jar
#RUN mv /app/*.jar /app/application.jar

#ENTRYPOINT ["java", "-jar", "/app/target/application.jar"]