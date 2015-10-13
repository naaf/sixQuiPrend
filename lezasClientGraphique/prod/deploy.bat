@echo off
cd ..
echo "mvn clean RUN  ..."
call mvn clean > .\prod\deploy.log
echo "mvn deploy RUN  ..."
call mvn deploy -B -V -e >> .\prod\deploy.log
pause