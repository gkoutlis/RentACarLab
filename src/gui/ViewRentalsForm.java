package gui;

import dao.CarDAO;
import dao.ClientDAO;
import dao.RentDAO;
import model.Car;
import model.Client;
import model.Rent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewRentalsForm extends JFrame {

    private JTextArea allRentalsArea;
    private JComboBox<Client> clientSearchCombo;
    private JTextArea clientRentalsArea;
    private JComboBox<Car> carSearchCombo;
    private JTextArea carRentalsArea;

    private RentDAO rentDAO = new RentDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private CarDAO carDAO = new CarDAO();

    public ViewRentalsForm() {
        setTitle("Προβολή Ενοικιάσεων");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Όλες οι Ενοικιάσεις", createAllRentalsPanel());
        tabs.add("Αναζήτηση Πελάτη", createClientSearchPanel());
        tabs.add("Αναζήτηση Αυτοκινήτου", createCarSearchPanel());

        add(tabs, BorderLayout.CENTER);

        JButton backBtn = new JButton("← Πίσω στο Μενού");
        backBtn.addActionListener(e -> dispose());
        add(backBtn, BorderLayout.SOUTH);
    }


    private JPanel createAllRentalsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        allRentalsArea = new JTextArea();
        allRentalsArea.setEditable(false);
        panel.add(new JScrollPane(allRentalsArea), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Φόρτωση");
        refreshBtn.addActionListener(e -> {
            allRentalsArea.setText("");
            List<Rent> rents = rentDAO.findAll();
            for (Rent r : rents) {
                allRentalsArea.append(formatRent(r) + "\n\n");  // ← νέα μέθοδος
            }
        });
        panel.add(refreshBtn, BorderLayout.SOUTH);

        return panel;
    }


    private JPanel createClientSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());


        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Πελάτης:"));
        clientSearchCombo = new JComboBox<>();
        for (Client c : clientDAO.findAll()) clientSearchCombo.addItem(c);
        top.add(clientSearchCombo);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(e -> searchByClient());
        top.add(searchBtn);
        panel.add(top, BorderLayout.NORTH);


        clientRentalsArea = new JTextArea();
        clientRentalsArea.setEditable(false);
        panel.add(new JScrollPane(clientRentalsArea), BorderLayout.CENTER);

        return panel;
    }

    private void searchByClient() {
        Client selected = (Client) clientSearchCombo.getSelectedItem();
        if (selected == null) return;

        clientRentalsArea.setText("Ενοικιάσεις του " + selected.getName() + " " + selected.getSurname() + ":\n\n");

        List<Rent> all = rentDAO.findAll();
        boolean found = false;
        for (Rent r : all) {
            if (r.getClient().getId() == selected.getId()) {
                clientRentalsArea.append(formatRent(r) + "\n\n");  // ← νέα μέθοδος
                found = true;
            }
        }
        if (!found) clientRentalsArea.append("Δεν βρεθηκαν ενοικιασεις.");
    }


    private JPanel createCarSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Αυτοκίνητο:"));
        carSearchCombo = new JComboBox<>();
        for (Car c : carDAO.findAll()) carSearchCombo.addItem(c);
        top.add(carSearchCombo);

        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.addActionListener(e -> searchByCar());
        top.add(searchBtn);
        panel.add(top, BorderLayout.NORTH);

        carRentalsArea = new JTextArea();
        carRentalsArea.setEditable(false);
        panel.add(new JScrollPane(carRentalsArea), BorderLayout.CENTER);

        return panel;
    }


    private String formatRent(Rent r) {
        return "Ενοικίαση #" + r.getId() + "\n" +
                "  Πελάτης: " + r.getClient().getName() + " " + r.getClient().getSurname() +
                " (Email: " + r.getClient().getEmail() + ")\n" +
                "  Αυτοκίνητο: " + r.getCar().getModel() +
                " (Κατηγορία: " + r.getCar().getCategory().getKind() + ")\n" +
                "  Διάρκεια: " + r.getDays() + " μέρες\n" +
                "  Ημερομηνία: " + r.getDateAt() + "\n" +
                "  Κόστος: " + r.getCar().getCostPerDay().multiply(
                new java.math.BigDecimal(r.getDays())) + "€";
    }

    private void searchByCar() {
        Car selected = (Car) carSearchCombo.getSelectedItem();
        if (selected == null) return;

        carRentalsArea.setText("Ενοικιάσεις του " + selected.getModel() + ":\n\n");

        List<Rent> all = rentDAO.findAll();
        boolean found = false;
        for (Rent r : all) {
            if (r.getCar().getId() == selected.getId()) {
                carRentalsArea.append(formatRent(r) + "\n\n");
                found = true;
            }
        }
        if (!found) carRentalsArea.append("Δεν βρεθηκαν ενοικιασεις.");
    }
}