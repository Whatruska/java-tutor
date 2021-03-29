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

    private void createReportFolder() {
        File folder = new File(Paths.get(REPORT_FOLDER_PATH).toUri());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    @Override
    public void createMainReportFile(Map<String, Integer> participantsMap, Map<String, String> formatMap) {
        createReportFolder();
        File reportFile = new File(Paths.get(REPORT_FOLDER_PATH + "/report.txt").toUri());
        if (!reportFile.exists()) {
            try {
                reportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter writer = new PrintWriter(reportFile);
            participantsMap
                    .keySet()
                    .stream().sorted((clas1, clas2) -> {
                        int participants1 = participantsMap.get(clas1);
                        int participants2 = participantsMap.get(clas2);
                        return participants2 - participants1;
                    })
                    .forEach(clas -> {
                String line = clas + " | " + participantsMap.get(clas) + " | " + formatMap.get(clas);
                writer.println(line);
            });
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createReportForClass(String className, Map<String, Set<String>> groupStatMap) {
        createReportFolder();
        File subfolder = new File(Paths.get(REPORT_FOLDER_PATH + "/" + className).toUri());
        if (!subfolder.exists()) {
            subfolder.mkdir();
        }

        File infoFile = new File(Paths.get(REPORT_FOLDER_PATH + "/" + className + "/info.txt").toUri());
        try {
            PrintWriter out = new PrintWriter(infoFile);
            groupStatMap.keySet().forEach(group -> {
                out.println(group);
                out.println("-------");
                Set<String> people = groupStatMap.get(group);
                long peopleCount = people.size();
                out.println("Всего людей: " + peopleCount);
                people.forEach(out::println);
                out.println("\n");
            });
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static Record deserialize(String line) {
        String[] lineParts = line.split("\t+");
        Set<String> classes = Arrays.stream(lineParts[3].split(",")).map(clas ->
            clas.trim().replaceAll("/", " | ")
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
