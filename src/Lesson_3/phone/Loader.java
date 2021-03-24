package Lesson_3.phone;

import java.io.*;

public class Loader {
    public static void main(String[] args) throws IOException {
        PhoneManager manager = new PhoneManagerImpl();
        System.out.println(manager.getContactByPhoneNumber("+79136560423"));
        System.out.println(manager.getPossibleContactsByName("Артем"));
    }
}
