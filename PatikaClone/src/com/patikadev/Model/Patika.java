package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patika {
    private int id;

    private String name;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Patika> getList() {
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "SELECT * FROM \"patika\"";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Patika obj = new Patika(rs.getInt("id"), rs.getString("name"));
                patikaList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patikaList;
    }

    public static boolean addPatikaToDB(String name) {

        String query = "INSERT INTO \"patika\" (name) VALUES (?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id, String name) {
        String query = "UPDATE \"patika\" SET name = ? WHERE id = ?";
        try {

            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, id);
            int rowsUpdated = pr.executeUpdate();
            return rowsUpdated != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Patika getFetch(int id) {
       Object obj = null;
        String query = "SELECT * FROM \"patika\" WHERE id = ? ";
        try {

            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                 obj = new Patika(rs.getInt("id"), rs.getString("name"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (Patika) obj;
    }
    public static Patika getFetch(String name) {
        Patika obj = null;
        String sql = "SELECT * FROM \"patika\" WHERE \"name\" LIKE ?";
        try {
            PreparedStatement pre = DBConnector.getInstance().prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                obj = new Patika(rs.getInt("id"), rs.getString("name"));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }
public static boolean delete(int id){
    String query = "DELETE FROM \"patika\" WHERE ID = ?";
ArrayList<Course> courses = Course.getListByUser(id);
for (Course c : courses){
    Course.deleteToDB(c.getId());
}
    try {
        PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
        pr.setInt(1, id);
        int result = pr.executeUpdate();
        return result != -1;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
