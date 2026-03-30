package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import core.basesyntax.service.strategy.BalanceOperation;
import org.junit.jupiter.api.Test;

public class ReportGeneratorImplTest {

    @Test
    void getReport_oneFruit_ok() {
        Storage storage = new Storage();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        BalanceOperation operation = new BalanceOperation(storage);
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);

        operation.handle(transaction);
        String report = reportGenerator.getReport();

        assertEquals("fruit,quantity\napple,100\n", report);
    }

    @Test
    void getReport_multipleFruits_ok() {
        Storage storage = new Storage();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        BalanceOperation operation = new BalanceOperation(storage);
        FruitTransaction transaction = new FruitTransaction(100, "apple",
                FruitTransaction.Operation.BALANCE);
        FruitTransaction transaction2 = new FruitTransaction(20, "banana",
                FruitTransaction.Operation.BALANCE);

        operation.handle(transaction);
        operation.handle(transaction2);

        String report = reportGenerator.getReport();
        assertTrue(report.contains("apple,100"));
        assertTrue(report.contains("banana,20"));
    }

    @Test
    void getReport_containsHeader_ok() {
        Storage storage = new Storage();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

        String report = reportGenerator.getReport();

        assertTrue(report.contains("fruit,quantity\n"));
    }
}
