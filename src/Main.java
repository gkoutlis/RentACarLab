import dao.CategoryDAO;
import dao.CarDAO;
import dao.ClientDAO;
import dao.RentDAO;

import model.Category;
import model.Car;
import model.Client;
import model.Rent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Δημιουργία των DAO
        CategoryDAO categoryDAO = new CategoryDAO();
        CarDAO carDAO = new CarDAO();
        ClientDAO clientDAO = new ClientDAO();
        RentDAO rentDAO = new RentDAO();

        // ════════════════════════════════════════
        // TEST 1: Insert Categories
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 1: INSERT CATEGORIES ===");
        categoryDAO.insert(new Category("μικρό"));
        categoryDAO.insert(new Category("μεγάλο"));
        categoryDAO.insert(new Category("οικονομικό"));
        categoryDAO.insert(new Category("τζιπ"));
        System.out.println("✓ 4 categories inserted");

        // ════════════════════════════════════════
        // TEST 2: Find All Categories
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 2: FIND ALL CATEGORIES ===");
        List<Category> allCategories = categoryDAO.findAll();
        for (Category c : allCategories) {
            System.out.println("  ID=" + c.getId() + " -> " + c.getKind());
        }

        // ════════════════════════════════════════
        // TEST 3: Insert Client
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 3: INSERT CLIENT ===");
        Client newClient = new Client(
                "Γιώργος",
                "Παπαδόπουλος",
                "Άρρεν",
                "Αθήνα 12345",
                "giorgos@mail.com",
                "6912345678"
        );
        clientDAO.insert(newClient);
        System.out.println("✓ Client inserted");

        // ════════════════════════════════════════
        // TEST 4: Find All Clients
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 4: FIND ALL CLIENTS ===");
        List<Client> allClients = clientDAO.findAll();
        for (Client c : allClients) {
            System.out.println("  " + c);
        }

        // ════════════════════════════════════════
        // TEST 5: Insert Car (with Category relation!)
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 5: INSERT CAR ===");
        Category jeepCategory = categoryDAO.findById(4);  // assuming "τζιπ" is id=4
        if (jeepCategory != null) {
            Car newCar = new Car(
                    "Toyota RAV4",
                    new BigDecimal("65.50"),
                    2000,
                    5,
                    jeepCategory
            );
            carDAO.insert(newCar);
            System.out.println("✓ Car inserted with category: " + jeepCategory.getKind());
        } else {
            System.out.println("✗ Category not found!");
        }

        // ════════════════════════════════════════
        // TEST 6: Find All Cars
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 6: FIND ALL CARS ===");
        List<Car> allCars = carDAO.findAll();
        for (Car c : allCars) {
            System.out.println("  " + c);
        }

        // ════════════════════════════════════════
        // TEST 7: Insert Rent (with Client AND Car!)
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 7: INSERT RENT ===");
        if (!allClients.isEmpty() && !allCars.isEmpty()) {
            Client firstClient = allClients.get(0);
            Car firstCar = allCars.get(0);

            Rent newRent = new Rent(
                    5,
                    LocalDate.now(),
                    firstClient,
                    firstCar
            );
            rentDAO.insert(newRent);
            System.out.println("✓ Rent inserted: " + firstClient.getName() +
                    " rents " + firstCar.getModel());
        }

        // ════════════════════════════════════════
        // TEST 8: Find All Rents (with full info!)
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 8: FIND ALL RENTS ===");
        List<Rent> allRents = rentDAO.findAll();
        for (Rent r : allRents) {
            System.out.println("  " + r);
        }

        // ════════════════════════════════════════
        // TEST 9: Update
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 9: UPDATE CLIENT ===");
        if (!allClients.isEmpty()) {
            Client toUpdate = allClients.get(0);
            String oldEmail = toUpdate.getEmail();
            toUpdate.setEmail("newemail@mail.com");
            clientDAO.update(toUpdate);

            Client updated = clientDAO.findById(toUpdate.getId());
            System.out.println("  Old email: " + oldEmail);
            System.out.println("  New email: " + updated.getEmail());
        }

        // ════════════════════════════════════════
        // TEST 10: FindById
        // ════════════════════════════════════════
        System.out.println("\n=== TEST 10: FIND BY ID ===");
        Category foundCat = categoryDAO.findById(1);
        System.out.println("  Category id=1: " + foundCat);

        Client foundCli = clientDAO.findById(1);
        System.out.println("  Client id=1: " + foundCli);



        // System.out.println("\n=== TEST 11: DELETE RENT ===");
        // if (!allRents.isEmpty()) {
        //     rentDAO.delete(allRents.get(0).getId());
        //     System.out.println("✓ Rent deleted");
        // }

        System.out.println("\n=== ALL TESTS COMPLETED ===\n");
    }
}