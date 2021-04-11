package Lesson_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RecordFileManagerImpl implements RecordFileManager{
    private static final String FILE_PATH = "src/Lesson_7/dataset.tsv";
    private static final String REPORT_FOLDER_PATH = "src/Lesson_7/report";

    private static Set<String> readAllInfoFromFile() {
        File file = new File(Paths.get(FILE_PATH).toUri());
        Set<String> allStrings = new TreeSet<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                allStrings.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allStrings;
    }

    @Override
    public List<Record> readRecordsFromDataset() {
        List<Record> list = new ArrayList<>();

        Set<String> lines = readAllInfoFromFile();
        for (String line : lines) {
            Record record = deserialize(line);
            list.add(record);
        }

        return list;
    }

    @Override
    public void writeCommonReport(Map<String, ClassReportDto> map) {
        createReportFolder();
        File commonReportFile = new File(Paths.get(REPORT_FOLDER_PATH + "/report.txt").toUri());
        try {
            PrintWriter writer = new PrintWriter(commonReportFile);
            map.keySet().forEach(clas -> {
                ClassReportDto reportDto = map.get(clas);
                writer.println(clas + " | " + reportDto.getPeopleCount() + " | " + reportDto.getPreferedFormat());
            });
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeSpecificReports(Map<String, Map<String, List<String>>> map) {
        createReportFolder();
        map.keySet().forEach(clas -> {
            File classFolder = new File(Paths.get(REPORT_FOLDER_PATH + "/" + clas).toUri());
            classFolder.mkdir();
            File infoFile = new File(Paths.get(REPORT_FOLDER_PATH + "/" + clas + "/info.txt").toUri());
            try {
                PrintWriter writer = new PrintWriter(infoFile);
                Map<String, List<String>> groupMap = map.get(clas);
                groupMap.keySet().forEach(group -> {
                    writer.println(group);
                    writer.println("-------");
                    List<String> fios = groupMap.get(group);
                    fios.forEach(writer::println);
                    writer.println("Всего людей: " + fios.size());
                    writer.println("\n");
                });
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void createReportFolder() {
        File folder = new File(Paths.get(REPORT_FOLDER_PATH).toUri());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private static Record deserialize(String line) {
        String[] lineParts = line.split("\t+");
        Set<String> classes = Arrays.stream(lineParts[3].split(",")).map(clas ->
            clas.trim().replaceAll("/", " - ")
        ).collect(Collectors.toSet());
        return new Record.RecordBuilder()
                .date(lineParts[0].trim())
                .fio(lineParts[1].trim())
                .group(lineParts[2].trim())
                .classes(classes)
                .format(lineParts[4].trim())
                .build();
    }
}
