package com.testTask.contacts.gui;

import com.testTask.contacts.manager.ContactManager;
import com.testTask.contacts.storage.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactFrame extends JFrame implements ActionListener {

    private static final String LOAD = "LOAD",
                                ADD = "ADD",
                                DELETED = "DELETED";

    private final ContactManager contactManager = new ContactManager();
    private final JTable jContactTable = new JTable();

    public ContactFrame() {

        jContactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 5, 1, 5);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(gridBagLayout);

        buttonPanel.add(createButton(gridBagLayout, gridBagConstraints, "Обновить", LOAD));
        buttonPanel.add(createButton(gridBagLayout, gridBagConstraints, "Добавить", ADD));
        buttonPanel.add(createButton(gridBagLayout, gridBagConstraints, "Удалить", DELETED));

        JPanel leftPanel = new JPanel();

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        add(new JScrollPane(jContactTable), BorderLayout.CENTER);

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadContact();
    }

    private JButton createButton(GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints, String title, String action){

        JButton button = new JButton(title);

        button.setActionCommand(action);
        button.addActionListener(this);
        gridBagLayout.setConstraints(button, gridBagConstraints);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case LOAD:
                loadContact();
                break;
            case ADD:
                addContact();
                break;
            case DELETED:
                deleteContact();
                break;
        }
    }

    private void loadContact() {
        List<Contact> contacts = contactManager.findContacts();

        ContactModel contactModel = new ContactModel(contacts);

        jContactTable.setModel(contactModel);
    }

    private void addContact(){
        ContactDialog contactDialog = new ContactDialog();
        saveContact(contactDialog);
    }

    private void deleteContact(){
        int highlightedLine = jContactTable.getSelectedRow();
        if (highlightedLine != 1){
            Long id = Long.parseLong(jContactTable.getModel().getValueAt(highlightedLine,0).toString());
            String numberDeletedUser = contactManager.getContactUseId(id).getPhoneNumber();
            contactManager.deletedContact(numberDeletedUser);
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "Выделите строку удаляемого контакта");
        }
    }

    private void saveContact(ContactDialog contactDialog) {
        if (contactDialog.isSave()) {
            Contact contact = contactDialog.getContact();
            if (contact.getContactId() == null){
                contactManager.addContact(contact);
            } else {
                JOptionPane.showMessageDialog(this, "Данный контакт уже зарегитрирован");
            }
            loadContact();
        }
    }
}
