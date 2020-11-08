package example.domain.model.money;

/**
 * 合計
 */
public class Sum implements Amount {
    Amount augend;
    Amount addend;

    public Sum(Amount augend, Amount addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Amount times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }

    public Amount plus(Amount addend) {
        return new Sum(this, addend);
    }

    public Money reduce(RateTable rateTable, Currency to) {
        int amount = augend.reduce(rateTable, to).amount
                + addend.reduce(rateTable, to).amount;
        return new Money(amount, to);
    }
}
