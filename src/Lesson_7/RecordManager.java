package Lesson_7;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecordManager {
    Set<Record> getAllRecordsByClass(String clas);
    Map<String, Set<String>> getGroupStatisticForClass(String clas);
    Set<String> getPossibleClasses();
    Map<String, Integer> getClassParticipantsInfo();
    Map<String, String> getClassPreferedFormat();
}
