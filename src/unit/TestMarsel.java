package unit;
import comp.Cat;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
public class TestMarsel {
    Cat testCat;
    @BeforeEach
    public void init() {
        testCat = new Cat(100, "Тест");
    }
    //Метод дожен начинаться с should...
    //TODO: Разобраться почему null
    //Ответ - разные версии BeforeEach (JUnit 5) и Test (JUnit 4)
    @Test
    public void shouldMeowReduceWeightByOne() throws Exception {
        //Создаете объект который хотите протестить
        //Восоздаете тестируемую ситуацию
        //Сравниваете объект с тем, который ожидаете получить

        testCat.meow();
        assert testCat.getWeight() == 99;
    }
    //Тест-кейс: при попытке покомить кота отрицательным кол-вом корма, вес не изменяется
    //Ожидаем ошибку
    @Test(expected = Exception.class)
    public void shouldFeedWithNegativeIntNotAffectWeightAndThrowException() throws Exception {
        testCat.feed(-100);
        assert testCat.getWeight() == 100;
    }
    //Тест-кейс: при "мяу" с весом 0, вес не может быть меньше 0
    //Ждем ошибку
    @Test(expected = Exception.class)
    public void test() throws Exception {
        for (int i = 0; i < 101; i++) {
            testCat.meow();
        }
        assert testCat.getWeight() == 0;
    }
}
