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


}
