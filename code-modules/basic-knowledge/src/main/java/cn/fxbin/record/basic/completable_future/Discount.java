package cn.fxbin.record.basic.completable_future;

import static cn.fxbin.record.basic.completable_future.Utils.*;

/**
 * Discount
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/23 11:15
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}
