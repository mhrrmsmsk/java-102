package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String uname;
    private String password;
    private String type;

    public User() {
    }

    public User(int id, String name, String uname, String password, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList() {
        ArrayList<User> list = new ArrayList<>();
        String query = "SELECT * FROM \"user\""; // "user" bir anahtar kelime olduğu için tırnak içinde kullanılmalıdır.
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean deleteToDB(int id) {

        String query = "DELETE FROM \"user\" WHERE ID = ?";
        ArrayList<Course> courseList = Course.getListByUser(id);
        for (Course course : courseList) {
            Course.deleteToDB(course.getId());
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

    public static boolean addUserToDB(String name, String uname, String password, String type) {


        String query = "INSERT INTO \"user\" (name, uname, password, type) VALUES (?, ?, ?, ?)";
        // Checking if the user has already been added to the db
        User findUser = User.getFetch(uname);
        if (findUser != null) {

            return false;
        } else {
            try {
                PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                pr.setString(1, name);
                pr.setString(2, uname);
                pr.setString(3, password);
                pr.setObject(4, type, Types.OTHER);

                int response = pr.executeUpdate();

                if (response == -1) {
                    Helper.showMessage("error");
                }
                return response != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static boolean updateToDB(int id, String name, String uname, String password, String type) {
        String query = "UPDATE \"user\" SET name = ?, uname = ?, password = ?, type = CAST(? AS user_role) WHERE id = ?";
        User findUser = User.getFetch(uname);
        if (findUser != null && findUser.getId() != id) {
            Helper.showMessage("Bukullanıcı daha önceden eklenmiş Lütfen farklı bir kullanıcı giriniz...");
            return false;
        }
        if (!(type.equals("student") || type.equals("operator") || type.equals("educator"))) {
            Helper.showMessage("Invalid Type");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, password);
            pr.setString(4, type);
            pr.setInt(5, id);
            int rowsUpdated = pr.executeUpdate();
            return rowsUpdated != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static User getFetch(String uname) {
        User obj = null;

        String sql = "SELECT * FROM \"user\" WHERE \"uname\" LIKE ?";
        try {
            PreparedStatement pre = DBConnector.getInstance().prepareStatement(sql);
            pre.setString(1, uname);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static User getFetch(String uname,String pass) {
        User obj = null;

        String sql = "SELECT * FROM \"user\" WHERE \"uname\" = ? AND \"password\" = ?";
        try {
            PreparedStatement pre = DBConnector.getInstance().prepareStatement(sql);
            pre.setString(1, uname);
            pre.setString(2,pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                if (rs.getString("type").equals("operator")){
                    obj = new Operator();
                }else if (rs.getString("type").equals("educator")){
                    obj = new Educator();
                }else{
                    obj = new User();
                }

                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static User getFetch(String name, int i) {
        User obj = null;

        String sql = "SELECT * FROM \"user\" WHERE \"name\" LIKE ?";
        try {
            PreparedStatement pre = DBConnector.getInstance().prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static User getFetch(int id) {
        User obj = null;

        String sql = "SELECT * FROM \"user\" WHERE id= ?";
        try {
            PreparedStatement pre = DBConnector.getInstance().prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obj;
    }

    public static ArrayList<User> searchUserList(String query) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname")); // "uname" olarak ayarla
                obj.setPassword(rs.getString("password")); // "password" olarak ayarla
                obj.setType(rs.getString("type")); // "type" olarak ayarla
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String searchQuery(String name, String uname, String type) {
        String query = "SELECT * FROM \"user\" WHERE uname LIKE '%{{uname}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{uname}}", uname);
        query = query.replace("{{name}}", name);

        if (!type.isEmpty()) {
            query += " AND CAST(type AS TEXT) LIKE '%{{type}}%'";
            query = query.replace("{{type}}", type);
        }
        return query;
    }

}
