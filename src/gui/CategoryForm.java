package gui;

import dao.CategoryDAO;
import model.Category;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryForm extends JFrame {


    private JTextField kindField;
    private JComboBox<Category> categoryCombo;


    private CategoryDAO categoryDAO = new CategoryDAO();


    public CategoryForm() {
        setTitle("Διαχείριση Κατηγοριών");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // κλείνει μόνο αυτό το παράθυρο
        setLocationRelativeTo(null);  // κεντράρει στην οθόνη
        setLayout(new GridLayout(6, 1, 10, 10));  // 5 σειρές, 1 στήλη, 10px gaps


        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Νέα κατηγορία:"));
        kindField = new JTextField(20);
        p1.add(kindField);
        add(p1);


        JButton addBtn = new JButton("Προσθήκη");
        addBtn.addActionListener(e -> addCategory());  // lambda — όταν πατηθεί, καλεί addCategory()
        add(addBtn);


        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Υπάρχουσες:"));
        categoryCombo = new JComboBox<>();
        p3.add(categoryCombo);
        add(p3);


        JButton refreshBtn = new JButton("Ανανέωση Λίστας");
        refreshBtn.addActionListener(e -> loadCategories());
        add(refreshBtn);


        JButton editBtn = new JButton("Επεξεργασία Επιλεγμένης");
        editBtn.addActionListener(e -> editCategory());
        add(editBtn);


        JButton deleteBtn = new JButton("Διαγραφή Επιλεγμένης");
        deleteBtn.addActionListener(e -> deleteCategory());
        add(deleteBtn);

        JButton backBtn = new JButton("← Πίσω στο Μενού");
        backBtn.addActionListener(e -> dispose());
        add(backBtn);



        categoryCombo.addActionListener(e -> {
            Category selected = (Category) categoryCombo.getSelectedItem();
            if (selected != null) {
                kindField.setText(selected.getKind());
            }
        });


        loadCategories();
    }



    private void editCategory() {
        Category selected = (Category) categoryCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επιλεξε κατηγορια!");
            return;
        }

        String newKind = kindField.getText().trim();
        if (newKind.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπληρωσε το πεδιο!");
            return;
        }

        try {
            selected.setKind(newKind);
            categoryDAO.update(selected);
            JOptionPane.showMessageDialog(this, "Ενημερωθηκε!");
            loadCategories();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Σφαλμα: " + ex.getMessage());
        }
    }

    private void addCategory() {
        String kind = kindField.getText().trim();


        if (kind.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπλήρωσε το πεδίο!");
            return;
        }


        Category category = new Category(kind);
        categoryDAO.insert(category);


        JOptionPane.showMessageDialog(this, "Προστέθηκε: " + kind);


        kindField.setText("");
        loadCategories();
    }


    private void loadCategories() {
        categoryCombo.removeAllItems();

        List<Category> categories = categoryDAO.findAll();
        for (Category c : categories) {
            categoryCombo.addItem(c);
        }
    }


    private void deleteCategory() {
        Category selected = (Category) categoryCombo.getSelectedItem();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Επίλεξε κατηγορία!");
            return;
        }


        int confirm = JOptionPane.showConfirmDialog(this,
                "Σίγουρα θες να διαγράψεις: " + selected.getKind() + ";",
                "Επιβεβαίωση", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                categoryDAO.delete(selected.getId());
                JOptionPane.showMessageDialog(this, "Διαγράφηκε!");
                loadCategories();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Δεν μπορεί να διαγραφεί! Υπάρχουν αυτοκίνητα σε αυτή την κατηγορία.");
            }
        }
    }
}