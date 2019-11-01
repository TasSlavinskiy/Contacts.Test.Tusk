package com.testTask.contacts.gui;

import com.testTask.contacts.storage.Contact;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ContactDialog  extends JDialog implements ActionListener {

    private static final String SAVE = "SAVE",
                                CANCEL = "CANCEL";

    private static final int PADDING = 10,
                             WIDTH_POINT = 100,
                             WIDTH_INPUT_FIELD = 300,
                             WIDTH_BUTTON = 120,
                             HEIGHT_ALL_ELEMENTS = 25;

    private static final DateFormat date = new SimpleDateFormat("yyyy-mm-dd");


    private final JTextPane textFirstName = new JTextPane(),
                            textLastName = new JTextPane(),
                            textPhoneNumber = new JTextPane();
    private JFormattedTextField  textDateOfBirth = new JFormattedTextField(date);;

    private Long contactId;
    private boolean save = false;


    public ContactDialog() {
        this(null);
    }

    public ContactDialog(Contact contact) {

        setLayout(null);

        buildPointsAndFieldsOnForm();

        initFields(contact);

        buildButtonsAtForm();

        setModal(true);

        setResizable(false);

        setBounds(300, 300, 450, 200);

        setVisible(true);
    }

    private void buildPointsAndFieldsOnForm(){

        JLabel labelFirstName = new JLabel("First Name : ");
        labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelFirstName.setBounds(new Rectangle(PADDING, 0 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_POINT, HEIGHT_ALL_ELEMENTS));
        add(labelFirstName);
        textFirstName.setBounds(new Rectangle(WIDTH_POINT + 2 * PADDING, 0 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_INPUT_FIELD, HEIGHT_ALL_ELEMENTS));
        textFirstName.setBorder(BorderFactory.createEtchedBorder());
        add(textFirstName);


        JLabel labelLastName = new JLabel("Last Name : ");
        labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelLastName.setBounds(new Rectangle(PADDING, 1 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_POINT, HEIGHT_ALL_ELEMENTS));
        add(labelLastName);
        textLastName.setBounds(new Rectangle(WIDTH_POINT + 2 * PADDING, 1 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_INPUT_FIELD, HEIGHT_ALL_ELEMENTS));
        textLastName.setBorder(BorderFactory.createEtchedBorder());
        add(textLastName);


        JLabel labelPhoneNumber = new JLabel("Phone : ");
        labelPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPhoneNumber.setBounds(new Rectangle(PADDING, 2 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_POINT, HEIGHT_ALL_ELEMENTS));
        add(labelPhoneNumber);
        textPhoneNumber.setBounds(new Rectangle(WIDTH_POINT + 2 * PADDING, 2 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_INPUT_FIELD, HEIGHT_ALL_ELEMENTS));
        textPhoneNumber.setBorder(BorderFactory.createEtchedBorder());
        add(textPhoneNumber);


        JLabel labelDateOfBirth = new JLabel("Date of Birth : ");
        labelDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
        labelDateOfBirth.setBounds(new Rectangle(PADDING, 3 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_POINT, HEIGHT_ALL_ELEMENTS));
        add(labelDateOfBirth);
        MaskFormatter formatter;
        try {
            formatter = new MaskFormatter("####-##-##");
            formatter.setPlaceholder("YYYY-MM-DD");
            formatter.setValidCharacters("0123456789");
            formatter.install(textDateOfBirth);
        } catch (ParseException exc) {
            exc.printStackTrace();
        }
        textDateOfBirth.setBounds(new Rectangle(WIDTH_POINT + 2 * PADDING, 3 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_INPUT_FIELD, HEIGHT_ALL_ELEMENTS));
        textDateOfBirth.setBorder(BorderFactory.createEtchedBorder());

        add(textDateOfBirth);
    }

    private void initFields(Contact contact){
        if (contact != null) {
            contactId = contact.getContactId();
            textFirstName.setText(contact.getFirstName());
            textLastName.setText(contact.getLastName());
            textPhoneNumber.setText(contact.getPhoneNumber());
            textDateOfBirth.setText(contact.getDateOfBirth().toString());
        }
    }

    private void buildButtonsAtForm(){

        JButton buttonSave = new JButton("SAVE");
        buttonSave.setActionCommand(SAVE);
        buttonSave.addActionListener(this);
        buttonSave.setBounds(new Rectangle(PADDING, 5 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_BUTTON, HEIGHT_ALL_ELEMENTS));
        add(buttonSave);

        JButton buttonCancel = new JButton("CANCEL");
        buttonCancel.setActionCommand(CANCEL);
        buttonCancel.addActionListener(this);
        buttonCancel.setBounds(new Rectangle(WIDTH_BUTTON + 2 * PADDING, 5 * HEIGHT_ALL_ELEMENTS + PADDING, WIDTH_BUTTON, HEIGHT_ALL_ELEMENTS));
        add(buttonCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }

    public boolean isSave(){
        return save;
    }

    public Contact getContact(){
        Contact contact = new Contact(textFirstName.getText(), textLastName.getText(), textPhoneNumber.getText(), textDateOfBirth.getText());
        return contact;
    }
}
