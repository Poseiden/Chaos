# Spring Project Initial With Gradle
[![Build Status](https://travis-ci.com/Poseiden/spring_project_initial_with_gradle.svg?branch=master)](https://travis-ci.com/Poseiden/spring_project_initial_with_gradle)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
### 项目简介
> 该项目是一个 SpringBoot 项目初始化代码框架，希望做到开箱即用，有效缩短I0时间。
### 技术选型
- SpringBoot 
- Gradle
- H2
- Docker 
- Travis 
- Jenkins
### 本地构建
```
./gradlew test  #本地测试
./gradlew build  #本地构建出Jar
./gradlew bootRun  #本地启动
```
### 测试策略
目前只有四个基本的 API 测试。用于保障项目启动成功的 Hello World，以及权限的成功配置。
### 部署架构
Docker + K8S
### 外部依赖
目前持续集成使用的是开源Travis
### 环境信息
### 编码实践
- TDD
### 领域模型
- UserAccount 用户
- Role 角色
### FAQ

### ISSUE
#### 集成 Dockerhub
#### 添加 Test coverage
#### 添加 CheckStyle
### Swagger 添加Auth Header

### Feature
#### File Upload
