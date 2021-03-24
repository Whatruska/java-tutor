package Lesson_6;

import java.io.IOException;

public interface FileManager {
    void createFileOrFolder(String filePath) throws IOException;
    void printFileTree(String filePath, int maxDepth);
}
