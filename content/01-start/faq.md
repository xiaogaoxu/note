+++
toc = true
date = "2016-12-06T22:38:50+08:00"
title = "FAQ"
weight = 2
prev = "/01-start/quick-start/"
next = "/01-start/features/"

+++

### 1. 阅读源码时为什么会出现编译错误?

回答：

Sharding-JDBC使用lombok实现极简代码。关于更多使用和安装细节，请参考[lombok官网](https://projectlombok.org/download.html)。

### 2. java.lang.NoSuchMethodError:com.alibaba.druid.sql.ast.expr.SQLAggregateExpr.getOption().....异常的解决方法？

回答：

目前Sharding-JDBC使用Druid作为SQL解析的基础库，请确保业务代码中使用的Druid与Sharding-JDBC使用的版本一致，目前Sharding-JDBC使用的是`1.0.12`版本。

### 3. 使用Spring命名空间时在网上相应地址找不到xsd?

回答：

Spring命名空间使用规范并未强制要求将xsd文件部署至公网地址，因此我们并未将`http://www.dangdang.com/schema/ddframe/rdb/rdb.xsd`部署至公网，但不影响正常使用。

sharding-jdbc-config-spring的jar包中`META-INF\spring.schemas`配置了xsd文件的位置：`META-INF\namespace\rdb.xsd`，需确保jar包中该文件存在。

### 4. Cloud not resolve placeholder ... in string value ...异常的解决方法?

回答：

在Spring的配置文件中，由于inline表达式使用了Groovy语法，Groovy语法的变量符与Spring默认占位符同为`${}`，因此需要在配置文件中增加：

```xml
<context:property-placeholder location="classpath:conf/rdb/conf.properties" ignore-unresolvable="true"/>
```

### 5. inline表达式返回结果为何出现浮点数？

回答：

Java的整数相除结果是整数，但是对于inline表达式中的Groovy语法则不同，整数相除结果是浮点数。
想获得除法整数结果需要将A/B改为A.intdiv(B)。


### 6. 使用Proxool时分库结果不正确？

回答：

使用Proxool配置多个数据源时，应该为每个数据源设置alias，因为Proxool在获取连接时会判断连接池中是否包含已存在的alias，不配置alias会造成每次都只从一个数据源中获取连接。

以下是Proxool源码中ProxoolDataSource类getConnection方法的关键代码：

```java
    if(!ConnectionPoolManager.getInstance().isPoolExists(this.alias)) {
        this.registerPool();
    }
```

更多关于alias使用方法请参考[Proxool官网](http://proxool.sourceforge.net/configure.html)。

PS:sourceforge网站需要翻墙访问。
