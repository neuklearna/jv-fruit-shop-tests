package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.strategy.BalanceOperation;
import org.junit.jupiter.api.Test;

public class BalanceOperationTest {

    @Test
    void handle_validTransaction_ok() {
        // GIVEN — przygotuj dane
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transaction = new FruitTransaction(100, "banana",
                FruitTransaction.Operation.BALANCE);

        // WHEN — wywołaj metodę
        balanceOperation.handle(transaction);

        // THEN — sprawdź wynik
        assertEquals(100, storage.getStorage().get("banana"));
    }

    @Test
    void handle_twoFruits_ok() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transaction1 = new FruitTransaction(100,"banana",
                FruitTransaction.Operation.BALANCE);
        FruitTransaction transaction2 = new FruitTransaction(50,"apple",
                FruitTransaction.Operation.BALANCE);

        balanceOperation.handle(transaction1);
        balanceOperation.handle(transaction2);

        assertEquals(100, storage.getStorage().get("banana"));
        assertEquals(50, storage.getStorage().get("apple"));
    }

    @Test
    void handle_overwriteExistingBalance_ok() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transaction1 = new FruitTransaction(100, "banana",
                FruitTransaction.Operation.BALANCE);
        FruitTransaction transaction2 = new FruitTransaction(50, "banana",
                FruitTransaction.Operation.BALANCE);

        balanceOperation.handle(transaction1);
        balanceOperation.handle(transaction2);

        assertEquals(50, storage.getStorage().get("banana"));
    }

    @Test
    void handle_nullTransaction_notOk() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);

        assertThrows(RuntimeException.class, () -> balanceOperation.handle(null));
    }
}
