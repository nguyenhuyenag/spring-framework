@echo off

set jar1=F:/Soft/apache/apache-jmeter-5.6.3/bin/ApacheJMeter.jar
set jar2=F:/Dev/apache/apache-jmeter-5.6.3/bin/ApacheJMeter.jar

if exist "%jar1%" (
    echo Launching Apache JMeter from %jar1%
    start java -jar "%jar1%"
    exit
)

if exist "%jar2%" (
    echo Launching Apache JMeter from %jar2%
    start java -jar "%jar2%"
    exit
)

echo ApacheJMeter.jar not found in both paths.
pause
exit
