<h1 align="center">Order API</h1>

## ✨ Technologies

This project was developed using the following technologies:

- [SpringBoot](https://spring.io)

- [Hibernate](https://hibernate.org/)

- [PostgreSQL](https://www.postgresql.org/)

- [H2Database](https://www.h2database.com/)

- [Docker](https://www.docker.com/)

## 💻 Project

simple order api that you can create, read, update and delete items,
orders, with relationship.

## 🤠 Documentation

- You will need to have Insomnia API client installed at your computer to use the JSON file and checkout the documentation.

You can download [here](https://insomnia.rest/download)

- The documentation of this project is available on file [insomnia](./insomnia/order-api.json)

## 🚀 How to run

- You will need to have JDK17 and maven installed at your computer.

You can download JDK [here](https://www.oracle.com/java/technologies/downloads/)

You can download Maven [here](https://maven.apache.org/surefire/download.cgi)

🐳 First of all run PostgreSQL as a service with Docker

- You will need to have Docker installed at your computer and it need to be running.

You can download [here](https://docs.docker.com/desktop/windows/install/)

```bash
# Create a postgresql container running the following command
$ docker run
/ --name postgres
/ -e POSTGRES_USER=admin
/ -e POSTGRES_PASSWORD=admin
/ -e POSTGRES_DB=order_api
/ -p 5432:5432
/ -d
/ postgres

#Start the docker container
$ docker start postgres
```
- On You project folder, open the command line and type:

```bash
$ mvn install -DskipTests

$ java -jar target/order-closing-api-0.0.1-SNAPSHOT.jar

# Await the process on the command line to end.
# Now the API it's available at `http://localhost:8080/api

```
