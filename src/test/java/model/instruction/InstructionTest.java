package model.instruction;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

    @Test
    public void testTradeAmountCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20), // Its a Monday
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        agreedFx,
                        units,
                        pricePerUnit));

        // test initialization
        assertEquals(agreedFx, fakeInstruction.getAgreedFx());
        assertEquals(pricePerUnit, fakeInstruction.getPricePerUnit());
        assertEquals(units, fakeInstruction.getUnits());

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units))
                                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(correct, fakeInstruction.getTradeAmount());
    }
}