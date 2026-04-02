package core.basesyntax;

import static org.junit.jupiter.api.Assertions.*;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationHandler;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.impl.OperationStrategyImpl;
import core.basesyntax.service.impl.ShopServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ShopServiceImplTest {

    @Test
    void process_emptyList_ok() {
        FakeOperationHandler fakeOperationHandler = new FakeOperationHandler();
        Map<FruitTransaction.Operation, OperationHandler> operationStrategies = new HashMap<>();
        operationStrategies.put(FruitTransaction.Operation.BALANCE, fakeOperationHandler);

        OperationStrategy strategy = new OperationStrategyImpl(operationStrategies);
        ShopService shopService = new ShopServiceImpl(strategy);

        shopService.process(List.of());

        assertFalse(fakeOperationHandler.wasCalled);
    }

    @Test
    void process_validTransactions_ok() {
        FakeOperationHandler fakeOperationHandler = new FakeOperationHandler();
        Map<FruitTransaction.Operation, OperationHandler> operationStrategies = new HashMap<>();
        operationStrategies.put(FruitTransaction.Operation.BALANCE, fakeOperationHandler);

        OperationStrategy strategy = new OperationStrategyImpl(operationStrategies);
        ShopService shopService = new ShopServiceImpl(strategy);

        FruitTransaction transaction = new  FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);
        shopService.process(List.of(transaction));

        assertTrue(fakeOperationHandler.wasCalled);
    }

    @Test
    void process_nullList_NotOk() {
        Map<FruitTransaction.Operation, OperationHandler> operationStrategies = new HashMap<>();
        OperationStrategy strategy = new OperationStrategyImpl(operationStrategies);
        ShopService shopService = new ShopServiceImpl(strategy);

        assertThrows(RuntimeException.class, () -> shopService.process(null));
    }
}
