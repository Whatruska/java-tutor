package Lesson_7;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecordFileManager {
    List<Record> readRecordsFromDataset();
    void createMainReportFile(
            Map<String, Integer> participantsMap,
            Map<String, String> formatMap
    );
    void createReportForClass(
            String className,
            Map<String, Set<String>> groupStatMap
    );
}
