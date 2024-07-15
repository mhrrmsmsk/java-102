package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends JFrame {
    private Patika patika;
    private JPanel wrapper;
    private JLabel lbl_patika;
    private JTextField fld_patika_name;
    private JButton btn_update;

    public UpdatePatikaGUI(Patika patika) {
        this.patika = patika;
        add(wrapper);
        setSize(300, 150);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_patika_name.setText(patika.getName());

        btn_update.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMessage("fill");
            } else {
                if (Patika.update(patika.getId(), fld_patika_name.getText())) {
                    Helper.showMessage("succes");

                }
                dispose();
            }
        });
    }


}
