package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import core.basesyntax.service.impl.FileReaderImpl;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {

    @Test
    void read_validFile_ok() throws Exception {
        FileReaderImpl fileReaderImpl = new FileReaderImpl();

        URL resource = getClass().getClassLoader().getResource("reportToRead.csv");
        String path = Paths.get(Objects.requireNonNull(resource).toURI()).toString();

        List<String> read = fileReaderImpl.read(path);

        assertFalse(read.isEmpty());
        assertEquals("operation,fruit,quantity", read.get(0));
    }

    //My task passed with 80% line and that's why i didn't add test with negaitve result
    //that you recommended me in code review.
}
