package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JButton btn_login;
    private JButton btn_signup;

    public LoginGUI() {
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.centerScreen("x",getSize()),Helper.centerScreen("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        btn_login.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_user_uname)|| Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMessage("fill");
            }else {
                User user = User.getFetch(fld_user_uname.getText(),fld_user_pass.getText());
if (user!=null){
    Helper.showMessage("Başarıyla Giriş Yapıldı");
    switch (user.getType()){
        case "operator":
            OperatorGUI operatorGUI = new OperatorGUI((Operator) user);
            break;
        case "educator":
            EducatorGUI educatorGUI = new EducatorGUI((Educator) user);
            break;
        case "student":
            StudentGUI studentGUI = new StudentGUI(user.getId());
            break;
    }
    dispose();
}else{
    Helper.showMessage("Kullanıcı adı veya Şifre yanlış Lütfen tekrar deneyiniz");
}
            }
        });
        btn_signup.addActionListener(actionEvent -> {
            SignUpGUI signUpGUI = new SignUpGUI();
        });
    }

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
    }
}
