package dao;

import db.DBConfig;
import model.Rent;
import model.Client;
import model.Car;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {

    private ClientDAO clientDAO = new ClientDAO();
    private CarDAO carDAO = new CarDAO();

    public void insert(Rent rent){
        String sql = "INSERT INTO rent (days, date_at, client_id, car_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rent.getDays());
            pstmt.setDate(2, java.sql.Date.valueOf(rent.getDateAt()));
            pstmt.setInt(3, rent.getClient().getId());
            pstmt.setInt(4, rent.getCar().getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Rent findById(int id){
        String sql = "SELECT * FROM rent WHERE rent_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int rentId = rs.getInt("rent_id");
                int days = rs.getInt("days");
                LocalDate dateAt = rs.getDate("date_at").toLocalDate();
                int clientId = rs.getInt("client_id");
                Client client = clientDAO.findById(clientId);
                int carId = rs.getInt("car_id");
                Car car = carDAO.findById(carId);
                Rent rent1 = new Rent(rentId, days, dateAt, client, car);
                return rent1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Rent> findAll(){
        List<Rent> rents = new ArrayList<>();
        String sql = "SELECT * FROM rent";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int rentId = rs.getInt("rent_id");
                int days = rs.getInt("days");
                LocalDate dateAt = rs.getDate("date_at").toLocalDate();
                int clientId = rs.getInt("client_id");
                Client client = clientDAO.findById(clientId);
                int carId = rs.getInt("car_id");
                Car car = carDAO.findById(carId);
                Rent rent2 = new Rent(rentId, days, dateAt, client, car);
                rents.add(rent2);
            }
        } catch (SQLException e) {
                e.printStackTrace();
            }
            return rents;
    }

    public void update(Rent rent) {
        String sql = "UPDATE rent SET days=?, date_at=?, client_id = ?, car_id = ? " +
                "WHERE rent_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rent.getDays());
            pstmt.setDate(2, java.sql.Date.valueOf(rent.getDateAt()));
            pstmt.setInt(3, rent.getClient().getId());
            pstmt.setInt(4, rent.getCar().getId());
            pstmt.setInt(5, rent.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM rent WHERE rent_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}