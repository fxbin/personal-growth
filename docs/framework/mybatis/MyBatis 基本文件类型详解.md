## 配置文件

> MyBatis 的配置文件为一个XML文件，通常被命名为 mybatis-config.xml 该文件的根节点为 configuration，根节点内可以包含的一级节点及其含义如下所示：
> 
- properties: 属性信息，MyBatis 的全局变量
- settings: 设置信息，通过它对 MyBatis 的功能进行调整
- typeAliases: 类型别名，在这里可以为类型设置一些简短的名字
- typeHandlers: 类型处理器，在这里可以为不同的类型设置相应的处理器
- objectFactory: 对象工厂，在这里可以指定 MyBatis 创建新对象时使用的工厂
- objectWrapperFactory: 对象包装器工厂，在这里可以指定 MyBatis 使用的对象包装器工厂
- reflectorFactory: 反射器工厂，在这里可以设置 MyBatis 的反射器工厂
- plugins: 插件， 在这里可以为 MyBatis 配置差价，从而修改或扩展 MyBatis 的行为
- environments: 环境，这里可以配置 MyBatis 运行的环境信息，如数据源信息等
- databaseIdProvider: 数据库编号，在这里可以为不同的数据库配置不同的编号，这样可以对不同类型的数据库设置不同的数据库操作语句
- mappers: 映射文件，在这里可以配置映射文件或映射文件接口的地址

示例： 
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!--

       Copyright 2009-2017 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

-->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <!-- autoMappingBehavior should be set in each test case -->

    <environments default="development">
        <environment id="development">
          <!-- 事务管理器 -->
            <transactionManager type="JDBC">
                <property name="" value=""/>
            </transactionManager>
          <!-- 配置管理器 -->
            <dataSource type="UNPOOLED">
                <property name="driver" value="org.hsqldb.jdbcDriver"/>
                <property name="url" value="jdbc:hsqldb:mem:automapping"/>
                <property name="username" value="sa"/>
            </dataSource>
        </environment>
    </environments>

  <!-- 扫描 Mapper 文件 -->
    <mappers>
        <mapper resource="org/apache/ibatis/autoconstructor/AutoConstructorMapper.xml"/>
    </mappers>

</configuration>

```

## 映射文件

> 映射文件也是一个 XML文件，用来完成 Java方法与 SQL语句的映射、Java对象与SQL参数的映射、SQL查询结果与 Java对象的映射等
>
- cache：缓存，通过它可以对当前命名空间进行缓存配置。
- cache-ref：缓存引用，通过它可以引用其他命名空间的缓存作为当前命名空间的缓存。
- resultMap：结果映射，通过它来配置如何将 SQL查询结果映射为对象。
- parameterMap：参数映射，通过它来配置如何将参数对象映射为 SQL参数。该节点已废弃，建议直接使用内联参数。
- sql：SQL语句片段，通过它来设置可以被复用的语句片段。
- insert：插入语句。
- update：更新语句。
- delete：删除语句。
- select：查询语句。

示例： 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--

       Copyright 2009-2017 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

-->
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="org.apache.ibatis.autoconstructor.AutoConstructorMapper">
  <select id="getSubjects" resultType="org.apache.ibatis.autoconstructor.PrimitiveSubject">
    SELECT * FROM subject
  </select>
</mapper>
```

## 映射接口文件

> 在映射接口文件中可以定义一些抽象方法，这些抽象方法可以分为两类：
> * 第一类抽象方法与对应的映射文件中的数据库操作节点相对应。
> * 第二类抽象方法通过注解声明自身的数据库操作语句。当整个接口文件中均为该类抽象方法时，则该映射接口文件可以没有对应的映射文件。

```java
public interface AutoConstructorMapper {
  @Select("SELECT * FROM subject WHERE id = #{id}")
  PrimitiveSubject getSubject(final int id);

  List<PrimitiveSubject> getSubjects();

  @Select("SELECT * FROM subject")
  List<AnnotatedSubject> getAnnotatedSubjects();

  @Select("SELECT * FROM subject")
  List<BadSubject> getBadSubjects();

  @Select("SELECT * FROM extensive_subject")
  List<ExtensiveSubject> getExtensiveSubjects();
}
```