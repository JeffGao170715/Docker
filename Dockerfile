## 基础镜像
From my_java
## 维护者信息
MAINTAINER JeffGao "157330205@qq.com"

## 工作路径
WORKDIR /Jeff/test/

## 拷贝jar包到容器指定路径
ADD /target/docker-0.0.1-SNAPSHOT.jar my.jar


## 暴露的端口号
EXPOSE 8080

## 启动jar包
ENTRYPOINT ["java", "-jar", "my.jar"]