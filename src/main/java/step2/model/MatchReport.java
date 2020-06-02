package step2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MatchReport {

    private static final int DECIMAL_DIGIT = 2;

    private final MoneyAmount moneyAmount;
    private final MatchResult matchResult;

    private MatchReport(MoneyAmount moneyAmount, MatchResult matchResult) {
        if (isNull(moneyAmount, matchResult)) {
            throw new IllegalArgumentException();
        }

        this.moneyAmount = moneyAmount;
        this.matchResult = matchResult;
    }

    private boolean isNull(MoneyAmount moneyAmount, MatchResult matchResult) {
        return moneyAmount == null || matchResult == null;
    }

    public static MatchReport create(MoneyAmount moneyAmount, MatchResult matchResult) {
        return new MatchReport(moneyAmount, matchResult);
    }

    public BigDecimal calculateRateOfReturn() {
        BigDecimal winningMoney = BigDecimal.valueOf(this.matchResult.calculateTotalWinningMoney());
        BigDecimal usedAmount = BigDecimal.valueOf(moneyAmount.getUsedAmount().getValue());

        if (BigDecimal.ZERO.equals(usedAmount)) {
            return BigDecimal.ZERO;
        }

        return winningMoney.divide(usedAmount, DECIMAL_DIGIT, RoundingMode.DOWN);
    }
}