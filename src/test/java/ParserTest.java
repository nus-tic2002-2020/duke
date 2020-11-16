import exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parser.Parser;

public class ParserTest {
    @Test
    public void getDescription_withoutDescription_exceptionThrow() {
        Parser parser = new Parser("event");

        Assertions.assertThrows(DukeException.class, () -> {
            parser.getDescription();
        });
    }

    @Test
    public void getDescription_withDescription_success() throws DukeException {
        Parser parser = new Parser("event happy new year /at 2020-12-31 23:59");

        String description = "happy new year";

        Assertions.assertEquals(description, parser.getDescription());
    }
}
