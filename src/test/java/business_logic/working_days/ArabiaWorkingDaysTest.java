package business_logic.working_days;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ArabiaWorkingDaysTest {
    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = ArabiaWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2017, 3, 26);

        // should return the same, since Sunday is a working day in Arabia
        assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate aThursday = LocalDate.of(2017, 3, 23);

        // should return the same, since Thursday is a working day in Arabia
        assertEquals(aThursday, workingDays.findFirstWorkingDate(aThursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2017, 3, 24);

        // should return the first Sunday (26/3/2017), since Friday is not a working day
        assertEquals(LocalDate.of(2017, 3, 26), workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2017, 3, 25);

        // should return the first Sunday (26/3/2017), since Saturday is not a working day
        assertEquals(LocalDate.of(2017, 3, 26), workingDays.findFirstWorkingDate(aSaturday));
    }

}