package com.patikadev.Model;


import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class enrolledClasses {
    private int enrolled_id;
    private int course_id;
    private int student_id;
    private String enrolled_date;



    public static boolean add(int course_id,int student_id,String enrolled_date){
        String query="INSERT INTO enrolledclases (course_id,student_id,enrolled_date) VALUES (? , ? , ? )";
        if (isAlreadyEnrolled(course_id, student_id)) {
            Helper.showMessage("Öğrenci zaten bu derse kayıtlı.");
            return false;
        }
        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,course_id);
            pr.setInt(2,student_id);
            pr.setString(3,enrolled_date);


            int response= pr.executeUpdate();
            if (response == -1){
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }//Veritabanina Content Ekeleme

    public static boolean isAlreadyEnrolled(int courseId, int studentId) {
        String queryy = "SELECT * FROM enrolledclases WHERE course_id = ? AND student_id = ?";

        try (PreparedStatement statement = DBConnector.getInstance().prepareStatement(queryy)) {
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Kayıt varsa true, yoksa false döndür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
}

