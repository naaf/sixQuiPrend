@echo off
cd ..
echo "mvn clean RUN  ..."
call mvn clean compile assembly:single > .\prod\package.log
echo "mvn package RUN  ..."
call mvn package -B -V -e >> .\prod\package.log
pause
