package Lesson_3.cats;

import java.util.List;
import java.util.Map;

public class Loader {
    public static void main(String[] args) {
        Cat sbercat = new CatBuilder()
                .name("Сберкот")
                .age(4)
                .fatherCat(null)
                .motherCat(null)
                .color("Рыжий")
                .build();
        Cat roketbankCat = new CatBuilder()
                .name("РокетКот")
                .age(5)
                .fatherCat(null)
                .motherCat(null)
                .color("Черный")
                .build();
        Cat vaska = new CatBuilder()
                .name("Василиса")
                .age(2)
                .fatherCat(sbercat)
                .motherCat(roketbankCat)
                .color("Серый")
                .build();
        Cat petka = new CatBuilder()
                .name("Петька")
                .age(1)
                .fatherCat(sbercat)
                .motherCat(roketbankCat)
                .color("Коричневый")
                .build();
        Cat sashka = new CatBuilder()
                .name("Сашка")
                .age(1)
                .fatherCat(sbercat)
                .motherCat(roketbankCat)
                .color("Белый")
                .build();
        Cat barsik = new CatBuilder()
                .name("Барсик")
                .age(1)
                .fatherCat(sbercat)
                .motherCat(null)
                .color("Белый")
                .build();

        Cat[] catArray = {sbercat, roketbankCat, vaska, petka, sashka, barsik};
        CatManager catManager = new CatManagerImpl(catArray);

        System.out.println("Первая мапа");
        Map<String, Cat> firstMap = catManager.getNameCatMap();
        for (String name : firstMap.keySet()) {
            Cat cat = firstMap.get(name);
            System.out.println(name + " -> {" + cat.getName() + " , " + cat.getAge() + "}");
        }
        System.out.println("");

        System.out.println("Вторая мапа");
        Map<Integer, List<Cat>> secondMap = catManager.getAgeCatMap();
        for (Integer age : secondMap.keySet()) {
            List<Cat> cats = secondMap.get(age);
            System.out.println("Возраст: " + age);
            for (Cat cat : cats) {
                System.out.println(cat.getName() + " - " + cat.getColor());
            }
        }
        System.out.println("");

        System.out.println("Третья мапа");
        Map<Cat, List<Cat>> childrenMap = catManager.getChildrenCatMap();
        for (Cat cat : childrenMap.keySet()) {
            if (cat != null) {
                List<Cat> childrenList = childrenMap.get(cat);
                System.out.println("Список детей " + cat.getName() + ":");
                for (Cat childCat : childrenList) {
                    System.out.println(childCat.getName() + " - " + childCat.getColor());
                }
            }
            System.out.println("");
        }
    }
}
