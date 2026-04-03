package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.OperationHandler;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.impl.OperationStrategyImpl;
import core.basesyntax.service.strategy.BalanceOperation;
import core.basesyntax.service.strategy.PurchaseOperation;
import core.basesyntax.service.strategy.ReturnOperation;
import core.basesyntax.service.strategy.SupplyOperation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationStrategyImplTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
    }

    @Test
    void getHandler_balance_ok() {
        BalanceOperation balanceOperation = new BalanceOperation(storage);
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        handler.put(FruitTransaction.Operation.BALANCE, balanceOperation);
        OperationStrategy strategy = new OperationStrategyImpl(handler);

        assertInstanceOf(BalanceOperation.class,
                strategy.getHandler(FruitTransaction.Operation.BALANCE));
    }

    @Test
    void getHandler_purchase_ok() {
        PurchaseOperation purchaseOperation = new PurchaseOperation(storage);
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        handler.put(FruitTransaction.Operation.PURCHASE, purchaseOperation);
        OperationStrategy strategy = new OperationStrategyImpl(handler);

        assertInstanceOf(PurchaseOperation.class,
                strategy.getHandler(FruitTransaction.Operation.PURCHASE));
    }

    @Test
    void getHandler_supply_ok() {
        SupplyOperation supplyOperation = new SupplyOperation(storage);
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        handler.put(FruitTransaction.Operation.SUPPLY, supplyOperation);
        OperationStrategy strategy = new OperationStrategyImpl(handler);

        assertInstanceOf(SupplyOperation.class,
                strategy.getHandler(FruitTransaction.Operation.SUPPLY));
    }

    @Test
    void getHandler_return_ok() {
        ReturnOperation returnOperation = new ReturnOperation(storage);
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        handler.put(FruitTransaction.Operation.RETURN, returnOperation);
        OperationStrategy strategy = new OperationStrategyImpl(handler);

        assertInstanceOf(ReturnOperation.class,
                strategy.getHandler(FruitTransaction.Operation.RETURN));
    }

    @Test
    void getHandler_null_ok() {
        Map<FruitTransaction.Operation, OperationHandler> handler = new HashMap<>();
        OperationStrategy strategy = new OperationStrategyImpl(handler);

        assertNull(strategy.getHandler(null));
    }

}
