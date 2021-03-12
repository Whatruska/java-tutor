package thirdLesson.phone;

import java.io.*;
import java.nio.file.Paths;

public class Loader {
    public static void main(String[] args) throws IOException {
        File file = new File(Paths.get("src/thirdLesson/phone/info.txt").toUri());
    }
}
