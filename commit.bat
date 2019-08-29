@echo off
echo show commit message %1%
set /p msg=commit message:
git status && git pull && echo 'git pull success'
git add . && echo  commit message:   %msg%   && git commit -m %msg%   && git push && echo "git push success..."

