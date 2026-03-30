package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.service.impl.FileReaderImpl;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {

    @Test
    void read_validFile_ok() throws IOException {
        FileReaderImpl fileReaderImpl = new FileReaderImpl();
        List<String> strings = List.of("type,fruit,quantity",
                "b,banana,20",
                "b,apple,100",
                "s,banana,100",
                "p,banana,13",
                "r,apple,10",
                "p,apple,20",
                "p,banana,5",
                "s,banana,50"
        );
        List<String> read = fileReaderImpl.read("reportToRead.csv");
        assertEquals(strings, read);
    }
}
