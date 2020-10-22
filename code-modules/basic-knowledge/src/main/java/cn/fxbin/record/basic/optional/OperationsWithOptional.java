package cn.fxbin.record.basic.optional;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * OperationsWithOptional
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/22 18:22
 */
public class OperationsWithOptional {

    public static void main(String... args) {
        Optional<Integer> opt1 = of(5);
        Optional<Integer> opt2 = of(4);
        System.out.println(max(opt1, opt2));
    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

}
