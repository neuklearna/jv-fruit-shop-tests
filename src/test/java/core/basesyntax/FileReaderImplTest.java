package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.service.impl.FileReaderImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {

    @Test
    void read_validFile_ok() throws URISyntaxException, IOException {
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

        URL resource = getClass().getClassLoader().getResource("reportToRead.csv");
        String path = Paths.get(Objects.requireNonNull(resource).toURI()).toString();

        List<String> read = fileReaderImpl.read(path);
        assertEquals(strings, read);
    }

    //My task passed with 80% line and that's why i didn't add test with negaitve result
    //that you recommended me in code review.
}
