package Lesson_6;

/*import Lesson_5.WeatherDto;
import Lesson_5.WeatherDtoBuilder;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loader {
    //Сериализация - процесс приведения объекта к строке
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File(Paths.get("src/Lesson_6/data.txt").toUri());
//        WeatherDto dto = new WeatherDtoBuilder()
//                .date(new Date())
//                .temperature(14.0)
//                .windDirection(90.0)
//                .windSpeed(10.0)
//                .humidity(70.)
//                .build();
//        WeatherDto dto2 = new WeatherDtoBuilder()
//                .date(new Date())
//                .temperature(1.)
//                .windDirection(9.0)
//                .windSpeed(40.0)
//                .humidity(15.)
//                .build();
//        List<WeatherDto> list = new ArrayList<>();
//        list.add(dto);
//        list.add(dto2);
//        writeWeatherDtoListToFile(list, file);
        System.out.println(
                readDtoFromFile(file)
        );
    }

    public static void writeWeatherDtoListToFile(List<WeatherDto> dtos, File file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(outputStream);

        for (WeatherDto dto : dtos) out.writeObject(dto);

        out.flush();
        out.close();
    }

    public static List<WeatherDto> readDtoFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(inputStream);

        ArrayList<WeatherDto> dtos = new ArrayList<>();

        //TODO: Вспомнить как в цикле считывать инфу из стрима
        /*
            while(stream.hasNext()) {...}
         */
/*
        dtos.add((WeatherDto) in.readObject());
        dtos.add((WeatherDto) in.readObject());

        in.close();
        return dtos;
    }
}
*/
