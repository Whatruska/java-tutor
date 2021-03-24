package Lesson_1.unit;
import Lesson_1.comp.Cat;
//JUnit 5
import org.junit.jupiter.api.Test;
//JUnit 5
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(testCat.getWeight(), 99);
    }
    //Тест-кейс: при попытке покомить кота отрицательным кол-вом корма, вес не изменяется
    //Ожидаем ошибку
    @Test
    public void shouldFeedWithNegativeIntNotAffectWeightAndThrowException() {
        assertThrows(Exception.class, () -> {
            testCat.feed(-100);
        });
        assertEquals(testCat.getWeight(), 100);
    }
    //Тест-кейс: при "мяу" с весом 0, вес не может быть меньше 0
    //Ждем ошибку
    @Test
    public void test() {
        assertThrows(Exception.class, () -> {
            for (int i = 0; i < 101; i++) {
                testCat.meow();
            }
        });
        assertEquals(testCat.getWeight(), 0);
    }
}
