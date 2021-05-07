package Lesson_11;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class DocumentDownloader {
    private static final int LIMIT = 8000;
    public static final String FOLDER_PATH = "src/Lesson_11/files";

    public static void main(String[] args) throws MalformedURLException {
        String template = "https://www.rfc-editor.org/rfc/rfc%s.txt";
        makeFileFolder();
        ArrayList<String> sitePull = new ArrayList<>();
        String threadName = "";
        for (int i = 1; i <= LIMIT; i++) {
            String site = template.replace("%s", "" + i);
            sitePull.add(site);
            threadName += i + " ";
            if (i % 5 == 0 || i == LIMIT - 1) {
                Thread thread = new Thread(new Downloader(sitePull.subList(0, sitePull.size())));
                thread.start();
                thread.setName(threadName);

                sitePull = new ArrayList<>();
                threadName = "";
            }
        }
    }

    public static void makeFileFolder () {
        File file = new File("src/Lesson_11/files");
        if (file.exists()) {
            file.delete();
        }
        file.mkdir();
    }
}
