package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class contentGUIforSTUDENT extends JFrame {

    private JPanel wrapper;
    private JTable tbl_content;
    private JTextField field_educator_title;
    private JTextField field_educator_description;
    private JTextField field_educator_yt;
    private JButton btn_educator_content;
    private JButton btn_content_exit;
    private JScrollPane pnl_edu_top;

    //Modeller
    private DefaultTableModel mdl_content;
    private Object[] row_content_list;
    private int courseID;

    //JpopMenu Guncelleme Silme Icin
    //private JPopupMenu contentMenu;

    public contentGUIforSTUDENT(int courseId){
        this.courseID=courseId;
        setResizable(false);
        add(wrapper);
        tbl_content.setEnabled(false);
        setSize(1000,500);
        int x= Helper.centerScreen("x",getSize());
        int y=Helper.centerScreen("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);



        //##############  PopUp guncelleme silme islemleri  ##############//
        //contentMenu=new JPopupMenu();
        //JMenuItem deleteMenu=new JMenuItem("Sil");
        //contentMenu.add(deleteMenu);

        tbl_content.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point=e.getPoint();
                int selectedROW=tbl_content.rowAtPoint(point);
                tbl_content.setRowSelectionInterval(selectedROW,selectedROW);
            }
        });//Sag tikladiginda mavi olma secme islemleri

        /*deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selected_row_id=Integer.parseInt(tbl_content.getValueAt(tbl_content.getSelectedRow(),0).toString());
                if (Content.delete(selected_row_id)){
                    Helper.showMassage("done");
                    loadContent(courseID);
                }else {
                    Helper.showMassage("error");
                }

            }


        });*/





        //Tablo Islemleri

        mdl_content=new DefaultTableModel();
        Object[] col_content_list = {"ID", "İçerik Başlığı", "İçerik Açıklaması", "İçerik Youtube Linki", "Ders"};
        mdl_content.setColumnIdentifiers(col_content_list);
        row_content_list=new Object[col_content_list.length];
        loadContent(courseID);



        tbl_content.setModel(mdl_content);
        //tbl_content.setComponentPopupMenu(contentMenu);//Pop up menu eklemesi
        tbl_content.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_content.getTableHeader().setReorderingAllowed(false);




        btn_content_exit.addActionListener(e -> {
            dispose();
            //loginGUI loginGUI=new loginGUI();

        });
    }

    public void loadContent(int course_id) {
        // Önce tabloyu temizle
        DefaultTableModel tableModel = (DefaultTableModel) tbl_content.getModel();
        tableModel.setRowCount(0);

        // Belirli eğitmenin derslerini al
        ArrayList<Content> contentList = Content.getContent(course_id);

        // Her bir ders için tabloya bir satır ekle
        int i=0;
        for (Content obj : contentList) {
            i=0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getLink();
            row_content_list[i++] = obj.getCourse().getName();
            mdl_content.addRow(row_content_list);
        }
    }

    public static void main(String[] args) {

        contentGUIforSTUDENT contentGUIforSTUDENT=new contentGUIforSTUDENT(44);

    }




}


