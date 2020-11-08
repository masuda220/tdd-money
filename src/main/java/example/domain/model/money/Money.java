package example.domain.model.money;

/**
 * お金
 */
public class Money implements Amount {
    protected int amount;
    protected Currency currency;

    Money(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Amount times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    String currency() {
        return currency.name();
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return this.amount == money.amount
                && this.currency().equals(money.currency());
    }

    public String toString() {
        return this.amount + " " + this.currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, Currency.USD);
    }

    static Money franc(int amount) {
        return new Money(amount, Currency.CHF);
    }

    public Amount plus(Amount addend) {
        return new Sum(this, addend);
    }

    public Money reduce(RateTable rateTable, Currency to) {
        int rate = rateTable.rate(currency, to);
        return new Money(amount / rate, to);
    }
}
