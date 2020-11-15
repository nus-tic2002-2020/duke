package tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    @Test
    void testDeadline(){
        Task tester = new Deadline("Duke", LocalDateTime.parse("20/12/2020 1200", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        assertEquals("[D][" + tester.getStatusIcon() + "]Duke (by: 20/12/2020 1200)", tester.toString());
    }

    @Test
    void testToSaveFormat() {
        Task tester = new Deadline("Duke", LocalDateTime.parse("12 12 2020 12:00", DateTimeFormatter.ofPattern("MM dd yyyy HH:mm")));
        assertEquals("D|0|Duke|12 12 2020 12:00", tester.saveFormat());
    }
}
