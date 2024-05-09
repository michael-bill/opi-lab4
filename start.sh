mvn clean package -DskipTests
docker rm -f jsf-web-lab3-application
docker rmi -f opi-lab4-jsf-web-lab3-application
docker-compose up -d
open http://localhost:8080/laba3_web
jconsole -J-Djava.class.path=/Users/mihailbilosickij/Documents/Programs/jboss-cli-client.jar > /dev/null &
# for connect: service:jmx:http-remoting-jmx://localhost:9990
# user: user1, password: Qwerty123
