:: Start ZooKeeper
start cmd /k "%ZOOKEEPER_HOME%\bin\zkserver.cmd"
timeout 2

:: Start Kafka
start cmd /k "%KAFKA_HOME%\bin\windows\kafka-server-start.bat %KAFKA_HOME%\config\server.properties"

