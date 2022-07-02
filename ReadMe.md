# 使用手册

## 写在前面
本项目基于servlet和jsp

## 变量对应表
### 普通变量名
Variable.ack: 记录跳转信息

### session名
orders(Orders): 个人的订单信息。


### 请求名
option(String): 进入OrderController后，决定进入哪个分支。其中option的值和函数名相同。

## 功能大致逻辑
1. 通过请求跳转到server，通过重定向跳转到client
2. 传送到jsp的数据都是List<Map<String, Object>>类型
