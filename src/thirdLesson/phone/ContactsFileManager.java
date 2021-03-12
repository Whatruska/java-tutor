package thirdLesson.phone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class ContactsFileManager {
    private static final String FILE_PATH = "src/thirdLesson/phone/info.txt";

    private static Set<String> readAllInfoFromFile() {
        File file = new File(Paths.get(FILE_PATH).toUri());
        Set<String> allStrings = new TreeSet<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                allStrings.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allStrings;
    }

    public static Map<String, Contact> loadMapFromFile() {
        TreeMap<String, Contact> map = new TreeMap<>();

        Set<String> lines = readAllInfoFromFile();
        for (String line : lines) {
            String phoneNumber = line.split(";")[0];
            Contact contact = deserializeContact(line);
            map.put(phoneNumber, contact);
        }

        return map;
    }

    public static void saveData(Map<String, Contact> map) throws FileNotFoundException {
        File file = new File(Paths.get(FILE_PATH).toUri());
        Set<String> lines = readAllInfoFromFile();

        for (String number : map.keySet()) {
            Contact contact = map.get(number);
            String newLine = number + ";" + serialize(contact);
            lines.add(newLine);
        }

        PrintWriter writer = new PrintWriter(file);
        for (String line : lines) {
            writer.println(line);
        }
        writer.flush();
        writer.close();
    }

    //Десериализация - процесс парсинга объекта из строки
    private static Contact deserializeContact(String line) {
        String[] lineParts = line.split(";");
        PhoneType phoneType = Integer.parseInt(lineParts[4]) == 0 ? PhoneType.MOBILE : PhoneType.HOME;
        Contact contact = new ContactBuilder()
                .name(lineParts[1])
                .surname(lineParts[2])
                .organisation(lineParts[3])
                .phoneType(phoneType)
                .build();
        return contact;
    }

    //Сериализация - процесс приведения объекта к строке
    public static String serialize(Contact contact) {
        return contact.getName() + ";" +
                contact.getSurname() + ";" +
                contact.getOrganisation() + ";" +
                (contact.getPhoneType() == PhoneType.MOBILE ? 0 : 1);
    }
}
