package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;
import com.patikadev.Model.enrolledClasses;
import com.patikadev.Model.enrolledClasses;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_exit;
    private JLabel lbl_welcome;
    private JComboBox cmb_student_patika;
    private JComboBox cmb_courses;
    private JButton btn_register;
    private JButton btn_quiz;
    private JButton btn_look_content;
    private JComboBox cmb_educator;
    private JComboBox cmb_course_select;
    private JComboBox cmb_rating;
    private JTextArea area_comment;
    private JButton btn_comment;
    private int selectedPatika_id;
    private int course_ID;
    private int EducatorID;
    private int studentID;

    public StudentGUI(int id){
        this.studentID=id;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        loadPatikaCombobox();
        loadCoursesForSelectedPatika(selectedPatika_id);
        loadEducatorCombobox();
        loadCourseForSelectedEducator(EducatorID);

        cmb_student_patika.addActionListener(e -> {
            int selectedPatikaId = ((Item) cmb_student_patika.getSelectedItem()).getKey();
            selectedPatika_id=selectedPatikaId;
            // Seçilen patikanın derslerini yükle
            loadCoursesForSelectedPatika(selectedPatikaId);

        });


        cmb_courses.addActionListener(e -> {
            // Seçilen öğeyi al
            Object selectedItem = cmb_courses.getSelectedItem();

            // Seçilen öğenin null olup olmadığını kontrol et
            if (selectedItem instanceof Item) {
                Item selectedCourseItem = (Item) selectedItem;

                // Dersin ID'sini al
                int courseId = selectedCourseItem.getKey();
                course_ID = courseId;
                System.out.println(courseId);
            } else {
                Helper.showMessage("Hiçbir ders seçilmedi.");
            }
        });


        cmb_educator.addActionListener(e -> {
            int selectedEducatorId = ((Item) cmb_educator.getSelectedItem()).getKey();
            EducatorID=selectedEducatorId;
            System.out.println(EducatorID);
            // Seçilen patikanın derslerini yükle
            loadCourseForSelectedEducator(selectedEducatorId);

        });
        btn_look_content.addActionListener(e -> {
            contentGUIforSTUDENT contentGUIforSTUDENT=new contentGUIforSTUDENT(course_ID);
        });
        btn_register.addActionListener(actionEvent -> {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            String date = today.format(formatter);
            Item courseItem= (Item) cmb_courses.getSelectedItem();
            if (courseItem==null){
                Helper.showMessage("Lutfen Bir Ders Secin");
            }else {
                if (Helper.confirm(courseItem.toString()+"Dersıne Kayıt Olmak Istedıgıne Emın Mısın ?")){
                    if (enrolledClasses.add(courseItem.getKey(),studentID,date)){
                        Helper.showMessage("done");
                    }else {
                        //Helper.showMassage("error");
                    }
                }
            }
        });
        btn_quiz.addActionListener(e -> {
            if (enrolledClasses.isAlreadyEnrolled(course_ID,studentID)){
                quizForSTUDENT quizForSTUDENT=new quizForSTUDENT(course_ID);
            }else {
                Helper.showMessage("Quiz olabilmeniz icin derse kaydolmalisiniz! ");
            }
        });
        btn_exit.addActionListener(actionEvent -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });
    }

    public void loadPatikaCombobox(){
        cmb_student_patika.getSelectedItem();
        for (Patika obj:Patika.getList()){
            cmb_student_patika.addItem(new Item(obj.getId(),obj.getName()));
        }
    }
    public void loadEducatorCombobox(){
        cmb_educator.removeAllItems();
        for (User obj:User.getList()){
            if (obj.getType().equals("educator")){
                cmb_educator.addItem(new Item(obj.getId(),obj.getName()));
            }

        }
    }
    public void loadCourseForSelectedEducator(int educator) {
        cmb_course_select.removeAllItems();

        try {
            // Seçilen patikanın derslerini al
            ArrayList<Course> courses = Course.getCoursesByEducator(educator);

            // Dersleri ComboBox'a ekle
            for (Course course : courses) {
                cmb_course_select.addItem(new Item(course.getId(), course.getName()));
            }
        } catch (Exception ex) {
            // Diğer istisnaları işlemek için buraya kod ekleyebilirsiniz
            // Genel bir hata mesajı gösterebilir veya loglayabilirsiniz
            Helper.showMessage("Çok fazla deneme yaptınız !");
            dispose();



        }
    }
    public void loadCoursesForSelectedPatika(int patikaId) {
        cmb_courses.removeAllItems();

        try {
            // Seçilen patikanın derslerini al
            ArrayList<Course> courses = Course.getCoursesByPatikaId(patikaId);

            // Dersleri ComboBox'a ekle
            for (Course course : courses) {
                cmb_courses.addItem(new Item(course.getId(), course.getName()));
            }
        } catch (Exception ex) {
            // Diğer istisnaları işlemek için buraya kod ekleyebilirsiniz
            // Genel bir hata mesajı gösterebilir veya loglayabilirsiniz
            Helper.showMessage("Çok fazla deneme yaptınız !");
            dispose();



        }
    }
}
