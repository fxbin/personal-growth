package cn.fxbin.record.basic.optional;

import java.util.Optional;

/**
 * Car
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/22 18:21
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

}
