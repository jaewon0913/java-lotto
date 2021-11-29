package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoNumber implements CreatableLottoNumber {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;

    private static final List<Integer> NUMBER;

    static {
        NUMBER = IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Integer> createLottoNumber() {
        Collections.shuffle(NUMBER);
        return new ArrayList<>(NUMBER.subList(FROM_INDEX, TO_INDEX));
    }
}