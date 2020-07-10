#!/bin/bash
cd /opt/project/springcloud
git pull
# 删除已存在容器
cantionor=("demo1", "demo2", "zuul")
for element in ${cantionor[*]}
do
  cantion=`docker-compose ps  | grep $element | awk '{print$1}'`
  if  [ -n "$cantion" ] ;then
    docker stop $cantion
    docker rm $cantion
  fi
  image=`docker-compose images| grep $element | awk '{print$1}'`
  if  [ -n "$image" ] ;then
    docker-compose rmi $image
  fi

done
# 更新代码
cd /opt/project/springcloud
# git pull

mvn clean package
docker-compose -f docker-compose.yml up -d

