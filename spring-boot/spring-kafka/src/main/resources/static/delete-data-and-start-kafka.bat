:: @echo off

:: Delete all old data
rmdir /s/q "%ZOOKEEPER_HOME%\data\"
rmdir /s/q "%KAFKA_HOME%\kafka-logs\"

mkdir "%ZOOKEEPER_HOME%\data\"
mkdir "%KAFKA_HOME%\kafka-logs\"
timeout 1

:: Start ZooKeeper
start cmd /k "%ZOOKEEPER_HOME%\bin\zkserver.cmd"
timeout 1

:: Start Kafka
start cmd /k "%KAFKA_HOME%\bin\windows\kafka-server-start.bat %KAFKA_HOME%\config\server.properties"
