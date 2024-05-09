import com.example.laba3.beans.PointBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointValidationTest {
    @Test
    void checkInAreaTest() {
        assertTrue(PointBean.validation(1, 1, 1));
    }

    @Test
    void checkOutAreaTest() {
        assertTrue(PointBean.validation(999, 999, 999));
    }
}
