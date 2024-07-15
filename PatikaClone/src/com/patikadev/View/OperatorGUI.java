package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrll_user_list;
    private JTable tbl_usr_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cbm_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_srch_user_name;
    private JTextField fld_srch_user_uname;
    private JComboBox cmb_srch_user_type;
    private JButton btn_srch_user;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JLabel lbl_patika_name;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private final Operator operator;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu courseMenu;


    public OperatorGUI(Operator operator) {
        this.operator = operator;
        // Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.centerScreen("x", getSize()), Helper.centerScreen("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldin " + operator.getName());
        // model user list
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID ", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];
        loadUserModel();


        tbl_usr_list.setModel(mdl_user_list);
        tbl_usr_list.getTableHeader().setReorderingAllowed(false);

        tbl_usr_list.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            try {
                String selected_user_id = tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(selected_user_id);
            } catch (Exception e) {

            }

        });
        tbl_usr_list.getModel().addTableModelListener(tableModelEvent -> {
            if (tableModelEvent.getType() == TableModelEvent.UPDATE) {
                try {

                    int user_id = Integer.parseInt(tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 0).toString());
                    String user_name = tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 1).toString();
                    String user_uname = tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 2).toString();
                    String user_pass = tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 3).toString();
                    String user_type = tbl_usr_list.getValueAt(tbl_usr_list.getSelectedRow(), 4).toString();
                    if (!user_type.equals("operator")){
                        if (User.updateToDB(user_id, user_name, user_uname, user_pass, user_type)) {
                            Helper.showMessage("veri güncellendi");
                            loadUserModel();
                            loadUserCombo();
                            loadCourseModel();
                        } else {
                            Helper.showMessage("error");
                        }
                    }


                } catch (Exception e) {
                    System.out.println("işlem hatası ");
                }


            }
        });
        // ## model user list
        // model patika list
        patikaMenu = new JPopupMenu();
        JMenuItem updatemenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updatemenu);
        patikaMenu.add(deleteMenu);

        updatemenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(selected_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selected_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(selected_id)) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                    Helper.showMessage("Silme işlemi başarılı");

                } else Helper.showMessage("error");
            }
        });
        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
// ## model patika list

        // model course list
        courseMenu = new JPopupMenu();
        JMenuItem updateCourseMenu = new JMenuItem("Güncelle");
        JMenuItem deleteCourseMenu = new JMenuItem("Sil");
        courseMenu.add(updateCourseMenu);
        courseMenu.add(deleteCourseMenu);

        deleteCourseMenu.addActionListener(actionEvent -> {
            if (Helper.confirm("sure")){
                int selectedID =Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
                if (Course.deleteToDB(selectedID)){
                    loadCourseModel();
                    Helper.showMessage("Silme İşlemi Başarılı");
                }else Helper.showMessage("error");
            }
        });
        updateCourseMenu.addActionListener(actionEvent -> {
            int selectedID =Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
            UpdateCourseGUI updateCourseGUI = new UpdateCourseGUI(Course.getFetch(selectedID));
            updateCourseGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCourseModel();
                }
            });
        });

        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID", "Ders Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.setComponentPopupMenu(courseMenu);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadCourseModel();
        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_course_list.rowAtPoint(point);
                tbl_course_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
// ## model course list
loadPatikaCombo();
loadUserCombo();
        btn_user_add.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMessage("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cbm_user_type.getSelectedItem().toString();
                if (User.addUserToDB(name, uname, pass, type)) {
                    loadUserModel();
                    loadUserCombo();
                    Helper.showMessage("success");
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);

                } else {
                    Helper.showMessage("matchError");
                }


            }
        });
        btn_user_delete.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_user_id)) {
                Helper.showMessage("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int id = Integer.parseInt(fld_user_id.getText());
                    if (User.deleteToDB(id)) {
                        loadUserModel();
                        loadUserCombo();
                        loadCourseModel();
                        Helper.showMessage("Silme işlemi başarılı");
                        fld_user_id.setText(null);
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });

        btn_srch_user.addActionListener(actionEvent -> {
            String name = fld_srch_user_name.getText();
            String uname = fld_srch_user_uname.getText();
            String type = cmb_srch_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name, uname, type);
            ArrayList<User> searchedUser = User.searchUserList(query);
            loadUserModel(searchedUser);

        });
        btn_logout.addActionListener(actionEvent -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();

        });

        btn_patika_add.addActionListener(actionEvent -> {
            if (Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMessage("fill");
            } else {
                String name = fld_patika_name.getText();

                if (Patika.addPatikaToDB(name)) {
                    loadPatikaModel();
                    Helper.showMessage("success");
                    fld_user_name.setText(null);
                } else {
                    Helper.showMessage("matchError");
                }
            }
        });
        btn_course_add.addActionListener(actionEvent -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)){
                Helper.showMessage("fill");

            }else {
                if (Course.add(userItem.getKey(),patikaItem.getKey(),fld_course_name.getText(),fld_course_lang.getText())){
                    Helper.showMessage("success");
                    loadCourseModel();
                    fld_course_name.setText(null);
                    fld_course_lang.setText(null);
                }
            }
        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getList()) {
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_usr_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : User.getList()) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_usr_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : list) {
            row_user_list[0] = obj.getId();
            row_user_list[1] = obj.getName();
            row_user_list[2] = obj.getUname();
            row_user_list[3] = obj.getPassword();
            row_user_list[4] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadPatikaCombo(){
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadUserCombo(){
        cmb_course_user.removeAllItems();
        for (User obj : User.getList()){
            if (obj.getType().equals("educator")){
                cmb_course_user.addItem(new Item(obj.getId(),obj.getName()));
            }

        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
