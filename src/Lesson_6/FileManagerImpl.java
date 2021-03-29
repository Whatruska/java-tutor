package Lesson_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManagerImpl implements FileManager {
    @Override
    public void createFileOrFolder(String filePath) throws IOException {
        File file = new File(Paths.get(filePath).toUri());
        if (file.exists()) {
            //isFile - возвращает true, если файл является файлом
            //isDirectory - возвращает true, если файл является папкой
            if (file.isFile()) {
                makeNewDirectory(file);
            } else if (file.isDirectory()) {
                makeNewTextFile(file);
            }
        }
    }

    //info.module.css   /Users/java-tutor/info.module.css -> /Users/java-tutor/info
    public void makeNewDirectory(File file) {
        String parent = file.getParent();
        String name = file.getName();
        String[] parts = name.split("\\.");
        String newFolderName = parts[0];
        String newFolderPath = parent + "/" + newFolderName;
        File newFolder = new File(newFolderPath);
        newFolder.mkdir();
    }

    public void makeNewTextFile(File directory) throws IOException {
        String path = directory.getPath();
        String newTextFilePath = path + ".txt";
        File file = new File(newTextFilePath);
        file.createNewFile();
    }

    @Override
    public void printFileTree(String filePath, int maxDepth) {
        File file = new File(Paths.get(filePath).toUri());
        recursivePrintFileTree(file, 0, maxDepth);
    }

    /*
        folder_1
            file.txt
            dota.exe
        folder_2
            img.png
     */
    private void recursivePrintFileTree(File file, int currDepth, int maxDepth) {
        if (currDepth < maxDepth) {
            String tabs = formTabs(currDepth);
            String name = file.getName();
            System.out.println(tabs + name);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File innerFile : files) {
                    recursivePrintFileTree(innerFile, currDepth + 1, maxDepth);
                }
            }
        }
    }

    public String formTabs(int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "\t";
        }
        return str;
    }
}
