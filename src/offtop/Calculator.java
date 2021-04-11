package offtop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Calculator {

    final static private List<Action> actions = Arrays.asList(
            new Action('+', Integer::sum),
            new Action('-', (a, b) -> a - b),
            new Action('/', (a, b) -> a / b),
            new Action('*', (a, b) -> a * b),
            new Action('%', (a, b) -> a % b)
    );

    public static int calculate (int int1, int int2, char ch) {
        return actions.stream()
                .mapToInt(
                        action -> Stream.of(int1, int2, (int) ch)
                                .filter(el -> Stream.of(int1, int2, (int) ch)
                                .anyMatch(action.getPredicate()))
                                .limit(2).reduce(action.getReducer()).orElse(0)
                )
                .sum();
    }
}
