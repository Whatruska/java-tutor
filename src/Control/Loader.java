package Control;

import Control.classes.Cat;

public class Loader {
    public static void main(String[] args) {
        RepositoryFileHelper fileHelper = new FileHelper();
        Repository repository = new RepositoryImpl(fileHelper);

//        Circle circle = new Circle();
//
//        circle.setX(1.5);
//        circle.setY(4.5);
//        circle.setColor("Green");
//
//        repository.set(
//            "Custom",
//            circle
//        );

//        Circle circle = (Circle) repository.get("Custom");
//
//        circle.setRadius(115.0);
//
//        repository.set("Custom", circle);

//        repository.delete("Custom");

//        Cat cat = new Cat();
//
//        cat.setAge(14);
//        cat.setName("Васька");
//
//        repository.set("Васян", cat);

        repository.save();
    }
}
