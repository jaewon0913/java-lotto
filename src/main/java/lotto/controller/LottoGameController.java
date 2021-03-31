package lotto.controller;

import lotto.domain.LottoBall;
import lotto.domain.LottoBalls;
import lotto.domain.LottoGame;
import lotto.domain.LottoStore;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.WinningBall;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoGameController {

  public void start() {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    Money money = inputView.inputMoney();
    if (!money.availableBuyLotto()) {
      resultView.printUnavailableGame();
      return;
    }

    LottoGame lottoGame = LottoStore.sell(money);

    resultView.printLottoBuySize(lottoGame);
    resultView.printBuyingLottoGame(lottoGame);

    LottoBalls winLottoBalls = new LottoBalls(inputView.inputWinNumbers());
    LottoBall bonusBall = inputView.inputBonusBall();
    WinningBall winningBall = new WinningBall(winLottoBalls, bonusBall);

    Statistics statistics = lottoGame.selectPrizeWinning(winningBall);

    resultView.printLottoResult(money, statistics);
  }

}