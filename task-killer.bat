@echo off
set /p port="Enter port number to be closed: "
netstat -ano -p tcp | findstr :8910
set /p pid="Enter pid number to be killed: "
taskkill /F /IM %pid%