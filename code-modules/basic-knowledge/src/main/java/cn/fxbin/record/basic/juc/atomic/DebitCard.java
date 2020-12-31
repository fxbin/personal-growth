package cn.fxbin.record.basic.juc.atomic;

/**
 * DebitCard
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 11:40
 */
public class DebitCard {

    private final String account;

    private final int amount;

    public DebitCard(String account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
