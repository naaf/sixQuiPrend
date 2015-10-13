@echo off
cd ..
echo "mvn clean RUN  ..."
call mvn clean compile -U
pause