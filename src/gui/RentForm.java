package gui;

import dao.CarDAO;
import dao.ClientDAO;
import dao.RentDAO;
import model.Car;
import model.Client;
import model.Rent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class RentForm extends JFrame {

    private JComboBox<Client> clientCombo;
    private JComboBox<Car> carCombo;
    private JTextField daysField;
    private JTextField dateField;
    private JComboBox<Rent> rentCombo;

    private RentDAO rentDAO = new RentDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private CarDAO carDAO = new CarDAO();

    public RentForm() {
        setTitle("Διαχείριση Ενοικιάσεων");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 1, 5, 5));


        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Πελάτης:"));
        clientCombo = new JComboBox<>();
        p1.add(clientCombo);
        add(p1);


        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Αυτοκίνητο:"));
        carCombo = new JComboBox<>();
        p2.add(carCombo);
        add(p2);


        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Μέρες:"));
        daysField = new JTextField(10);
        p3.add(daysField);
        add(p3);


        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p4.add(new JLabel("Ημ/νια (YYYY-MM-DD):"));
        dateField = new JTextField(LocalDate.now().toString(), 10);
        p4.add(dateField);
        add(p4);


        JButton addBtn = new JButton("Νέα Καταχώρηση");
        addBtn.addActionListener(e -> addRent());
        add(addBtn);


        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p5.add(new JLabel("Επιλογή υπάρχουσας:"));
        rentCombo = new JComboBox<>();
        p5.add(rentCombo);
        add(p5);


        JButton editBtn = new JButton("Επεξεργασία Επιλεγμένης");
        editBtn.addActionListener(e -> editRent());
        add(editBtn);


        JButton deleteBtn = new JButton("Διαγραφή Επιλεγμένης");
        deleteBtn.addActionListener(e -> deleteRent());
        add(deleteBtn);


        JButton backBtn = new JButton("← Πίσω στο Μενού");
        backBtn.addActionListener(e -> dispose());
        add(backBtn);


        rentCombo.addActionListener(e -> {
            Rent selected = (Rent) rentCombo.getSelectedItem();
            if (selected != null) {
                clientCombo.setSelectedItem(selected.getClient());
                carCombo.setSelectedItem(selected.getCar());
                daysField.setText(String.valueOf(selected.getDays()));
                dateField.setText(selected.getDateAt().toString());
            }
        });

        loadClients();
        loadCars();
        loadRents();
    }

    private void loadClients() {
        clientCombo.removeAllItems();
        for (Client c : clientDAO.findAll()) clientCombo.addItem(c);
    }

    private void loadCars() {
        carCombo.removeAllItems();
        for (Car c : carDAO.findAll()) carCombo.addItem(c);
    }

    private void loadRents() {
        rentCombo.removeAllItems();
        for (Rent r : rentDAO.findAll()) rentCombo.addItem(r);
    }

    private void addRent() {
        try {
            Client client = (Client) clientCombo.getSelectedItem();
            Car car = (Car) carCombo.getSelectedItem();
            int days = Integer.parseInt(daysField.getText().trim());
            LocalDate date = LocalDate.parse(dateField.getText().trim());

            if (client == null || car == null) {
                JOptionPane.showMessageDialog(this, "Επιλεξε πελατη και αυτοκινητο!");
                return;
            }

            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Οι μερες πρεπει να ειναι > 0!");
                return;
            }

            Rent rent = new Rent(days, date, client, car);
            rentDAO.insert(rent);
            JOptionPane.showMessageDialog(this, "Καταχωρηθηκε!");

            daysField.setText("");
            loadRents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Οι μερες πρεπει να ειναι αριθμος!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }

    private void editRent() {
        Rent selected = (Rent) rentCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε ενοικιαση!");
            return;
        }

        try {
            Client client = (Client) clientCombo.getSelectedItem();
            Car car = (Car) carCombo.getSelectedItem();
            int days = Integer.parseInt(daysField.getText().trim());
            LocalDate date = LocalDate.parse(dateField.getText().trim());

            selected.setClient(client);
            selected.setCar(car);
            selected.setDays(days);
            selected.setDateAt(date);

            rentDAO.update(selected);
            JOptionPane.showMessageDialog(this, "Ενημερωθηκε!");
            loadRents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Λαθος αριθμητικη τιμη!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }

    private void deleteRent() {
        Rent selected = (Rent) rentCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε ενοικιαση!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Σιγουρα να διαγραφει η ενοικιαση;",
                "Επιβεβαιωση", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                rentDAO.delete(selected.getId());
                JOptionPane.showMessageDialog(this, "Διαγραφηκε!");
                loadRents();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
            }
        }
    }
}