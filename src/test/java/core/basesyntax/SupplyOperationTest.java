package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.strategy.BalanceOperation;
import core.basesyntax.service.strategy.SupplyOperation;
import org.junit.jupiter.api.Test;

public class SupplyOperationTest {

    @Test
    void handle_addToExisting_ok() {
        Storage storage = new Storage();
        SupplyOperation operation = new SupplyOperation(storage);
        BalanceOperation operation2 = new BalanceOperation(storage);
        FruitTransaction startValue = new FruitTransaction(20, "banana",
                FruitTransaction.Operation.BALANCE);
        FruitTransaction transaction = new FruitTransaction(100, "banana",
                FruitTransaction.Operation.SUPPLY);

        operation2.handle(startValue);
        operation.handle(transaction);

        assertEquals(120, storage.getStorage().get("banana"));
    }

    @Test
    void handle_emptyStorage_ok() {
        Storage storage = new Storage();
        SupplyOperation operation = new SupplyOperation(storage);
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.SUPPLY);

        assertThrows(RuntimeException.class, () -> operation.handle(transaction));
    }

    @Test
    void handle_multipleSupplies_ok() {
        Storage storage = new Storage();
        SupplyOperation operation = new SupplyOperation(storage);
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction balanseOperationValue = new FruitTransaction(20, "apple",
                FruitTransaction.Operation.BALANCE);
        FruitTransaction transaction = new FruitTransaction(20, "apple",
                FruitTransaction.Operation.SUPPLY);
        FruitTransaction transaction2 = new FruitTransaction(20, "apple",
                FruitTransaction.Operation.SUPPLY);

        balanceOperation.handle(balanseOperationValue);
        operation.handle(transaction);
        operation.handle(transaction2);

        assertEquals(60, storage.getStorage().get("apple"));
    }

    @Test
    void handle_nullTransaction_notOk() {
        Storage storage = new Storage();
        SupplyOperation operation = new SupplyOperation(storage);

        assertThrows(RuntimeException.class, () -> operation.handle(null));
    }

}
