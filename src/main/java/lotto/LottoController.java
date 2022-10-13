package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoReward;
import lotto.domain.Money;
import lotto.ui.LottoInput;
import lotto.ui.LottoOutput;

import java.util.List;

public class LottoController {

    private LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void draw() {
        Money money = purchaseMoney();

        List<Lotto> lottos = purchaseLotto(money);

        Lotto winner = drawWinner();

        lottoResult(money, lottos, winner);
    }

    private Money purchaseMoney() {
        LottoOutput.purchaseAmount();
        Money money = LottoInput.money();
        return money;
    }

    private List<Lotto> purchaseLotto(Money money) {
        int count = lottoService.purchaseNumber(money);
        LottoOutput.purchaseCount(count);
        List<Lotto> lottos = lottoService.purchaseLotto(count);
        LottoOutput.lotto(lottos);
        return lottos;
    }

    private Lotto drawWinner() {
        LottoOutput.winningNumber();
        Lotto winner = new Lotto(LottoInput.winnerNumbers());
        return winner;
    }

    private void lottoResult(Money money, List<Lotto> lottos, Lotto winner) {
        int[] matchList = lottoService.checkLotto(lottos, winner);
        
        LottoOutput.statistics();
        for (int i = 3; i <= lottoService.LOTTO_SIZE; i++) {
            LottoOutput.match(i, LottoReward.values()[lottoService.LOTTO_SIZE - i].reward(), matchList[i]);
        }

        LottoOutput.yield(lottoService.yield(lottos, winner, money));
    }
}