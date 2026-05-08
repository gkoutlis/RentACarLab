package dao;

import db.DBConfig;
import model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO {
    public void insert(Category category){
        String sql ="INSERT INTO category (kind) VALUES (?) ";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, category.getKind());

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public  Category findById(int id){
        String sql = "SELECT * FROM category WHERE category_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String kind = rs.getString("kind");
                Category category1 = new Category(categoryId, kind);
                return category1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String kind = rs.getString("kind");
                Category category2 = new Category(categoryId, kind);
                categories.add(category2);
            }
        }catch (SQLException e)  {
            e.printStackTrace();
        }
        return categories;
    }

    public void update(Category category){
        String sql = "UPDATE category SET kind = ? WHERE category_id = ? ";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, category.getKind());
            pstmt.setInt(2, category.getId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(int id){
        String sql = "DELETE FROM category WHERE category_id = ?";

        try (Connection conn = DBConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
