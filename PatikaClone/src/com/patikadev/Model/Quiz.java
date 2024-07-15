package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz {
    private int course_id;
    private int question_id;
    private String question;
    private String op_a;
    private String op_b;
    private String op_c;
    private String op_d;
    private String true_ans;
    private Course relatedCourse;


    public static ArrayList<Quiz> getQuizzesByCourseId(int courseId) {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        try {
            String query = "SELECT * FROM quiz WHERE course_id = ?";
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int question_id = rs.getInt("question_id");
                String question = rs.getString("question");
                String option_a = rs.getString("op_a");
                String option_b = rs.getString("op_b");
                String option_c = rs.getString("op_c");
                String option_d = rs.getString("op_d");
                String true_answer = rs.getString("true_ans");

                Quiz quiz = new Quiz(courseId, question_id, question, option_a, option_b, option_c, option_d, true_answer);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda hata izini yazdır
        }

        return quizzes;
    }




    public static boolean delete(int id){
        String query="DELETE FROM quiz WHERE question_id = ? ";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);

            return pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//Silme Islemi


    public static boolean update(int id,String question,String op_a,String op_b,String op_c,String op_d,String true_ans){
        String updateQuery="UPDATE quiz SET question=?,op_a=?,op_b=?,op_c=?,op_d=?,true_ans=? WHERE question_id =?";
        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(updateQuery);
            pr.setString(1,question);
            pr.setString(2,op_a);
            pr.setString(3,op_b);
            pr.setString(4,op_c);
            pr.setString(5,op_d);
            pr.setString(6,true_ans);
            pr.setInt(7,id);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Secilen sattira gore guncelleme



    public Quiz(int course_id,int question_id, String question, String option_a, String option_b, String option_c, String option_d, String answer) {
        this.course_id=course_id;
        this.question_id = question_id;
        this.question = question;
        this.op_a = option_a;
        this.op_b = option_b;
        this.op_c = option_c;
        this.op_d = option_d;
        this.true_ans = answer;
        relatedCourse=Course.getFetch(course_id);
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_a() {
        return op_a;
    }

    public void setOption_a(String option_a) {
        this.op_a = option_a;
    }

    public String getOption_b() {
        return op_b;
    }

    public void setOption_b(String option_b) {
        this.op_b = option_b;
    }

    public String getOption_c() {
        return op_c;
    }

    public void setOption_c(String option_c) {
        this.op_c = option_c;
    }

    public String getOption_d() {
        return op_d;
    }

    public void setOption_d(String option_d) {
        this.op_d = option_d;
    }

    public String getAnswer() {
        return true_ans;
    }

    public void setAnswer(String answer) {
        this.true_ans = answer;
    }

    public Course getRelatedCourse() {
        return relatedCourse;
    }

    public void setRelatedCourse(Course relatedCourse) {
        this.relatedCourse = relatedCourse;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}