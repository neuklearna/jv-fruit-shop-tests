package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.strategy.BalanceOperation;
import core.basesyntax.service.strategy.PurchaseOperation;
import org.junit.jupiter.api.Test;

public class PurchaseOperationTest {

    @Test
    void handle_validPurchase_ok() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transactionBalance = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);
        PurchaseOperation operation = new PurchaseOperation(storage);
        FruitTransaction transactionPurchase = new FruitTransaction(20, "apple",
                FruitTransaction.Operation.PURCHASE);

        balanceOperation.handle(transactionBalance);
        operation.handle(transactionPurchase);

        assertEquals(80, storage.getStorage().get("apple"));
    }

    @Test
    void handle_notEnoughFruits_notOk() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transactionBalance = new FruitTransaction(20, "apple",
                FruitTransaction.Operation.BALANCE);
        PurchaseOperation operation = new PurchaseOperation(storage);
        FruitTransaction transactionPurchase = new FruitTransaction(25, "apple",
                FruitTransaction.Operation.PURCHASE);

        balanceOperation.handle(transactionBalance);

        assertThrows(RuntimeException.class, () -> operation.handle(transactionPurchase));
    }

    @Test
    void handle_zeroQuantity_notOk() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transactionBalance = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);

        PurchaseOperation operation = new PurchaseOperation(storage);
        FruitTransaction transactionPurchase = new FruitTransaction(0, "apple",
                FruitTransaction.Operation.PURCHASE);

        balanceOperation.handle(transactionBalance);
        operation.handle(transactionPurchase);
        assertEquals(100, storage.getStorage().get("apple"));
    }
}
