package core.basesyntax.strategy;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperationStrategyTest {
    private static OperationStrategy operationStrategy;
    private static Map<String, OperationHandler> map;

    @BeforeClass
    public static void beforeClass() {
        map = new HashMap<>();
        map.put("b", new BalanceOperationHandler());
        map.put("s", new SupplyOperationHandler());
        map.put("p", new PurchaseOperationHandler());
        map.put("r", new ReturnOperationHandler());
        operationStrategy = new OperationStrategy(map);
    }

    @Test(expected = RuntimeException.class)
    public void getByOperation_emptyOperation_NotOk() {
        Class<?> expected = BalanceOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("").getClass();
        assertEquals(expected,actual);
    }

    @Test(expected = RuntimeException.class)
    public void getByOperation_nullOperation_NotOk() {
        Class<?> expected = BalanceOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation(null).getClass();
        assertEquals(expected,actual);
    }

    @Test(expected = RuntimeException.class)
    public void getByOperation_noSuchOperation_NotOk() {
        Class<?> expected = BalanceOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("a").getClass();
        assertEquals(expected,actual);
    }

    @Test
    public void getByOperation_validBalanceOperation_Ok() {
        Class<?> expected = BalanceOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("b").getClass();
        assertEquals(expected,actual);
    }

    @Test
    public void getByOperation_validSupplyOperation_Ok() {
        Class<?> expected = SupplyOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("s").getClass();
        assertEquals(expected,actual);
    }

    @Test
    public void getByOperation_validPurchaseOperation_Ok() {
        Class<?> expected = PurchaseOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("p").getClass();
        assertEquals(expected,actual);
    }

    @Test
    public void getByOperation_validReturnOperation_Ok() {
        Class<?> expected = ReturnOperationHandler.class;
        Class<?> actual = operationStrategy.getByOperation("r").getClass();
        assertEquals(expected,actual);
    }
}
