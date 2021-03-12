package thirdLesson.cats;

import java.util.*;

public class CatManagerImpl implements CatManager {
    private Cat[] sourceArray;

    public CatManagerImpl(Cat[] sourceArray) {
        this.sourceArray = sourceArray;
    }

    @Override
    public Map<String, Cat> getNameCatMap() {
        TreeMap<String, Cat> nameCatMap = new TreeMap<>();
        for (Cat cat: sourceArray) {
            String name = cat.getName();
            nameCatMap.put(name, cat);
        }
        return nameCatMap;
    }

    @Override
    public Map<Integer, List<Cat>> getAgeCatMap() {
        TreeMap<Integer, List<Cat>> ageCatMap = new TreeMap<>();
        for (Cat cat: sourceArray) {
            int age = cat.getAge();
            List<Cat> currentCats = ageCatMap.get(age);
            if (currentCats == null) {
                currentCats = new LinkedList<>();
            }
            currentCats.add(cat);
            ageCatMap.put(age, currentCats);
        }
        return ageCatMap;
    }

    @Override
    public Map<Cat, List<Cat>> getChildrenCatMap() {
        HashMap<Cat, List<Cat>> childrenCatMap = new HashMap<>();
        for (Cat cat: sourceArray) {
            Cat mother = cat.getMotherCat();
            Cat father = cat.getFatherCat();

            List<Cat> motherCats = childrenCatMap.get(mother);
            if (motherCats == null) {
                motherCats = new LinkedList<>();
            }
            motherCats.add(cat);
            childrenCatMap.put(mother, motherCats);

            List<Cat> fatherCats = childrenCatMap.get(father);
            if (fatherCats == null) {
                fatherCats = new LinkedList<>();
            }
            fatherCats.add(cat);
            childrenCatMap.put(father, fatherCats);
        }
        return childrenCatMap;
    }
}
