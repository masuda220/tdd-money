package example.domain.model.money;

import java.util.HashMap;
import java.util.Map;

/**
 * レート表
 */
public class RateTable {
    private Map<CurrencyPair, Integer> rates = new HashMap<>();

    Money reduce(Amount source, Currency to) {
        return source.reduce(this, to);
    }

    void addRate(Currency from, Currency to, int rate) {
        rates.put(new CurrencyPair(from, to), rate);
    }

    int rate(Currency from, Currency to) {
        if (from.equals(to)) return 1;
        return rates.get(new CurrencyPair(from, to));
    }
}
