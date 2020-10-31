import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

     @Test
    public void toSaveString_format() {
        String dateTime = "22/02/2020 1230";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(dateTime, inputFormatter);
        Task event = new Event("Eat", date);
    }

  
}