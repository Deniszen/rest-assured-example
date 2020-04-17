# API test example
API test example with used REST-Assured

Requirements
------------
Must be installed in your system: Docker, Java, Maven, Allure

Quick Start
------------
1. Pull and run docker image
    ```bash 
    docker pull zentreid/zoo:v1.01
    docker run -d -p 8080:8080 --name crud zentreid/zoo:v1.01
    ```
2. Clone project
    ```bash
    git clone git@github.com:Deniszen/rest-assured-example.git
    ```
3. Run test
    ```bash
    mvn clean test
    ```
4. Execute for view report
    ```bash
    allure serve path/to/roject/target/allure-results
    ```