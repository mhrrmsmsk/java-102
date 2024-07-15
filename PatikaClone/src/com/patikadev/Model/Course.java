package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika=Patika.getFetch(patika_id);
        if (this.patika == null) {
            System.out.println("Patika is null for patika_id: " + patika_id);}
        this.educator=User.getFetch(user_id);
            if (this.educator == null) {
                System.out.println("Educator is null for user_id: " + user_id);
            }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return this.patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return this.educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Course> getList() {
        ArrayList<Course> list = new ArrayList<>();
        String query = "SELECT * FROM \"course\"";
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,user_id,patika_id,name,lang);
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static  boolean add(int user_id,int patika_id,String name,String lang){
        String query = "INSERT INTO \"course\" (user_id, patika_id, name, lang) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);

           int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMessage("error");
            }
            return response!= -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Course> getListByUser(int user_id) {
        ArrayList<Course> list = new ArrayList<>();
        String query = "SELECT * FROM \"course\"";
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                int userID = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,userID,patika_id,name,lang);
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean deleteToDB(int id) {

        String query = "DELETE FROM \"course\" WHERE ID = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            int result = pr.executeUpdate();
            return result != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean update(int id,int user_id,int patika_id,String name,String lang){
        String query = "UPDATE \"course\" SET user_id = ?, patika_id = ?, name = ?, lang = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);
            pr.setInt(5,id);
            int rowsUpdated =  pr.executeUpdate();
            return rowsUpdated !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Course getFetch(int id) {
        Object obj = null;
        String query = "SELECT * FROM \"course\" WHERE id = ? ";
        try {

            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                int iD = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(iD,user_id,patika_id,name,lang);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (Course) obj;
    }
    public static ArrayList<Course> getCoursesByEducator(int educatorID) {
        ArrayList<Course> educatorCourses = new ArrayList<>();

        try {
            String query = "SELECT * FROM \"course\" WHERE user_id = ?";
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setInt(1, educatorID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                Course course = new Course(id, educatorID, patika_id, name, lang);
                educatorCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda hata izini yazdır
        }

        return educatorCourses;
    }
    public static ArrayList<Course> getCoursesByPatikaId(int patikaId) {
        // Burada, tüm derslerin listesini alınır
        ArrayList<Course> allCourses = getList();
        // Filtrelenmiş derslerin listesi için boş bir ArrayList oluşturulur
        ArrayList<Course> filteredCourses = new ArrayList<>();

        // Tüm derslerin listesi üzerinde döner ve patika ID'sine göre filtreleme yapılır
        for (Course course : allCourses) {
            if (course.patika_id == patikaId) {
                // Eğer dersin patika ID'si istenilen patika ID'si ile eşleşirse, ders filteredCourses listesine eklenir
                filteredCourses.add(course);
            }
        }

        // Filtrelenmiş derslerin listesi döndürülür
        return filteredCourses;
    }

}
