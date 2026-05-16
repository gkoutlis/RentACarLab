package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Γραφείο Ενοικίασης Αυτοκινήτων");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton categoryBtn = new JButton("Κατηγορίες");
        categoryBtn.addActionListener(e -> new CategoryForm().setVisible(true));
        add(categoryBtn);

        JButton clientBtn = new JButton("Πελάτες");
        clientBtn.addActionListener(e -> new ClientForm().setVisible(true));
        add(clientBtn);

        JButton carBtn = new JButton("Αυτοκίνητα");
        carBtn.addActionListener(e -> new CarForm().setVisible(true));
        add(carBtn);

        JButton rentBtn = new JButton("Νέα Ενοικίαση");
        rentBtn.addActionListener(e -> new RentForm().setVisible(true));
        add(rentBtn);

        JButton viewBtn = new JButton("Προβολή Ενοικιάσεων");
        viewBtn.addActionListener(e -> new ViewRentalsForm().setVisible(true));
        add(viewBtn);

        JButton exitBtn = new JButton("Έξοδος");
        exitBtn.addActionListener(e -> System.exit(0));
        add(exitBtn);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}