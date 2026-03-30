package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.service.impl.FileWriterImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class FileWriterImplTest {

    @Test
    void write_validContent_ok() throws IOException {
        FileWriterImpl fileWriterImpl = new FileWriterImpl();
        fileWriterImpl.write("fruit,quantity\napple,100\n", "testOutput.csv");
        String result = Files.readString(Path.of("testOutput.csv"));
        assertEquals("fruit,quantity\napple,100\n", result);
    }
}
