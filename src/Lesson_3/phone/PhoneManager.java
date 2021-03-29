package Lesson_3.phone;

import java.util.List;
import java.util.Map;

public interface PhoneManager {
    Map<String, Contact> getNumberContactMap();

    Contact getContactByPhoneNumber(String phoneNumber);

    List<ContactWithNumber> getPossibleContactsByName(String name);

    void addContact(ContactWithNumber contact);
}
