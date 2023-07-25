:: @echo off
:: Define Kafka path
set "KAFKA_HOME=C:\Server\Kafka\kafka-2.12"

:: Delete all log
:: rmdir /s/q "%KAFKA_HOME%\logs\"
:: mkdir "%KAFKA_HOME%\logs\"
del /S /Q "%KAFKA_HOME%\logs\*"
del /S /Q "%KAFKA_HOME%\kafka-logs\*"
del /S /Q "%ZOOKEEPER_HOME%\data\*"
timeout 2

:: Start ZooKeeper
start cmd /k "%ZOOKEEPER_HOME%\bin\zkserver.cmd"
timeout 2

:: Start Kafka
start cmd /k "%KAFKA_HOME%\bin\windows\kafka-server-start.bat %KAFKA_HOME%\config\server.properties"
