package Lesson_7;

import java.util.*;
import java.util.stream.Collectors;

public class RecordManagerImpl implements RecordManager {
    private List<Record> records;
    private Set<String> possibleClasses;

    public RecordManagerImpl (List<Record> records) {
        this.records = records;
        this.possibleClasses = new TreeSet<>();
        records.stream().map(Record::getClasses).forEach(r -> this.possibleClasses.addAll(r));
    }

    @Override
    //<МК -> <группа -> [записи, соотв. этой группе]>>
    public Map<String, Map<String, List<Record>>> getGeneralClassesData() {
        Map<String, Map<String, List<Record>>> data = new TreeMap<>();
        possibleClasses.stream().forEach(possibleClass -> {
            //Мапа <группа, список записей, в котором group равен этой группе>
            Map<String, List<Record>> map = records.stream()
                    .filter(record -> record.getClasses().contains(possibleClass))
                    .collect(Collectors.groupingBy(Record::getGroup));
            data.put(possibleClass, map);
        });
        return data;
    }

    @Override
    //Мк -> <группа -> список фамилий>
    public Map<String, Map<String, List<String>>> getGroupFioData() {
        Map<String, Map<String, List<Record>>> data = getGeneralClassesData();
        Map<String, Map<String, List<String>>> newData = new TreeMap<>();
        data.keySet().forEach(clas -> {
            //группа -> список записей этой группы
            Map<String, List<Record>> relevantsRecordsMap = data.get(clas);
            //группа -> список фамилий группы
            Map<String, List<String>> newRelevantData = new TreeMap<>();
            relevantsRecordsMap.keySet().forEach(group -> {
                newRelevantData
                        .put(group,
                                relevantsRecordsMap.get(group).stream()
                                        .map(Record::getFio)
                                        .sorted()
                                        .collect(Collectors.toList())
                        );
            });
            newData.put(clas, newRelevantData);
        });
        return newData;
    }

    @Override
    //Мк -> {число записавшихся, препочтительный формат}
    public Map<String, ClassReportDto> getClassInfo() {
        Map<String, Map<String, List<Record>>> data = getGeneralClassesData();
        Map<String, ClassReportDto> newData = new TreeMap<>();
        data.keySet().forEach(clas -> {
            //группа -> [записи группы]
            Map<String, List<Record>> relevantsRecordsMap = data.get(clas);
            //"11-001" -> 1, "11-002" -> 2, ..
            //1, 2, 3, 5, 6
            long totalPeopleCount = relevantsRecordsMap.keySet().stream().mapToLong(group -> {
                List<Record> relevantRecords = relevantsRecordsMap.get(group);
                return relevantRecords.size();
            }).sum();
            long distPeopleCount = relevantsRecordsMap.keySet().stream().mapToLong(group -> {
                List<Record> relevantRecords = relevantsRecordsMap.get(group);
                return relevantRecords.stream().filter(record -> record.getFormat().equals("Дистанционный")).count();
            }).sum();

            String preferedFormat = "Очный";
            if (distPeopleCount >= (totalPeopleCount / 2)) {
                preferedFormat = "Дистанционный";
            }

            newData.put(clas, ClassReportDto.builder()
                    .peopleCount(totalPeopleCount)
                    .preferedFormat(preferedFormat)
                    .build()
            );
        });
        return newData;
    }
}
