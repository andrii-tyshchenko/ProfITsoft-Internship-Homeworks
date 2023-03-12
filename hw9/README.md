1) mvn clean package
2) docker build -f Dockerfile -t hw9:0.0.1 .
3) docker run -it -d --env-file=.env -p 8080:8080 hw9:0.0.1
4) docker-compose up --build --detach

Якщо просто запускати проект - все працює (в application.properties 
треба розкоментувати spring.mail), а якщо через контейнер - Exception 
encountered during context initialization: ... Error creating bean with name 'sendEmailListener'