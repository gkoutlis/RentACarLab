package dao;

import db.DBConfig;
import model.Car;
import model.Category;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private CategoryDAO categoryDAO = new CategoryDAO();

    public void insert(Car car) {
        String sql = "INSERT INTO car (model, cost_per_day, horse_power, seats, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getModel());
            pstmt.setBigDecimal(2, car.getCostPerDay());
            pstmt.setInt(3, car.getHorsePower());
            pstmt.setInt(4, car.getSeats());
            pstmt.setInt(5, car.getCategory().getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car findById(int id){
        String sql = "SELECT * FROM car WHERE car_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int carId = rs.getInt("car_id");
                String model = rs.getString("model");
                BigDecimal costPerDay = rs.getBigDecimal("cost_per_day");
                int horsePower = rs.getInt("horse_power");
                int seats = rs.getInt("seats");
                int categoryId = rs.getInt("category_id");
                Category category = categoryDAO.findById(categoryId);
                Car car1 = new Car(carId, model, costPerDay, horsePower, seats, category);
                return car1;

            }
        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int carId = rs.getInt("car_id");
                String model = rs.getString("model");
                BigDecimal costPerDay = rs.getBigDecimal("cost_per_day");
                int horsePower = rs.getInt("horse_power");
                int seats = rs.getInt("seats");
                int categoryId = rs.getInt("category_id");
                Category category = categoryDAO.findById(categoryId);
                Car car2 = new Car(carId, model, costPerDay, horsePower, seats, category);
                cars.add(car2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void update(Car car) {
        String sql = "UPDATE car SET model=?, cost_per_day=?, horse_power=?, seats=?, category_id=? " +
                "WHERE car_id=?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getModel());
            pstmt.setBigDecimal(2, car.getCostPerDay());
            pstmt.setInt(3, car.getHorsePower());
            pstmt.setInt(4, car.getSeats());
            pstmt.setInt(5, car.getCategory().getId());
            pstmt.setInt(6, car.getId());


            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM car WHERE car_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
