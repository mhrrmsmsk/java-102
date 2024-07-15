package com.patikadev.Model;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Content {
    private int id;
    private String title;
    private String description;
    private String link;
    private int course_id;
    private int educator_id;

    private Course course;
    private User educator;

    public Content(int id, String title, String description, String link, int course_id, int educator_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.course_id = course_id;
        this.educator_id = educator_id;
        this.course = Course.getFetch(course_id);
        this.educator = Educator.getFetch(educator_id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Content> getList(int user_id) {
        ArrayList<Content> list = new ArrayList<>();
        String query = "SELECT * FROM \"content\" WHERE educator_id = " + user_id;
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String link = rs.getString("link");
                int course_id = rs.getInt("course_id");
                int educator_id = rs.getInt("educator_id");

                obj = new Content(id, title, description, link, course_id, educator_id);
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean addContentToDB(String title, String description, String link, int course_id, int educator_id) {
        String query = "INSERT INTO \"content\" (title, description, link, course_id, educator_id) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2, description);
            pr.setString(3, link);
            pr.setInt(4, course_id);
            pr.setInt(5, educator_id);

            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addQuestion(int course_id, String question, String op_a, String op_b, String op_c, String op_d, String true_ans) {
        String query = "INSERT INTO \"quiz\" (course_id, question, op_a, op_b, op_c,op_d,true_ans) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,course_id);
            pr.setString(2,question);
            pr.setString(3,op_a);
            pr.setString(4,op_b);
            pr.setString(5,op_c);
            pr.setString(6,op_d);
            pr.setString(7,true_ans);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMessage("error");
            }
            return response != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<Content> getContent(int course_ID) {
        ArrayList<Content> contentss = new ArrayList<>();
        String query = "SELECT * FROM \"content\" WHERE course_id = ?";
        Content obj;

        try {
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setInt(1, course_ID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String link = rs.getString("link");
                int course_id = rs.getInt("course_id");
                int educator_id = rs.getInt("educator_id");

                obj = new Content(id, title, description, link, course_id, educator_id);
                contentss.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda hata izini yazdır
        }

        return contentss;
    }

    public static ArrayList<Content> searchContentList(String query) {
        ArrayList<Content> list = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Content obj = new Content(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("link"), rs.getInt("course_id"), rs.getInt("educator_id"));
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String searchQuery(String title, int course_id) {
        String query = "SELECT * FROM \"content\" WHERE title LIKE '%{{title}}%' AND course_id =" + course_id;
        query = query.replace("{{title}}", title);
        if (title == null) {
            query = "SELECT * FROM \"content\" WHERE  course_id =" + course_id;
        }

        return query;
    }
    public static boolean update(int id,String title,String description,String link,int course_id,int educator_id){
        String updateQuery="UPDATE content SET contentName=?,contentDesc=?,videoLink=?,course_id=?,educator_id=? WHERE id =?";
        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(updateQuery);
            pr.setString(1,title);
            pr.setString(2,description);
            pr.setString(3,link);
            pr.setInt(4,course_id);
            pr.setInt(5,educator_id);
            pr.setInt(6,id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
}
