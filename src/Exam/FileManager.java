package Exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {
    private final static String pathToAssetsFolder = "src/Exam/assets";

    private HashMap<String, String> getProps (List<String> fieldNames, String line) {
        HashMap<String, String> props = new HashMap<>();
        String[] parts = line.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            props.put(fieldNames.get(i), parts[i]);
        }
        return props;
    }

    private <T> T deserialize (HashMap<String, String> props, Class<T> c) {
        try {
            Object obj = c.newInstance();
            Field[] fields = c.getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String value = props.get(fieldName);
                field.set(obj, value);
                field.setAccessible(false);
            }
            return (T) obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getEntitiesFromFile (String filename, Class<T> tClass) {
        List<Object> objects = new ArrayList<>();

        File file = new File(Paths.get(pathToAssetsFolder, filename).toUri());
        try {
            Scanner scanner = new Scanner(file);
            List<String> fieldNames = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("@")) {
                    line = line.replace("@", "");
                    fieldNames = Arrays.asList(line.split("\\s+"));
                } else {
                    HashMap<String, String> props = getProps(fieldNames, line);
                    objects.add(deserialize(props, tClass));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (List<T>) objects;
    }
}
