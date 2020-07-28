### 概述|参考

* 在 [Bean Validation API](https://mvnrepository.com/artifact/javax.validation/validation-api) 中，定义了 Bean Validation 相关的接口，并没有具体实现。
* 在 `javax.validation.constraints` 包下，定义了一系列的校验注解。例如说，`@NotNull`、`@NotEmpty` 。
* [Hibernate Validator](https://hibernate.org/validator/) 实现了Bean Validation 规范的数据校验
* [JSR 303 – Bean Validation 介绍及最佳实践](https://developer.ibm.com/zh/articles/j-lo-jsr303/)

### Bean Validation 注解

* 空和非空检查

    * `@NotBlank` ：只能用于字符串不为 null ，并且字符串 #trim() 以后 length 要大于 0 。
    * `@NotEmpty` ：集合对象的元素不为 0 ，即集合不为空，也可以用于字符串不为 null 。
    * `@NotNull` ：不能为 null 。
    * `@Null` ：必须为 null 。

* 数值检查

    * `@DecimalMax(value)` ：被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
    * `@DecimalMin(value)` ：被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
    * `@Digits(integer, fraction)` ：被注释的元素必须是一个数字，其值必须在可接受的范围内。
    * `@Positive` ：判断正数。
    * `@PositiveOrZero` ：判断正数或 0 。
    * `@Max(value)` ：该字段的值只能小于或等于该值。
    * `@Min(value)` ：该字段的值只能大于或等于该值。
    * `@Negative` ：判断负数。
    * `@NegativeOrZero` ：判断负数或 0 。

* Boolean 值检查

    * `@AssertFalse` ：被注释的元素必须为 true 。
    * `@AssertTrue` ：被注释的元素必须为 false 。

* 长度检查
    
    * `@Size(max, min)` ：检查该字段的 size 是否在 min 和 max 之间，可以是字符串、数组、集合、Map 等。

* 日期检查

    * `@Future` ：被注释的元素必须是一个将来的日期。
    * `@FutureOrPresent` ：判断日期是否是将来或现在日期。
    * `@Past` ：检查该字段的日期是在过去。
    * `@PastOrPresent` ：判断日期是否是过去或现在日期。

* 其它检查

    * `@Email` ：被注释的元素必须是电子邮箱地址。
    * `@Pattern(value)` ：被注释的元素必须符合指定的正则表达式。

### Hibernate Validator 附加的约束注解

![Hibernate Validator](images/Hibernate%20Validator.png)

### `@Valid` 和 `@Validated` 

* `@Valid` 注解，是 Bean Validation 所定义，可以添加在普通方法、构造方法、方法参数、方法返回、成员变量上，表示它们需要进行约束校验。

* `@Validated` 注解，是 Spring Validation 锁定义，可以添加在类、方法参数、普通方法上，表示它们需要进行约束校验。同时，@Validated 有 value 属性，支持分组校验。属性如下：

```java
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validated {

	/**
	 * Specify one or more validation groups to apply to the validation step
	 * kicked off by this annotation.
	 * <p>JSR-303 defines validation groups as custom annotations which an application declares
	 * for the sole purpose of using them as type-safe group arguments, as implemented in
	 * {@link org.springframework.validation.beanvalidation.SpringValidatorAdapter}.
	 * <p>Other {@link org.springframework.validation.SmartValidator} implementations may
	 * support class arguments in other ways as well.
	 */
	Class<?>[] value() default {};

}
```

* ① 声明式校验

Spring Validation 仅对 `@Validated` 注解，实现声明式校验。

* ② 分组校验

Bean Validation 提供的 `@Valid` 注解，因为没有分组校验的属性，所以无法提供分组校验。此时，我们只能使用 `@Validated` 注解。

* ③ 嵌套校验

相比来说，@Valid 注解的地方，多了【成员变量】。这就导致，如果有嵌套对象的时候，只能使用 `@Valid` 注解。例如说：

```java

// User.java
public class User {
    
    private String id;

    @Valid
    private UserProfile profile;

}

// UserProfile.java
public class UserProfile {

    @NotBlank
    private String nickname;

}

```

如果不在 User.profile 属性上，添加 `@Valid` 注解，就会导致 UserProfile.nickname 属性，不会进行校验。


* [更多...](http://www.iocoder.cn/Spring-Boot/Validation/?self)