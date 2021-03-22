package fifthLesson;

//Декларируем что должен делать менеждер наблюдений
//Просто красота, можно обойтись и без этого
public interface WeatherManager {
    Double countAverageTemperature();
    Double countAverageHumidity();
    Double countAverageWindSpeed();
    WeatherDto findDtoWithMaxTemp();
    WeatherDto findDtoWithMinHumidity();
    WeatherDto findDtoWithMaxWindSpeed();
    String findCommonWindDirection();
}
