package com.testTask.contacts.storage;

import java.io.Serializable;
import java.time.LocalDate;

public final class Contact implements Serializable{

    private Long contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Contact(String firstName, String lastName, String phoneNumber, String dataOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = convertData(dataOfBirth);
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }



    @Override
    public String toString() {
        return firstName + ' ' + lastName + ' ' + phoneNumber + ' ' + dateOfBirth ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!getFirstName().equals(contact.getFirstName())) return false;
        return getLastName().equals(contact.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }

    private LocalDate convertData(String dateOfBirth){
        LocalDate convertedDateOfBirth = LocalDate.parse(dateOfBirth);
        return convertedDateOfBirth;
    }
}
