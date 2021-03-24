package Lesson_5;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

//Класс, отвечающий за работу с файлом наблюдений
public class WeatherFileManager {
    private File srcFile;

    public WeatherFileManager(File srcFile) {
        this.srcFile = srcFile;
    }

    //Считать все наблюдения из файла
    public List<WeatherDto> readWeatherFromFile() throws Exception {
        List<WeatherDto> weatherDtos = new ArrayList<>();

        Scanner scanner = new Scanner(srcFile);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            //Если считали наблюдение, десериализуем его и добавим в список
            if (isWeatherDto(parts)) {
                WeatherDto weatherDto = deserializeWeaterDto(parts);
                weatherDtos.add(weatherDto);
            }
        }

        return weatherDtos;
    }

    //Определяет, является ли строка наблюдением по его частям
    public boolean isWeatherDto(String[] parts) {
        String firstPart = parts[0];
        char ch = firstPart.charAt(0);
        return ch >= '0' && ch <= '9';
    }

    //Объект -> строка - сериализация
    //Строка -> объект - десериализация
    private WeatherDto deserializeWeaterDto (String[] parts) throws Exception {
        WeatherDto dto;
        WeatherDtoBuilder builder = new WeatherDtoBuilder();

        if (parts.length < 5) {
            throw new Exception("Parts length is " + parts.length);
        } else {
            String dateStr = parts[0];
            //Указываем в каком формате хранится дата в файле (см. табличку)
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmm");

            //Парсим дату в соотв. с форматом
            Date trueDate = format.parse(dateStr);
            Double temperature = Double.parseDouble(parts[1]);
            Double humidity = Double.parseDouble(parts[2]);
            Double windSpeed = Double.parseDouble(parts[3]);
            Double windDirection = Double.parseDouble(parts[4]);

            //Юзаем тут билдер, а могли бы конструктор от 5 переменных, но т.к 4 Double-переменные, можем запутаться
            dto = builder
                    .date(trueDate)
                    .temperature(temperature)
                    .humidity(humidity)
                    .windSpeed(windSpeed)
                    .windDirection(windDirection)
                    .build();
        }

        return dto;
    }

    //Считываем единицы измерения из файла
    public String[] readUnitsFromFile() throws Exception {
        String[] units = {};

        Scanner scanner = new Scanner(srcFile);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            //Первая часть должна быть равна "unit", если считываем единицы измерения
            if (parts[0].equals("unit")) {
                units = parts;
                break;
            }
        }

        return units;
    }
}
