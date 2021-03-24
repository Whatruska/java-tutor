package Lesson_3.phone;

public class Contact {
    private String name;
    private String surname;
    private String organisation;
    private PhoneType phoneType;

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", organisation='" + organisation + '\'' +
                ", phoneType=" + phoneType +
                '}';
    }
}
