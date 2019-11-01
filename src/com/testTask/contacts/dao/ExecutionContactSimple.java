package com.testTask.contacts.dao;

import com.testTask.contacts.storage.Contact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ExecutionContactSimple implements ExecutionContact {

    private final List<Contact> contacts = new ArrayList<>();

    public ExecutionContactSimple() {}

    @Override
    public Long addContact(Contact contact) {
        Long id = assignContactId();
        contact.setContactId(id);
        contacts.add(contact);
        return id;
    }

    @Override
    public Contact getContact(String phoneNumber) {
        for (Contact contact : contacts){
           if ( contact.getPhoneNumber() == phoneNumber){
               return contact;
           }
        }
        return null;
    }

    @Override
    public Contact getContactUseId(Long someId) {
        for (Contact contact : contacts){
            if (contact.getContactId().equals(someId)){
                return contact;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(String phoneNumber) {
        for (Iterator<Contact> iteratorContacts = contacts.iterator(); iteratorContacts.hasNext();){
            Contact contact = iteratorContacts.next();
            if (contact.getPhoneNumber() == phoneNumber){
                iteratorContacts.remove();
            }
        }
    }

    @Override
    public List<Contact> findContacts() {
        return contacts;
    }

    private Long assignContactId(){
        Long finalContactId = generateContactId();
        while (getContactUseId(finalContactId) != null){
            finalContactId = generateContactId();
        }
        return finalContactId;
    }

    @Override
    public String checkContact(String phoneNumber) {
        for (Iterator<Contact> iteratorContacts = contacts.iterator(); iteratorContacts.hasNext();){
            Contact contact = iteratorContacts.next();
            if (contact.getPhoneNumber() == phoneNumber){
                return contact.getPhoneNumber();
            }
        }
        return null;
    }

    private Long generateContactId(){
        UUID contactIdUUID = UUID.randomUUID();
        Long temporaryContactId = contactIdUUID.getLeastSignificantBits();
        return temporaryContactId;
    }
}
