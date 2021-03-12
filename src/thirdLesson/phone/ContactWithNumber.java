package thirdLesson.phone;

public class ContactWithNumber {
    private String phoneNumber;
    private Contact contactInfo;

    public ContactWithNumber(String phoneNumber, Contact contactInfo) {
        this.phoneNumber = phoneNumber;
        this.contactInfo = contactInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Contact contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "ContactWithNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
