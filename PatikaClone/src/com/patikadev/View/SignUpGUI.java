package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_uname;
    private JTextField fld_name;
    private JTextField fld_pass;
    private JTextField fld_listen;
    private JTextField fld_username;
    private JTextField fld_pass_repead;
    private JButton btn_logup;

    public SignUpGUI() {
        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_logup.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass_repead) || Helper.isFieldEmpty(fld_username)) {
                Helper.showMessage("fill");
            }else {
                if (!fld_pass.getText().equals(fld_pass_repead.getText())){
                    Helper.showMessage("Lütfen şifreleri doğru girirniz");
                }else {
                    if (User.addUserToDB(fld_name.getText(),fld_uname.getText(),fld_pass.getText(),"student")){
                        Helper.showMessage("Başarılı bir şekilde kayıt yapıldı.");
                        dispose();
                    }
                }
            }

        });
    }
}
