package Lesson_7;

import java.util.List;
import java.util.Map;

public interface RecordManager {
    Map<String, Map<String, List<Record>>> getGeneralClassesData();
    Map<String, Map<String, List<String>>> getGroupFioData();
    Map<String, ClassReportDto> getClassInfo();
}
