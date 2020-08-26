## mysql-binlog

### binlog 是什么

> mysql-binlog是MySQL数据库的二进制日志，用于记录用户对数据库操作的SQL语句(SELECT 或 SHOW 等不修改数据的操作除外)信息。

### 为什么要用 binlog

* 复制
> 使用二进制日志，可以把对服务器所做的更改以流式方式传输到另一台服务器上。从（slave）服务器充当镜像副本，也可用于分配负载。接受写入的服务器称为主（master）服务器。

* 时间点恢复
> 假设你在星期日的00：00进行了备份，而数据库在星期日的08：00出现故障。使用备份可以恢复到周日00：00的状态；而使用二进制日志可以恢复到周日08：00的状态。

### 使用 binlog

* 验证是否创建了binlog
```sql
show variables like 'log_bin%';
```

* 显示服务器所有的binlog 文件
```sql
show binary logs;
```

```sql
show master logs;
```

* 获取当前binlog 位置
```sql
show master status;
```

* 关闭当前的 binlog 并打开一个新的 binlog
```sql
flush logs;
```

* 清理 binlog 
    * 使用 `binlog_expire_logs_seconds` 和 `expire_logs_days` 设置日志的到期时间，二者效果是叠加的；例如，如果expire_logs_days是1并且binlog_expire_logs_seconds是43200，那么二进制日志就会每 1.5 天清除一次。
        这与将 binlog_expire_logs_seconds设置为129600、将expire_logs_days设置为0的效果是相同的
    * 手动清除日志，需执行 `PURGE BINARY LOGS TO '<file_name>'` 或者 `PURGE BINARY LOGS BEFORE '2020-08-24 14:35:00'`
    * 删除所有的二进制日志， `RESET MASTER`

### binlog 格式

* STATEMENT

> 记录实际的SQL语句

* ROW

> 记录每行所做的更改。
> 例如，更新语句更新10行，所有10行的更新信息都会被写入日志。而在基于语句的复制中，只有更新语句会被写入日志，默认格式是ROW。

* MIXED

> 当需要时，MySQL会从STATEMENT切换到ROW。
> 有些语句在不同服务器上执行时可能会得到不同的结果。例如，UUID（）函数的输出就因服务器而异。这些语句被称为非确定性的语句，基于这些语句的复制是不安全的。在这些情况下，当设置MIXED格式时，MySQL服务器会切换为基于行的格式。
>
> 欲知更多, 请[参阅官网](https://dev.mysql.com/doc/refman/8.0/en/binary-log-mixed.html)


### 参考文档

[MySQL 官方文档](https://dev.mysql.com/doc/refman/8.0/en/binary-log.html)
[MySQL binlog 基本用法](https://www.iteblog.com/mysql-binlog_basic_usage/)