@echo off
cd ..
echo RUN clean  ...
call mvn -B -V -e clean > .\prod\package_SNAPSHOT.log
echo End clean

echo RUN package ...
call mvn package -B -V -e -Dmaven.test.skip=true >> .\prod\package_SNAPSHOT.log
pause
