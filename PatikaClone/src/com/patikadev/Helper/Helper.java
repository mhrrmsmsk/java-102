package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static boolean confirm(String str){
String msg;
switch (str){
    case "sure":
        msg = "Bu işlemi gerçekleştirmek istediğne emin misiniz ?";
        break;
    default:
        msg = str;

}
return JOptionPane.showConfirmDialog(null,msg,"Son kararın mı",JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

        }
    }
            public static int centerScreen(String axis , Dimension size){
    int point ;
    switch (axis){
        case "x":
            point = (Toolkit.getDefaultToolkit().getScreenSize().width-(size.width))/2;
            break;
            case "y":
             point =(Toolkit.getDefaultToolkit().getScreenSize().height-(size.height))/2;
             break;
        default:
            point=0;
            break;
    }
    return point;
            }

            public static boolean isFieldEmpty(JTextField jTextField){
        return jTextField.getText().trim().isEmpty();
            }
            public static void showMessage(String str){
        String msg;
        String title = null;
        switch (str){
            case "fill":
                msg="Lütfen Tüm Alanları Doldurun...";
                title = "Hata";
                break;
            case "success":
                msg ="İşlem Başarılı...";
                title = "Bilgi";
                break;
            case "error":
                msg = "Ekleme sırasında hata oluştu...";
                title = "Hata";
                break;
            case "matchError":
                msg = "Bu kulalnıcı zaten kayıtlı...";
                title ="Hata";
                break;
            default:
                msg=str;
                break;
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
            }

}
