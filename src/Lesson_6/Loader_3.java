package Lesson_6;

import java.io.IOException;

public class Loader_3 {
    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManagerImpl();
        fileManager.createFileOrFolder("src/Lesson_6");
        fileManager.printFileTree("src", 4);
    }
}
