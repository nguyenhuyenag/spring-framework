@echo off
setlocal enabledelayedexpansion

:menu
cls
echo.
echo    [1] Start
echo    [2] Clear data and start
echo    [0] Exit
echo    ===============================
echo.

set /p "choice=Enter your choice (1, 2, or 0): " 

if "%choice%"=="1" (
    call :startZooKeeperAndKafka
) else if "%choice%"=="2" (
    rmdir /s/q "%ZOOKEEPER_HOME%\data\"
    rmdir /s/q "%KAFKA_HOME%\kafka-logs\"
    mkdir "%ZOOKEEPER_HOME%\data\"
    mkdir "%KAFKA_HOME%\kafka-logs\"
    call :startZooKeeperAndKafka
)else if "%choice%"=="0" (
	exit
)

::timeout /t 1 /nobreak
::exit

:startZooKeeperAndKafka
start cmd /k "%ZOOKEEPER_HOME%\bin\zkserver.cmd"
timeout 2
start cmd /k "%KAFKA_HOME%\bin\windows\kafka-server-start.bat %KAFKA_HOME%\config\server.properties"
goto :eof
