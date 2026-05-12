package gui;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientForm extends JFrame {


    private JTextField nameField, surnameField, addressField, emailField, phoneField;
    private JComboBox<String> genderCombo;
    private JComboBox<Client> clientCombo;

    private ClientDAO clientDAO = new ClientDAO();

    public ClientForm() {
        setTitle("Διαχείριση Πελατών");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 1, 5, 5));


        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Όνομα:"));
        nameField = new JTextField(20);
        p1.add(nameField);
        add(p1);


        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Επώνυμο:"));
        surnameField = new JTextField(20);
        p2.add(surnameField);
        add(p2);


        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Φύλο:"));
        genderCombo = new JComboBox<>(new String[]{"Άρρεν", "Θήλυ", "Άλλο"});
        p3.add(genderCombo);
        add(p3);


        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p4.add(new JLabel("Διεύθυνση:"));
        addressField = new JTextField(20);
        p4.add(addressField);
        add(p4);


        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p5.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        p5.add(emailField);
        add(p5);


        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p6.add(new JLabel("Τηλέφωνο:"));
        phoneField = new JTextField(20);
        p6.add(phoneField);
        add(p6);


        JButton addBtn = new JButton("Προσθήκη Πελάτη");
        addBtn.addActionListener(e -> addClient());
        add(addBtn);


        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p7.add(new JLabel("Επιλογή:"));
        clientCombo = new JComboBox<>();
        p7.add(clientCombo);
        add(p7);

        clientCombo.addActionListener(e -> {
            Client selected = (Client) clientCombo.getSelectedItem();
            if (selected != null) {
                nameField.setText(selected.getName());
                surnameField.setText(selected.getSurname());
                genderCombo.setSelectedItem(selected.getGender());
                addressField.setText(selected.getAddress());
                emailField.setText(selected.getEmail());
                phoneField.setText(selected.getPhone());
            }
        });

        JButton editBtn = new JButton("Επεξεργασία Επιλεγμένου");
        editBtn.addActionListener(e -> editClient());
        add(editBtn);


        JButton deleteBtn = new JButton("Διαγραφή Επιλεγμένου");
        deleteBtn.addActionListener(e -> deleteClient());
        add(deleteBtn);


        JButton backBtn = new JButton("← Πίσω στο Μενού");
        backBtn.addActionListener(e -> dispose());
        add(backBtn);

        loadClients();
    }

    private void addClient() {

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String gender = (String) genderCombo.getSelectedItem();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();


        if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπληρωσε ονομα, επωνυμο και email!");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Μη εγκυρο email!");
            return;
        }


        try {
            Client client = new Client(name, surname, gender, address, email, phone);
            clientDAO.insert(client);
            JOptionPane.showMessageDialog(this, "Προστεθηκε: " + name + " " + surname);


            nameField.setText("");
            surnameField.setText("");
            addressField.setText("");
            emailField.setText("");
            phoneField.setText("");

            loadClients();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }



    private void editClient() {
        Client selected = (Client) clientCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε πελατη!");
            return;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπληρωσε ονομα, επωνυμο και email!");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Μη εγκυρο email!");
            return;
        }

        try {
            selected.setName(name);
            selected.setSurname(surname);
            selected.setGender((String) genderCombo.getSelectedItem());
            selected.setAddress(addressField.getText().trim());
            selected.setEmail(email);
            selected.setPhone(phoneField.getText().trim());

            clientDAO.update(selected);
            JOptionPane.showMessageDialog(this, "Ενημερωθηκε!");
            loadClients();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }
    private void loadClients() {
        clientCombo.removeAllItems();
        List<Client> clients = clientDAO.findAll();
        for (Client c : clients) {
            clientCombo.addItem(c);
        }
    }

    private void deleteClient() {
        Client selected = (Client) clientCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε πελατη!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Σιγουρα να διαγραφει ο " + selected.getName() + " " + selected.getSurname() + ";",
                "Επιβεβαιωση", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                clientDAO.delete(selected.getId());
                JOptionPane.showMessageDialog(this, "Διαγραφηκε!");
                loadClients();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
            }
        }
    }
}