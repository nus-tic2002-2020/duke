package duke.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeStorageTest {

    String testText = "Wedding/16/James & Jamie/08-Sep-2020 03:12/true/29-Sep-2020 18:30/" +
            "29-Sep-2020 23:30/300/567.23/567.23/567.23/0.0/false/false/08-Sep-2020 03:45\n";

    @Test
    void encodeAndDecodeTest() {

        DukeStorage testStorage = new DukeStorage();
        String encodedText = testStorage.encodeText(testText);
        String decodedText = testStorage.decodeText(encodedText);
        assertEquals(testText, decodedText);
    }
}
