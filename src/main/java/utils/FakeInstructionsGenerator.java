package utils;

import model.instruction.Instruction;
import model.instruction.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class FakeInstructionsGenerator {
    public static Set<Instruction> getFakeInstructions() {
        return new HashSet<>(Arrays.asList(

            new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20),
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25)),

            new Instruction(
                "E2",
                TradeAction.BUY,
                Currency.getInstance("AED"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 19),
                BigDecimal.valueOf(0.22),
                450,
                BigDecimal.valueOf(150.5)),

            new Instruction(
                "E3",
                TradeAction.SELL,
                Currency.getInstance("SAR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 18),
                BigDecimal.valueOf(0.27),
                150,
                BigDecimal.valueOf(400.8)),

            new Instruction(
                "E4",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                BigDecimal.valueOf(0.34),
                50,
                BigDecimal.valueOf(500.6)),

            new Instruction(
                "E5",
                TradeAction.BUY,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                BigDecimal.valueOf(0.34),
                20,
                BigDecimal.valueOf(40.6)),

            new Instruction(
                "E6",
                TradeAction.BUY,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                BigDecimal.valueOf(0.34),
                20,
                BigDecimal.valueOf(40.6)),

            new Instruction(
                "E7",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                BigDecimal.valueOf(0.34),
                1000,
                BigDecimal.valueOf(160.6)),

            new Instruction(
                "E8",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                BigDecimal.valueOf(0.34),
                120,
                BigDecimal.valueOf(500.6))
        ));
    }
}
