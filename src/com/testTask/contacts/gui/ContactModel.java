package com.testTask.contacts.gui;

import com.testTask.contacts.storage.Contact;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactModel extends AbstractTableModel {

    private static final String[] headers = {"User ID ", "First Name ", "Last Name ", "Phone ", "Date Of Birth "};
    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return contact.getContactId().toString();
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getPhoneNumber();
            default:
                return contact.getDateOfBirth().toString();
        }
    }
}
