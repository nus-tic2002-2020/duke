import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tasks.DoWithinPeriod;
import java.time.LocalDate;

public class DoWithinPeriodTaskTest {
    @Test
    public void doWithinPeriodTask() {
        String description = "To collect ticket";

        String fromDate = ("2020-12-01");
        LocalDate localFromDate = LocalDate.parse(fromDate);
        String toDate = ("2020-12-07");
        LocalDate localToDate = LocalDate.parse(toDate);

        DoWithinPeriod collectionDoWithinPeriod = new DoWithinPeriod(description,"P",localFromDate,localToDate,false);

        assertEquals("[P][\u2718] To collect ticket between Dec 1 2020 and Dec 7 2020",collectionDoWithinPeriod.getTaskListInfo());

    }
}
