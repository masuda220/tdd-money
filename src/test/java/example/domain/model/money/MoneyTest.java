package example.domain.model.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Amount sum = five.plus(five);
        RateTable rateTable = new RateTable();
        Money reduced = rateTable.reduce(sum, Currency.USD);
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Amount result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    @Test
    public void testReduceSum() {
        Amount sum = new Sum(Money.dollar(3), Money.dollar(4));
        RateTable rateTable = new RateTable();
        Money result = rateTable.reduce(sum, Currency.USD);
        assertEquals(Money.dollar(7), result);
    }

    @Test
    public void testRecudeMoney() {
        RateTable rateTable = new RateTable();
        Money result = rateTable.reduce(Money.dollar(1), Currency.USD);
        assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
        // 2 フラン → 1 ドル
        RateTable rateTable = new RateTable();
        rateTable.addRate(Currency.CHF, Currency.USD, 2);
        Money result = rateTable.reduce(Money.franc(2), Currency.USD);
        assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testIdentityRate() {
        // 同じ通貨同士ならレートは 1
        assertEquals(1, new RateTable().rate(Currency.USD, Currency.USD));
    }

    @Test
    public void testMixedAddition() {
        // 5 ドル + 10 フラン = 10 ドル
        Amount fiveBucks = Money.dollar(5);
        Amount tenFrancs = Money.franc(10);
        RateTable rateTable = new RateTable();
        rateTable.addRate(Currency.CHF, Currency.USD, 2);
        Money result = rateTable.reduce(fiveBucks.plus(tenFrancs), Currency.USD);
        assertEquals(Money.dollar(10), result);
    }

    @Test
    public void testSumPlusMoney() {
        Amount fiveBucks = Money.dollar(5);
        Amount tenFrancs = Money.franc(10);
        RateTable rateTable = new RateTable();
        rateTable.addRate(Currency.CHF,Currency.USD, 2);
        Amount sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = rateTable.reduce(sum, Currency.USD);
        assertEquals(Money.dollar(15), result);
    }

    @Test
    public void testSumTimes() {
        Amount fiveBucks = Money.dollar(5);
        Amount tenFrancs = Money.franc(10);
        RateTable rateTable = new RateTable();
        rateTable.addRate(Currency.CHF, Currency.USD, 2);
        Amount sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = rateTable.reduce(sum, Currency.USD);
        assertEquals(Money.dollar(20), result);
    }
}
