package firstLesson.unit;

import firstLesson.comp.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMarsel2 {

    Cat testCat;

    @BeforeEach
    public void init() {
        testCat = new Cat(100, "Тест");
    }

    //Метод дожен начинаться с should...

    //TODO: Разобраться почему null
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
    @Test
    public void shouldFeedWithNegativeIntNotAffectWeightAndThrowException() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            testCat.feed(-100);
        });
        assertEquals(exception.getMessage(), "Нефиг у кота корм отбирать!");
        assertEquals(testCat.getWeight(), 100);
    }

    //Тест-кейс: при "мяу" с весом 0, вес не может быть меньше 0
    //Ждем ошибку
    @Test
    public void test() {
        Exception exception = assertThrows(Exception.class, () -> {
            for (int i = 0; i < 101; i++) {
                testCat.meow();
            }
        });

        assertEquals(testCat.getWeight(), 0);
        assertEquals(exception.getMessage(), "Отстань, нет сил мяукать!");
    }
}
