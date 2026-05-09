package dao;

import db.DBConfig;
import model.Category;
import model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void insert(Client client) {
        String sql = "INSERT INTO client (name, surname, gender, address, email, phone) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getSurname());
            pstmt.setString(3, client.getGender());
            pstmt.setString(4, client.getAddress());
            pstmt.setString(5, client.getEmail());
            pstmt.setString(6, client.getPhone());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }


    public Client findById(int id) {
        String sql = "SELECT * FROM client WHERE client_id = ? ";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int clientId = rs.getInt("client_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Client client1 = new Client(clientId, name, surname,gender, address, email, phone);
                return client1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int clientId = rs.getInt("client_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Client client2 = new Client(clientId, name, surname,gender, address, email, phone);
                clients.add(client2);
            }
        }catch (SQLException e)  {
            e.printStackTrace();
        }
        return clients;
    }

    public void update(Client client) {
        String sql = "UPDATE client SET name=?, surname=?, gender=?, address=?, email=?, phone=? WHERE client_id=? ";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,client.getName());
            pstmt.setString(2, client.getSurname());
            pstmt.setString(3, client.getGender());
            pstmt.setString(4, client.getAddress());
            pstmt.setString(5, client.getEmail());
            pstmt.setString(6, client.getPhone());
            pstmt.setInt(7,client.getId());

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM client WHERE client_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}