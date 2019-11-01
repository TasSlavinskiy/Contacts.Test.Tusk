package com.testTask.contacts.manager;


import com.testTask.contacts.dao.ExecutionContact;
import com.testTask.contacts.dao.ContactFactory;
import com.testTask.contacts.storage.Contact;

import java.util.List;

public class ContactManager {

    private ExecutionContact executionContact;

    public ContactManager() {
        executionContact = ContactFactory.getContactDao();
    }

    public Long addContact(Contact contact){
        return executionContact.addContact(contact);
    }

    public Contact getContact(String phoneNumberUser){
        return executionContact.getContact(phoneNumberUser);
    }

    public Contact getContactUseId(Long userId){
        return executionContact.getContactUseId(userId);
    }

    public void deletedContact(String phoneNumberUser){
        executionContact.deleteContact(phoneNumberUser);
    }

    public String checkContact(String phoneNumberUser){return executionContact.checkContact(phoneNumberUser);}

    public List<Contact> findContacts(){
        return executionContact.findContacts();
    }
}
