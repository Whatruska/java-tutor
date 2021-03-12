package thirdLesson.phone;

public class ContactBuilder {
    private Contact buildContact;

    public ContactBuilder() {
        buildContact = new Contact();
    }

    public ContactBuilder name(String name) {
        this.buildContact.setName(name);
        return this;
    }

    public ContactBuilder surname(String surname) {
        this.buildContact.setSurname(surname);
        return this;
    }

    public ContactBuilder organisation(String organisation) {
        this.buildContact.setOrganisation(organisation);
        return this;
    }

    public ContactBuilder phoneType(PhoneType phoneType) {
        this.buildContact.setPhoneType(phoneType);
        return this;
    }

    public Contact build() {
        return buildContact;
    }
}
