package Lesson_6;

/*import Lesson_5.WeatherDto;
import Lesson_5.WeatherDtoBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Loader_2 {
    public static void main(String[] args) throws IOException {
        File file = new File(Paths.get("src/Lesson_6/data.xml").toUri());

//        WeatherDto dto = new WeatherDtoBuilder()
//                .date(new Date())
//                .temperature(14.0)
//                .windDirection(90.0)
//                .windSpeed(10.0)
//                .humidity(70.)
//                .build();
//
//        WeatherDto dto2 = new WeatherDtoBuilder()
//                .date(new Date())
//                .temperature(1.)
//                .windDirection(9.0)
//                .windSpeed(40.0)
//                .humidity(15.)
//                .build();

        System.out.println(
                readDtoListFromFile(file)
        );
    }

    //Частный случай
    public static void writeDtoToXML(WeatherDto dto, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("weather", WeatherDto.class);
        String xml = xstream.toXML(dto);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(xml.getBytes());
    }

    //Частный случай
    public static void writeDtosToXML(List<WeatherDto> dtos, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("weather", WeatherDto.class);
        String xml = xstream.toXML(dtos);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(xml.getBytes());
    }

    private static void writeObjToXML(Object obj, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("weather", WeatherDto.class);
        String xml = xstream.toXML(obj);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(xml.getBytes());
    }

    private static WeatherDto readDtoFromFile(File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("weather", WeatherDto.class);
        String xml = Files.lines(Paths.get(file.toURI())).reduce((prevString, currString) -> prevString + "\n" + currString).get();
        /*"<weather>","<date>...</date>","<temperature>10.0</temperature>
            <weather>
                <date>...<date>
                <temperature>10.0</temperature>
         */
/*
        WeatherDto dto = (WeatherDto) xstream.fromXML(xml);
        return dto;
    }

    private static List<WeatherDto> readDtoListFromFile(File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("weather", WeatherDto.class);
        String xml = Files.lines(Paths.get(file.toURI())).reduce((prevString, currString) -> prevString + "\n" + currString).get();
        List<WeatherDto> dtos = (List<WeatherDto>) xstream.fromXML(xml);
        return dtos;
    }
}
 */
