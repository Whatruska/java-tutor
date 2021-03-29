package Lesson_7;

import java.util.Map;
import java.util.Set;

public class Loader {
    public static void main(String[] args) {
        RecordFileManager fileManager = new RecordFileManagerImpl();
        RecordManager recordManager = new RecordManagerImpl(fileManager.readRecordsFromDataset());

        Map<String, Integer> peopleMap = recordManager.getClassParticipantsInfo();
        Map<String, String> formatMap = recordManager.getClassPreferedFormat();

        fileManager.createMainReportFile(peopleMap, formatMap);

        Set<String> possibleClasses = recordManager.getPossibleClasses();
        for (String clas: possibleClasses) {
            Map<String, Set<String>> groupStat = recordManager.getGroupStatisticForClass(clas);
            fileManager.createReportForClass(clas, groupStat);
        }
    }
}
