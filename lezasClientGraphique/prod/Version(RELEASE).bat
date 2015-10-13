@echo off
cd ..\
echo RUN clean  ...
call mvn -B -V -e clean > .\prod\package_RELEASE.log
echo End clean

echo RUN release:prepare ...
call mvn -B -V -e release:prepare >> .\prod\package_RELEASE.log
echo End release:prepare

echo RUN release:perform ...
call mvn -B -V -e release:perform -Dmaven.test.skip=true >> .\prod\package_RELEASE.log
echo End release:perform
pause
