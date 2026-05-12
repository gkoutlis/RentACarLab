package gui;

import dao.CarDAO;
import dao.CategoryDAO;
import model.Car;
import model.Category;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class CarForm extends JFrame {

    private JTextField modelField, costField, powerField, seatsField;
    private JComboBox<Category> categoryCombo;
    private JComboBox<Car> carCombo;

    private CarDAO carDAO = new CarDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public CarForm() {
        setTitle("Διαχείριση Αυτοκινήτων");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 1, 5, 5));


        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Μοντέλο:"));
        modelField = new JTextField(20);
        p1.add(modelField);
        add(p1);


        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Κόστος/μέρα (€):"));
        costField = new JTextField(20);
        p2.add(costField);
        add(p2);


        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Κυβικά:"));
        powerField = new JTextField(20);
        p3.add(powerField);
        add(p3);


        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p4.add(new JLabel("Θέσεις:"));
        seatsField = new JTextField(20);
        p4.add(seatsField);
        add(p4);


        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p5.add(new JLabel("Κατηγορία:"));
        categoryCombo = new JComboBox<>();
        p5.add(categoryCombo);
        add(p5);


        JButton addBtn = new JButton("Προσθήκη Αυτοκινήτου");
        addBtn.addActionListener(e -> addCar());
        add(addBtn);


        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p6.add(new JLabel("Επιλογή:"));
        carCombo = new JComboBox<>();
        p6.add(carCombo);
        add(p6);

        JButton editBtn = new JButton("Επεξεργασία Επιλεγμένου");
        editBtn.addActionListener(e -> editCar());
        add(editBtn);


        JButton deleteBtn = new JButton("Διαγραφή Επιλεγμένου");
        deleteBtn.addActionListener(e -> deleteCar());
        add(deleteBtn);


        JButton backBtn = new JButton("← Πίσω στο Μενού");
        backBtn.addActionListener(e -> dispose());
        add(backBtn);


        carCombo.addActionListener(e -> {
            Car selected = (Car) carCombo.getSelectedItem();
            if (selected != null) {
                modelField.setText(selected.getModel());
                costField.setText(selected.getCostPerDay().toString());
                powerField.setText(String.valueOf(selected.getHorsePower()));
                seatsField.setText(String.valueOf(selected.getSeats()));
                categoryCombo.setSelectedItem(selected.getCategory());
            }
        });

        loadCategories();
        loadCars();
    }

    private void loadCategories() {
        categoryCombo.removeAllItems();
        List<Category> categories = categoryDAO.findAll();
        for (Category c : categories) {
            categoryCombo.addItem(c);
        }
    }

    private void loadCars() {
        carCombo.removeAllItems();
        List<Car> cars = carDAO.findAll();
        for (Car c : cars) {
            carCombo.addItem(c);
        }
    }

    private void addCar() {
        try {
            String model = modelField.getText().trim();
            BigDecimal cost = new BigDecimal(costField.getText().trim());
            int power = Integer.parseInt(powerField.getText().trim());
            int seats = Integer.parseInt(seatsField.getText().trim());
            Category category = (Category) categoryCombo.getSelectedItem();

            if (model.isEmpty() || category == null) {
                JOptionPane.showMessageDialog(this, "Συμπληρωσε ολα τα πεδια!");
                return;
            }

            Car car = new Car(model, cost, power, seats, category);
            carDAO.insert(car);
            JOptionPane.showMessageDialog(this, "Προστεθηκε: " + model);

            modelField.setText("");
            costField.setText("");
            powerField.setText("");
            seatsField.setText("");

            loadCars();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Λαθος αριθμητικη τιμη!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }

    private void editCar() {
        Car selected = (Car) carCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε αυτοκινητο!");
            return;
        }

        try {
            String model = modelField.getText().trim();
            BigDecimal cost = new BigDecimal(costField.getText().trim());
            int power = Integer.parseInt(powerField.getText().trim());
            int seats = Integer.parseInt(seatsField.getText().trim());
            Category category = (Category) categoryCombo.getSelectedItem();

            if (model.isEmpty() || category == null) {
                JOptionPane.showMessageDialog(this, "Συμπληρωσε ολα τα πεδια!");
                return;
            }

            selected.setModel(model);
            selected.setCostPerDay(cost);
            selected.setHorsePower(power);
            selected.setSeats(seats);
            selected.setCategory(category);

            carDAO.update(selected);
            JOptionPane.showMessageDialog(this, "Ενημερωθηκε!");
            loadCars();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Λαθος αριθμητικη τιμη!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }

    private void deleteCar() {
        Car selected = (Car) carCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε αυτοκινητο!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Σιγουρα να διαγραφει το " + selected.getModel() + ";",
                "Επιβεβαιωση", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                carDAO.delete(selected.getId());
                JOptionPane.showMessageDialog(this, "Διαγραφηκε!");
                loadCars();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
            }
        }
    }
}