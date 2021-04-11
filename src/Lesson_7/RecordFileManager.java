package Lesson_7;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecordFileManager {
    List<Record> readRecordsFromDataset();
    void writeCommonReport (Map<String, ClassReportDto> map);
    void writeSpecificReports (Map<String, Map<String, List<String>>> map);
}
