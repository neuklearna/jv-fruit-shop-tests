package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.strategy.BalanceOperation;
import core.basesyntax.service.strategy.ReturnOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
    }

    @Test
    void handle_addReturn_ok() {
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
        ReturnOperation returnOperation = new ReturnOperation(storage);
        FruitTransaction transaction2 = new FruitTransaction(5, "apple",
                FruitTransaction.Operation.RETURN);

        assertThrows(RuntimeException.class, () -> returnOperation.handle(transaction2));
    }

    @Test
    void handle_nullTransaction_notOk() {
        ReturnOperation returnOperation = new ReturnOperation(storage);

        assertThrows(RuntimeException.class, () -> returnOperation.handle(null));
    }
}
