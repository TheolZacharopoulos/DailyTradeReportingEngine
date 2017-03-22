package business_logic;

import business_logic.ranking.Rank;
import model.instruction.Instruction;
import model.instruction.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * Describes a mapping between dates and the trade amount of those dates, based on instructions
 */
public class InstructionSettlementStatsCalculator {

    // Create a predicate for outgoing
    private final static Predicate<Instruction> outgoingInstructionsPredicate =
            instruction -> instruction.getAction().equals(TradeAction.BUY);

    // Create a predicate for incoming
    private final static Predicate<Instruction> incomingInstructionsPredicate =
            instruction -> instruction.getAction().equals(TradeAction.SELL);

    /**
     * Calculates the daily outgoing (BUY) trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Instruction> instructions) {
        return calculateDailyAmount(instructions, outgoingInstructionsPredicate);
    }

    /**
     * Calculates the daily incoming (SELL) trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Instruction> instructions) {
        return calculateDailyAmount(instructions, incomingInstructionsPredicate);
    }

    /**
     * Ranks the daily outgoing (BUY) by trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Rank>> calculateDailyOutgoingRanking(Set<Instruction> instructions) {
        return calculateRanking(instructions, outgoingInstructionsPredicate);
    }

    /**
     * Ranks the daily incoming (SELL) by trade amount in USD
     * @param instructions the instruction to calculate the stats from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Rank>> calculateDailyIncomingRanking(Set<Instruction> instructions) {
        return calculateRanking(instructions, incomingInstructionsPredicate);
    }

    private static Map<LocalDate, BigDecimal> calculateDailyAmount(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        return instructions.stream()
                .filter(predicate)
                .collect(groupingBy(Instruction::getSettlementDate,
                    mapping(Instruction::getTradeAmount,
                        reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    private static Map<LocalDate, LinkedList<Rank>> calculateRanking(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

        instructions.stream()
            .filter(predicate)
            .collect(groupingBy(Instruction::getSettlementDate, toSet()))
            .forEach((date, dailyInstructionSet) -> {
                final AtomicInteger rank = new AtomicInteger(1);

                final LinkedList<Rank> ranks = dailyInstructionSet.stream()
                    .sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
                    .map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntity(), date))
                    .collect(toCollection(LinkedList::new));

                ranking.put(date, ranks);
            });

        return ranking;
    }
}
