# Hospital-management-system


# 前言

非常垃圾的JAVA医疗卫生管理系统。

没用框架做,servlet+service+dao 三层架构。

这就当交作业了,只做了医生管理、药品管理、挂号信息管理三个模块。

有需要的可以自行clone拓展,交交作业。



# 安装

导入hostpitals.sql到mysql数据库。

Eclispe打开后导入自行配置环境,数据库在/src/c3p0-config.xml 下修改链接地址和密码

同时在挂号信息管理(register)的jsp界面中没有做多表查询,用的是jstl标签做doctor数据表查询。

在/WebContent/register/那些界面头部做数据库链接地址与密码修改,然后运行即可。
