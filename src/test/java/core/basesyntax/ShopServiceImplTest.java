package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.OperationHandler;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.impl.OperationStrategyImpl;
import core.basesyntax.service.impl.ShopServiceImpl;
import core.basesyntax.service.strategy.BalanceOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ShopServiceImplTest {

    @Test
    void process_emptyList_ok() {
        Storage storage = new Storage();
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        OperationStrategy strategy = new OperationStrategyImpl(handler);
        ShopService shopService = new ShopServiceImpl(strategy);
        shopService.process(List.of());
    }

    @Test
    void process_validTransactions_ok() {
        Storage storage = new Storage();
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        List<FruitTransaction> transactions = new ArrayList<>();
        BalanceOperation operation = new BalanceOperation(storage);
        handler.put(FruitTransaction.Operation.BALANCE, operation);
        transactions.add(new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE));

        OperationStrategy strategy = new OperationStrategyImpl(handler);
        ShopService shopService = new ShopServiceImpl(strategy);

        shopService.process(transactions);

        assertEquals(100, storage.getStorage().get("apple"));
    }

    @Test
    void process_nullList_notOk() {
        Storage storage = new Storage();
        BalanceOperation operation = new BalanceOperation(storage);
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        List<FruitTransaction> list = null;
        handler.put(FruitTransaction.Operation.BALANCE, operation);
        OperationStrategy strategy = new OperationStrategyImpl(handler);
        ShopService shopService = new ShopServiceImpl(strategy);

        assertThrows(RuntimeException.class, () -> shopService.process(null));
    }
}
