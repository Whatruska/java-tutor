package Lesson_7;

import java.util.List;

public class Loader {
    public static void main(String[] args) {
        RecordFileManager fileManager = new RecordFileManagerImpl();
        List<Record> recordList = fileManager.readRecordsFromDataset();
        RecordManager recordManager = new RecordManagerImpl(recordList);
        fileManager.writeCommonReport(recordManager.getClassInfo());
        fileManager.writeSpecificReports(recordManager.getGroupFioData());
    }
}
