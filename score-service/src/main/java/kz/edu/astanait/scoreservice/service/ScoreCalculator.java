package kz.edu.astanait.scoreservice.service;

import kz.edu.astanait.scoreservice.enums.ScoreTransactionType;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author aldi
 * @since 01.06.2024
 */
public class ScoreCalculator {
    private static final Map<ScoreTransactionType, BiFunction<BigDecimal, BigDecimal, BigDecimal>> transactionMap = createTransactionMap();

    private static Map<ScoreTransactionType, BiFunction<BigDecimal, BigDecimal, BigDecimal>> createTransactionMap() {
        Map<ScoreTransactionType, BiFunction<BigDecimal, BigDecimal, BigDecimal>> map = new EnumMap<>(ScoreTransactionType.class);

        map.put(ScoreTransactionType.ADD, BigDecimal::add);
        map.put(ScoreTransactionType.SUBTRACT, (current, toCalc) -> {
            if (current.compareTo(toCalc) < 0) {
                throw new RuntimeException("Score is not enough");
            }
            return current.subtract(toCalc);
        });

        return map;
    }

    public static BigDecimal calculateScore(BigDecimal current, BigDecimal toCalc, ScoreTransactionType transactionType) {
        if (current == null || toCalc == null || transactionType == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        var operation = transactionMap.get(transactionType);
        if (operation == null) {
            throw new RuntimeException("Unknown transaction type: " + transactionType);
        }

        return operation.apply(current, toCalc);
    }

    public static BigDecimal calculateAllTimeScore(BigDecimal current, BigDecimal toCalc, ScoreTransactionType transactionType) {
        return transactionType.equals(ScoreTransactionType.ADD) ? current.add(toCalc) : current;
    }
}
