echo 'wait Building package......'
mvn clean package
echo 'Run server @ http:localhost:8080........'
java -jar  cab-aggregator-webservice/target/cab-aggregator-webservice-1.0.0.jar server service-config.yml
