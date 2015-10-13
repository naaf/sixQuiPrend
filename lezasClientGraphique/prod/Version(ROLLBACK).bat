@echo off
cd ..
echo RUN clean  ...
call mvn -B -V -e clean > .\prod\package_ROLLBACK.log
echo End clean

echo RUN release:rollback ...
call mvn -B -V -e release:rollback -Dmaven.test.skip=true >> .\prod\package_ROLLBACK.log
echo End release:rollback
pause
