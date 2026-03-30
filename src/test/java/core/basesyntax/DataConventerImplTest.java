package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.impl.DataConverterImpl;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DataConventerImplTest {

    @Test
    void convertToTransaction_validData_ok() {
        List<String> data = List.of("quantity,fruit,operation", "b,apple,100");
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);

        DataConverterImpl dataConverter = new DataConverterImpl();

        List<FruitTransaction> result = dataConverter.convertToTransaction(data);

        assertEquals(transaction, result.get(0));
    }

    @Test
    void convertToTransaction_skipHeader_ok() {
        List<String> data = List.of("quantity,fruit,operation", "b,apple,100");
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);

        DataConverterImpl dataConverter = new DataConverterImpl();

        List<FruitTransaction> result = dataConverter.convertToTransaction(data);
        assertEquals(1, result.size());
    }

    @Test
    void convertToTransaction_allOperations_ok() {
        List<String> data = List.of("quantity,fruit,operation",
                "r,apple,100",
                "p,apple,20",
                "b,apple,30",
                "s,apple,10");
        DataConverterImpl dataConverter = new DataConverterImpl();

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(data);

        assertEquals(4, fruitTransactions.size());
    }

    @Test
    void convertToTransaction_unknownOperation_notOk() {
        List<String> data = List.of("quantity,fruit,operation", "x,apple,100");

        DataConverterImpl dataConverter = new DataConverterImpl();

        assertThrows(RuntimeException.class, () -> dataConverter.convertToTransaction(data));
    }

    @Test
    void convertToTransaction_nullData_notOk() {
        List<String> data = null;

        DataConverterImpl dataConverter = new DataConverterImpl();

        assertThrows(RuntimeException.class, () -> dataConverter.convertToTransaction(data));
    }

    @Test
    void setters_validData_ok() {
        FruitTransaction transaction = new FruitTransaction(20, "banana",
                FruitTransaction.Operation.BALANCE);
        transaction.setQuantity(100);
        transaction.setFruit("apple");
        transaction.setOperation(FruitTransaction.Operation.SUPPLY);

        assertEquals(100, transaction.getQuantity());
    }
}
