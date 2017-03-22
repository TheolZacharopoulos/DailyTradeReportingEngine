package business_logic.working_days;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DefaultWorkingDaysTest {

    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = DefaultWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Monday() throws Exception {
        final LocalDate aMonday = LocalDate.of(2017, 3, 20);

        // should return the same, since Monday is a working by default
        assertEquals(aMonday, workingDays.findFirstWorkingDate(aMonday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2017, 3, 24);

        // should return the same, since Friday is a working by default
        assertEquals(aFriday, workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2017, 3, 25);

        // should return the first Monday (27/3/2017), since Saturday is not a working day
        assertEquals(LocalDate.of(2017, 3, 27), workingDays.findFirstWorkingDate(aSaturday));
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2017, 3, 26);

        // should return the first Monday (27/3/2017), since Sunday is not a working day
        assertEquals(LocalDate.of(2017, 3, 27), workingDays.findFirstWorkingDate(aSunday));
    }
}