package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseGUI extends JFrame {
    Course course;
    private JPanel wrapper;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_update;

    public UpdateCourseGUI(Course course) {
        this.course = course;
        add(wrapper);
        setSize(250, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        fld_course_lang.setText(course.getLang());
        fld_course_name.setText(course.getName());
        loadPatikaCombo();
        loadUserCombo();
        btn_update.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)) {
                Helper.showMessage("fill");
            } else {
                if (Course.update(course.getId(),User.getFetch(cmb_course_user.getSelectedItem().toString(),1).getId(), Patika.getFetch(cmb_course_patika.getSelectedItem().toString()).getId(), fld_course_name.getText(), fld_course_lang.getText())) {
                    Helper.showMessage("success");
                }
                dispose();
            }
        });
    }


    public void loadPatikaCombo() {
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadUserCombo() {
        cmb_course_user.removeAllItems();
        for (User obj : User.getList()) {
            if (obj.getType().equals("educator")) {
                cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
            }

        }
    }

}
