package business_logic;

import model.instruction.Instruction;
import model.instruction.InstructionDetails;
import model.instruction.TradeAction;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionSettlementDateCalculatorTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the first monday (27/3/2017)
        assertEquals(LocalDate.of(2017, 3, 27), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("AED"), // Its Arabia (AED)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the first Sunday (26/3/2017)
        assertEquals(LocalDate.of(2017, 3, 26), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SAR"), // Its Arabia (SAR)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }
}