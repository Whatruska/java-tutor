package Lesson_8.randomizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class Randomizer {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(Paths.get("src/Lesson_7/randomizer/dataset.tsv").toUri());
        Scanner scanner = new Scanner(file);
        File report = new File(Paths.get("src/Lesson_7/randomizer/report.tsv").toUri());
        PrintWriter writer = new PrintWriter(report);
        for (int i = 0; i < 10; i++) {
            int M = scanner.nextInt();
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                data.add(scanner.nextInt());
            }
            //ОПЕРАЦИЯ M Time
            TreeSet<Integer> testTreeSet = new TreeSet<>();

            long milis = System.nanoTime();
            for (Integer dataItem: data) {
                testTreeSet.add(dataItem);
            }
            long milis2 = System.nanoTime();

            long time = milis2 - milis;
            writer.println("Добавление элемента" + "\t" + M + "\t" + time);
        }
        writer.flush();
        writer.close();
    }

    public static void generateDataset () throws FileNotFoundException {
        Random random = new Random();
        int N = random.nextInt(10000);
        File file = new File(Paths.get("src/Lesson_7/randomizer/dataset.tsv").toUri());
        PrintWriter writer = new PrintWriter(file);
        for (int i = 0; i < N; i++) {
            int M = random.nextInt(1000);
            while (M < 100) {
                M = random.nextInt(1000);
            }
            writer.print(M + "\t");
            for (int j = 0; j < M; j++) {
                writer.print(random.nextInt(100000) + "\t");
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }
}
