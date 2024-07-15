package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Content;
import com.patikadev.Model.Course;
import com.patikadev.Model.Educator;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JTabbedPane tab_educator;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JTable tbl_educator_list;
    private JScrollPane scrl_educator_list;
    private JTable tbl_content_list;
    private JTextField fld_cont_title;
    private JTextField fld_cont_description;
    private JTextField fld_cont_link;
    private JComboBox cmb_cont_course;
    private JButton btn_add_content;
    private JTextArea fld_cont_quiz;
    private JComboBox cmb_cont_quiz_course;
    private JButton btn_add_quiz;
    private JTextField fld_a;
    private JTextField fld_b;
    private JTextField fld_c;
    private JTextField fld_d;
    private JComboBox cmb_answer;
    private JButton btn_search;
    private JTextField fld_srch_title;
    private JComboBox cmb_srch_course;
    private final Educator educator;
    private DefaultTableModel mdl_educator_list;
    private Object[] row_educator_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;

    public EducatorGUI(Educator educator) {
        this.educator = educator;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldin " + educator.getName());
        initializeTableModels();
        loadContentModel();
        loadEducatorModel();
        loadCourseCombo();
        loadSearchCourseCombo();
        loadQuestionCombo();


        btn_logout.addActionListener(actionEvent -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });

        btn_add_content.addActionListener(actionEvent -> {
            Item courseItem = (Item) cmb_cont_course.getSelectedItem();
            if (Helper.isFieldEmpty(fld_cont_title) || Helper.isFieldEmpty(fld_cont_description) || Helper.isFieldEmpty(fld_cont_link)) {
                Helper.showMessage("fill");
            } else {
                if (Content.addContentToDB(fld_cont_title.getText(), fld_cont_description.getText(), fld_cont_link.getText(), courseItem.getKey(), educator.getId())) {
                    Helper.showMessage("success");
                    loadContentModel();
                    fld_cont_title.setText(null);
                    fld_cont_description.setText(null);
                    fld_cont_link.setText(null);

                }
            }
        });
        btn_search.addActionListener(actionEvent -> {
            Item courseSearchItem = (Item) cmb_srch_course.getSelectedItem();
            String title = fld_srch_title.getText();
            int course_id = courseSearchItem.getKey();
            String query = Content.searchQuery(title, course_id);
            ArrayList<Content> searchedContent = Content.searchContentList(query);
            loadContentModel(searchedContent);

        });

        btn_add_quiz.addActionListener(actionEvent -> {
            String question = fld_cont_quiz.getText();
            String ans_a = fld_a.getText();
            String ans_b = fld_b.getText();
            String ans_c = fld_c.getText();
            String ans_d = fld_d.getText();
            String true_ans = cmb_answer.getSelectedItem().toString();
            Item questionItem = (Item) cmb_cont_quiz_course.getSelectedItem();
            if (Content.addQuestion(questionItem.getKey(), question, ans_a, ans_b, ans_c, ans_d, true_ans)) {
                Helper.showMessage("success");
                fld_cont_quiz.setText(null);
                fld_a.setText(null);
                fld_b.setText(null);
                fld_c.setText(null);
                fld_d.setText(null);

            }
        });
    }

    public void loadCourseCombo() {
        cmb_cont_course.removeAllItems();
        for (Course obj : Course.getList()) {
            if (obj.getEducator().getName().equals(educator.getName())) {
                cmb_cont_course.addItem(new Item(obj.getId(), obj.getName()));
            }

        }
    }

    public void loadQuestionCombo() {
        cmb_cont_quiz_course.removeAllItems();
        for (Course obj : Course.getCoursesByEducator(educator.getId())) {
            cmb_cont_quiz_course.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadSearchCourseCombo() {
        cmb_srch_course.removeAllItems();
        for (Course obj : Course.getList()) {
            if (obj.getEducator().getName().equals(educator.getName())) {
                cmb_srch_course.addItem(new Item(obj.getId(), obj.getName()));
            }

        }
    }

    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Content obj : Content.getList(educator.getId())) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse().getName();
            mdl_content_list.addRow(row_content_list);


        }
    }

    private void loadContentModel(ArrayList<Content> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Content obj : list) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse().getName();
            mdl_content_list.addRow(row_content_list);


        }
    }

    private void loadEducatorModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_educator_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getList()) {
            if (obj.getEducator().getName().equals(educator.getName())) {
                Object[] row_educator_list = new Object[5]; // Create a new array for each row
                i = 0;
                row_educator_list[i++] = obj.getId();
                row_educator_list[i++] = obj.getName();
                row_educator_list[i++] = obj.getLang();
                row_educator_list[i++] = obj.getPatika().getName();
                row_educator_list[i++] = obj.getEducator().getName();
                mdl_educator_list.addRow(row_educator_list);
            }
        }
    }

    private void initializeTableModels() {
        // Eğitimler tablosu
        mdl_educator_list = new DefaultTableModel();
        Object[] col_educator_list = {"ID", "Ders Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_educator_list.setColumnIdentifiers(col_educator_list);
        row_educator_list = new Object[col_educator_list.length];
        tbl_educator_list.setModel(mdl_educator_list);
        tbl_educator_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_educator_list.getTableHeader().setReorderingAllowed(false);

        // İçerikler tablosu
        mdl_content_list = new DefaultTableModel();
        Object[] col_content_list = {"ID", "İçerik Başlığı", "İçerik Açıklaması", "İçerik Youtube Linki", "Ders"};
        mdl_content_list.setColumnIdentifiers(col_content_list);
        row_content_list = new Object[col_content_list.length];
        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
    }
}
