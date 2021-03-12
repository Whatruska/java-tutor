package thirdLesson.phone;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneManagerImpl implements PhoneManager {
    private Map<String, Contact> contactMap;

    public PhoneManagerImpl() {
        contactMap = ContactsFileManager.loadMapFromFile();
    }

    @Override
    public Map<String, Contact> getNumberContactMap() {
        return contactMap;
    }

    @Override
    public Contact getContactByPhoneNumber(String phoneNumber) {
        return contactMap.get(phoneNumber);
    }

    @Override
    public List<ContactWithNumber> getPossibleContactsByName(String name) {
        List<ContactWithNumber> contacts = new LinkedList<>();
        for (String phoneNumber : contactMap.keySet()) {
            Contact contact = contactMap.get(phoneNumber);
            if (contact.getName().equals(name)) {
                contacts.add(
                        new ContactWithNumber(
                                phoneNumber, contact
                        )
                );
            }
        }

        return contacts;
    }

    @Override
    public void addContact(ContactWithNumber contact) {
        contactMap.put(contact.getPhoneNumber(), contact.getContactInfo());
        try {
            ContactsFileManager.saveData(contactMap);
        } catch (Exception e) {

        }
    }
}
