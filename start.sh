mvn clean package -DskipTests
docker rm -f jsf-web-lab3-application
docker rmi -f opi-lab4-jsf-web-lab3-application
docker-compose up -d
open http://localhost:8080/laba3_web
