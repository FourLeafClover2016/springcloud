#!/bin/bash
cd /opt/project/springcloud
git pull
# 删除已存在容器
cantionor=("demo1/demo1", "demo2/demo2", "zuul-gateway/zuul")
for element in ${cantionor[*]}
do
  cantion=`docker ps -a | grep $element | awk '{print$1}'`
  if  [ -n "$cantion" ] ;then
    docker stop $cantion
    docker rm $cantion
  fi
  docker rmi $element
done
# 更新代码
cd /opt/project/springcloud
git pull
cantion=`docker ps -a | grep demo1/demo1 | awk '{print$1}'`
mvn clean package

cd /opt/project/springcloud/build/
docker-compose -f docker-compose.yml up -d

