package example.domain.model.money;

/**
 * 金額
 */
public interface Amount {
    Amount times(int multiplier);
    Amount plus(Amount addend);
    Money reduce(RateTable rateTable, Currency to);
}
