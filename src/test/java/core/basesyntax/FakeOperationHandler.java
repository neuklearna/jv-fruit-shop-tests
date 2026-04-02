package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationHandler;

public class FakeOperationHandler implements OperationHandler {

    boolean wasCalled = false;
    FruitTransaction transaction = null;
    @Override
    public void handle(FruitTransaction fruitTransaction) {
        wasCalled = true;
        transaction = fruitTransaction;
    }
}
