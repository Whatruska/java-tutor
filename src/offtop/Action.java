package offtop;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Action {
    private char sign;
    private Predicate<Integer> predicate;
    private BinaryOperator<Integer> reducer;

    public Action(char sign, BinaryOperator<Integer>  reducer) {
        this.sign = sign;
        this.predicate = (e -> (char) e.intValue() == sign);
        this.reducer = reducer;
    }

    public char getSign() {
        return sign;
    }

    public Predicate<Integer> getPredicate() {
        return predicate;
    }

    public BinaryOperator<Integer> getReducer() {
        return reducer;
    }
}
