package Lesson_7;

import java.util.*;
import java.util.stream.Collectors;

public class RecordManagerImpl implements RecordManager {
    private List<Record> records;

    public RecordManagerImpl(List<Record> records) {
        this.records = records;
    }

    @Override
    public Set<Record> getAllRecordsByClass(String clas) {
        return records.stream()
                .filter(record -> record.getClasses().contains(clas))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Set<String>> getGroupStatisticForClass(String clas) {
        Map<String, Set<String>> statMap = new TreeMap<>();
        Set<Record> relevantRecords = getAllRecordsByClass(clas);
        relevantRecords.forEach(record -> {
            String group = record.getGroup();
            Set<String> oldSet = statMap.get(group);
            if (oldSet == null) {
                oldSet = new TreeSet<>();
            }
            oldSet.add(record.getFio());
            statMap.put(group, oldSet);
        });
        return statMap;
    }

    @Override
    public Set<String> getPossibleClasses() {
        Set<String> possibleClasses = new TreeSet<>();
        records.forEach(record -> {
            Set<String> classes = record.getClasses();
            possibleClasses.addAll(classes);
        });
        return possibleClasses;
    }

    @Override
    public Map<String, Integer> getClassParticipantsInfo() {
        Map<String, Integer> map = new TreeMap<>();
        //Сет всех созможных занятий
        Set<String> possibleClasses = getPossibleClasses();
        //Java -> 0
        //Python -> 0
        //PHP -> 0
        //...
        possibleClasses.forEach(clas -> map.put(clas, 0));
        records.forEach(record -> {
            Set<String> classes = record.getClasses();
            classes.forEach(clas -> {
                Integer oldValue = map.get(clas);
                map.put(clas, oldValue + 1);
            });
        });
        return map;
    }

    @Override
    public Map<String, String> getClassPreferedFormat() {
        Map<String, String> map = new TreeMap<>();
        Set<String> possibleClasses = getPossibleClasses();
        //Проходимся по всем возможным занятиям
        possibleClasses.forEach(clas -> {
            //Считаем сколько всего людей записалось на этот МК
            long n = records.stream()
                    .filter(record -> record.getClasses().contains(clas))
                    .count();
            //Считаем сколько всего людей записалось на этот МК ОЧНО
            long ochCount = (int) records.stream()
                    .filter(record -> record.getClasses().contains(clas))
                    .filter(record -> record.getFormat().equals("Очный"))
                    .count();
            //Счетчик тех кто хочет дист
            long distCount = n - ochCount;
            if (distCount > ochCount) {
                map.put(clas, "Дистанционный");
            } else {
                map.put(clas, "Очный");
            }
        });
        return map;
    }
}
