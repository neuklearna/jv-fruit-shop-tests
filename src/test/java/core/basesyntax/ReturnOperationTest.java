package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.strategy.BalanceOperation;
import core.basesyntax.service.strategy.ReturnOperation;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {

    @Test
    void handle_addReturn_ok() {
        Storage storage = new Storage();
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);

        ReturnOperation returnOperation = new ReturnOperation(storage);
        FruitTransaction transaction2 = new FruitTransaction(5, "apple",
                FruitTransaction.Operation.RETURN);

        balanceOperation.handle(transaction);
        returnOperation.handle(transaction2);

        assertEquals(105, storage.getStorage().get("apple"));
    }

    @Test
    void handle_emptyStorage_notOk() {
        Storage storage = new Storage();
        ReturnOperation returnOperation = new ReturnOperation(storage);
        FruitTransaction transaction2 = new FruitTransaction(5, "apple",
                FruitTransaction.Operation.RETURN);

        assertThrows(RuntimeException.class, () -> returnOperation.handle(transaction2));
    }

    @Test
    void handle_nullTransaction_notOk() {
        Storage storage = new Storage();
        ReturnOperation returnOperation = new ReturnOperation(storage);

        assertThrows(RuntimeException.class, () -> returnOperation.handle(null));
    }
}
