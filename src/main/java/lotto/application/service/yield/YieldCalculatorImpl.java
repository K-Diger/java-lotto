package lotto.application.service.yield;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.application.service.yield.WinningMoney.*;

public class YieldCalculatorImpl implements YieldCalculator {

    private Map<String, Integer> winInformation = new HashMap<>() {{
        put("three", 0);
        put("four", 0);
        put("five", 0);
        put("fiveContainingBonusNumber", 0);
        put("six", 0);
    }};

    public Map<String, Integer> extractStatistic(List<List<Integer>> matchResult) {

        List<Integer> matchResultNotConsiderBonus = matchResult.get(0);
        List<Integer> bonusMatchingCount = matchResult.get(1);

        for (Integer integer : matchResultNotConsiderBonus) {
            if (integer == 3) {
                winInformation.put("three", (winInformation.get("three") + 1));
            } else if (integer == 4) {
                winInformation.put("four", (winInformation.get("four") + 1));
            } else if (integer == 5) {
                winInformation.put("five", (winInformation.get("five") + 1));
            } else if (integer == 6) {
                winInformation.put("six", (winInformation.get("six") + 1));
            }
        }
        winInformation = considerBonusNumber(bonusMatchingCount);
        return winInformation;
    }

    public Map<String, Integer> considerBonusNumber(List<Integer> bonusMatchingCount) {
        for (Integer integer : bonusMatchingCount) {
            if (integer == 1) {
                winInformation.put("fiveContainingBonusNumber", (winInformation.get("fiveContainingBonusNumber") + 1));
            }
        }
        winInformation.put("five", winInformation.get("five") - winInformation.get("fiveContainingBonusNumber"));

        return winInformation;
    }

    public void showStatistic(Map<String, Integer> winInformation) {
        System.out.println("\n당첨 통계\n---");
        System.out.printf("3개 일치 (5,000원) - %d개\n",
                winInformation.get("three"));
        System.out.printf("4개 일치 (50,000원) - %d개\n",
                winInformation.get("four"));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n",
                winInformation.get("five"));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n",
                winInformation.get("fiveContainingBonusNumber"));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n",
                winInformation.get("six"));
    }

    public double extractYield(Map<String, Integer> winInformation, int purchaseMoney) {
        double yield = 0;

        yield += (double) winInformation.get("three") * (double) (THREE.getWinningMoney());
        yield += (double) winInformation.get("four") * (double) (FOUR.getWinningMoney());
        yield += (double) winInformation.get("five") * (double) (FIVE.getWinningMoney());
        yield += (double) winInformation.get("fiveContainingBonusNumber") * (double) (FIVE_BONUS.getWinningMoney());
        yield += (double) winInformation.get("six") * (double) (SIX.getWinningMoney());

        return Math.round((yield / (double) purchaseMoney * 100) * 10) / 10.0;
    }

    public void showYield(Map<String, Integer> statistic, int purchaseNumber) {
        double yield = extractYield(statistic, (purchaseNumber * 1000));
        System.out.printf("총 수익률은 %s%%입니다.", Math.round(yield * 10) / 10.0);
    }
}
