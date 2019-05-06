# Spring Boot - Activiti

## 概述：

本Demo 配套的教程笔记：https://www.yuque.com/mrdeer/activiti_note

该项目是关于 Spring Boot 整合 Activiti 框架的一个代码笔记，只用于学习使用，非商业性质。所有的笔记都在Test文件夹下，
因为都是使用 Junit 进行测试使用的。

其中还有一个Demo，整个Demo 就是Activiti 的小的实例，关联数据库的一个请假流程。直接其中项目即可。在/processes/example/leave 下的流程就是Demo 的流程文件，在/processes/example/note 下的全部都是教程中用的流程文件
根目录中有一个 sql 的文件夹，里面就是这个项目需要用到的自定义的SQL语句。

- 项目主页：http://127.0.0.1:8080/activiti/view/index
- 登陆用户：
    - userA/123(员工)
    - userB/123(部门经理)
    - userC/123(总经理)

### 开发环境：

- Spring Tool Suite：Spring Tool Suite 4 版本
- JDK：1.8 版本
- SpringBoot：2.1.3.RELEASE 版本
- Activiti：6.0.0 版本
- Lombok：1.18.6 版本
- MyBatis：2.0.0 版本
- Mybatis Plus：3.1.0 版本
- MySQL：8.0.15 版本
- Bootstrap: 4.0.0 版本