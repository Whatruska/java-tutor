package Lesson_11;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Downloader implements Runnable {
    private List<String> sitePull;

    public Downloader(List<String> sitePull) {
        this.sitePull = sitePull;
    }

    @Override
    public void run() {
        sitePull.stream().forEach(site -> {
            URL url = null;
            try {
                url = new URL(site);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Files.copy(url.openStream(), Paths.get(DocumentDownloader.FOLDER_PATH, getNameByUrl(site)));
                System.out.println(site + " " + "downloaded");
            } catch (IOException e) {}
        });
    }

    private static String getNameByUrl (String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}
