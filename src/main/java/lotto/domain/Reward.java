package lotto.domain;

import lotto.util.RewardTable;

import java.util.Iterator;
import java.util.Map;

public class Reward {

    public Reward() {}

    public Long sumTotalMatchPrice(Map<RewardTable, Long> map) {
        Long totalRewardPrice = 0L;

        Iterator<RewardTable> keys = map.keySet().iterator();

        while (keys.hasNext()) {
            RewardTable key = keys.next();
            Long value = map.get(key);

            totalRewardPrice += sumMatchPrice(key, value);
        }

        return totalRewardPrice;
    }

    private Long sumMatchPrice(RewardTable key, Long value) {
        return key.matchReward(value);
    }
}
