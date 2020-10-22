package cn.fxbin.record.basic.optional;

import java.util.Optional;

/**
 * Person
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/22 18:24
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

}
