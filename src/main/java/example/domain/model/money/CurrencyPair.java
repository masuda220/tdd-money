package example.domain.model.money;

import java.util.Objects;

/**
 * 通貨の組
 */
public class CurrencyPair {
    private Currency from;
    private Currency to;

    CurrencyPair(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public boolean equals(Object object) {
        CurrencyPair currencyPair = (CurrencyPair) object;
        return from.equals(currencyPair.from) && to.equals(currencyPair.to);
    }

    public int hashCode() {
        return Objects.hash(from, to);
    }
}
