package com.testTask.contacts.dao;

import com.testTask.contacts.storage.Contact;

import java.util.List;

public interface ExecutionContact {

    Long addContact(Contact contact);

    Contact getContact(String phoneNumber);

    Contact getContactUseId(Long someId);

    void deleteContact(String phoneNumber);

    List<Contact> findContacts();
}
