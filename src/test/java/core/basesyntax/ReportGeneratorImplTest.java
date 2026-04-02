package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Storage;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import core.basesyntax.service.strategy.BalanceOperation;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ReportGeneratorImplTest {

    @Test
    void getReport_oneFruit_ok() {
        Storage storage = new Storage();
        Map<String, Integer> fruitFromStorage = storage.getStorage();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        fruitFromStorage.put("apple", 100);
        String report = reportGenerator.getReport();

        assertEquals("fruit,quantity\napple,100\n", report);
    }

    @Test
    void getReport_multipleFruits_ok() {
        Storage storage = new Storage();
        Map<String, Integer> fruitFromStorage = storage.getStorage();
        fruitFromStorage.put("apple", 100);
        fruitFromStorage.put("banana", 20);

        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

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
