# Bella Bank
An API to transfer money between accounts.

## Stack

Bella bank is built using:

* Gradle, an open-source build automation system. Feel free to use the `gradlew` on bash or `gradlew.bat` on windows cmd.
* Jersey, an open source framework for developing RESTful Web Services in Java
* Jetty, a Java HTTP (Web) server and Java Servlet container.
* H2 a relational database management system written in Java.

## Requirements

The following software is required to be installed locally in order to get this project running:

* Java 11

## Run the project

1. Clone from github (this will create bellaBank in the current directory)
```
git clone https://github.com/lauradebella/bellaBank
cd bellaBank
```

3. Build the project
```
./gradlew build
```

4. Run the project
```
java -jar build/libs/bellaBank.jar

OR

./gradlew run
```

## Try it

Create an account:

```bash
curl -XPOST localhost:8080/account -H 'Content-Type: application/json' -d '{
    "passportNumber": "PSDHJA",
    "name": "Laura"
}'
```

Make a transaction:

```bash
curl -XPOST localhost:8080/transaction -H 'Content-Type: application/json' -d '{
    "originAccountId": 1,
    "destinationAccountId": 2,
    "value": 10
}'
```

Since you need balance to transfer money between accounts, there is a seed script that create two accounts and put balance on them. 

Account with id = 1 have 100.00 of balance and account with id = 2 have 170.00 of balance to be used. 


[No curl?](https://onlinecurl.com/)

You can also use the postman collection on /postman to reach the endpoints using postman.