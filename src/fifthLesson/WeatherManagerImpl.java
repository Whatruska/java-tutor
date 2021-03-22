package fifthLesson;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WeatherManagerImpl implements WeatherManager{
    //Список наблюдений
    private List<WeatherDto> weatherDtos;

    public WeatherManagerImpl(List<WeatherDto> weatherDtos) {
        this.weatherDtos = weatherDtos;
    }

    @Override
    public Double countAverageTemperature() {
        //Берем все наблюдения и вместо каждого наблюдения вставляем его температуру, затем считаем среднее
        return weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getTemperature()).average().getAsDouble();
    }

    @Override
    public Double countAverageHumidity() {
        return weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getHumidity()).average().getAsDouble();
    }

    @Override
    public Double countAverageWindSpeed() {
        return weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getWindSpeed()).average().getAsDouble();
    }

    @Override
    public WeatherDto findDtoWithMaxTemp() {
        Double maxTemp = weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getTemperature()).max().getAsDouble();
        //Фильтруем лист наблюдений по максимальной температуре
        return weatherDtos.stream().filter((weatherDto) -> weatherDto.getTemperature().equals(maxTemp)).findFirst().get();
    }

    @Override
    public WeatherDto findDtoWithMinHumidity() {
        Double minHumidity = weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getHumidity()).min().getAsDouble();
        return weatherDtos.stream().filter((weatherDto) -> weatherDto.getHumidity().equals(minHumidity)).findFirst().get();
    }

    @Override
    public WeatherDto findDtoWithMaxWindSpeed() {
        Double maxWindSpeed = weatherDtos.stream().mapToDouble((weatherDto) -> weatherDto.getWindSpeed()).max().getAsDouble();
        return weatherDtos.stream().filter((weatherDto) -> weatherDto.getWindSpeed().equals(maxWindSpeed)).findFirst().get();
    }

    @Override
    public String findCommonWindDirection() {
        //Берем лист наблюдений и каждому наблюдению ставим НАПРАВЛЕНИЕ в соответствии с градусом ветра (см. картинку)
        List<String> directions = weatherDtos.stream().map((weatherDto) -> {
            double windDirection = weatherDto.getWindDirection();
            if (windDirection >= 315 || windDirection < 45) {
                return "N";
            } else if (windDirection >= 45 && windDirection < 135) {
                return "E";
            } else if (windDirection >= 135 && windDirection < 225) {
                return "S";
            } else {
                return "W";
            }
        }).collect(Collectors.toList());

        //В этой карте будем отображать, сколько раз встретилось то или иное направление
        TreeMap<String, Long> map = new TreeMap<>();

        //Фильтруем по направлению и при помощи .count() смотрим, сколько наблюдений удовлетворило предикату
        map.put("N", directions.stream().filter(direction -> direction.equals("N")).count());
        map.put("E", directions.stream().filter(direction -> direction.equals("E")).count());
        map.put("S", directions.stream().filter(direction -> direction.equals("S")).count());
        map.put("W", directions.stream().filter(direction -> direction.equals("W")).count());

        //Получим что-то в виде
        //{N -> 5}
        //{S -> 4}
        //...

        //Берем все значения карты и находим максимальное
        Collection<Long> values = map.values();
        Long maxValue = values.stream().mapToLong(x -> x).max().getAsLong();

        //Берем все ключи карты (направления)
        Set<String> keySet = map.keySet();
        //Находим какому ключу соответствует максимальное значение (если неск, берем первое)
        return keySet.stream().filter(key -> map.get(key).equals(maxValue)).findFirst().get();
    }
}
