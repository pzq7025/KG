package homework;

/**
 * 所有的GUI的实现都在这里
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main_GUI_Plugin extends conn_db implements ActionListener {
    private JMenuItem Change_User, Delete_User, Add_User, Record_Upload, Record_Download, Change_Information;

    void setAdd_User(JMenuItem add_User) {
        Add_User = add_User;
    }

    void setChange_Information(JMenuItem change_Information) {
        Change_Information = change_Information;
    }

    void setChange_User(JMenuItem change_User) {
        Change_User = change_User;
    }

    void setDelete_User(JMenuItem delete_User) {
        Delete_User = delete_User;
    }

    void setRecord_Download(JMenuItem record_Download) {
        Record_Download = record_Download;
    }

    void setRecord_Upload(JMenuItem record_Upload) {
        Record_Upload = record_Upload;
    }

    @Override
    // 点击菜单按钮弹出的界面窗口信息
    public void actionPerformed(ActionEvent e) {
        // 建立Main_GUI的对象  直接调用Main_GUI里面的界面函数对象
        Main_GUI main_gui = new Main_GUI();
        if (e.getSource() == Change_User){
            main_gui.Change_GUI();
        } else if (e.getSource() == Delete_User){
            main_gui.Change_GUI();
        } else if (e.getSource() == Add_User){
            main_gui.Change_GUI();
        } else if (e.getSource() == Record_Upload){
//            try {
                main_gui.File_GUI();
//            } catch (Exception e1){
//                JOptionPane.showMessageDialog(null, "\t\tInformation is wrong!", "Tip", JOptionPane.WARNING_MESSAGE);
//                e1.printStackTrace();
//            }
        } else if (e.getSource() == Record_Download){
            Browser_GUI browser_gui = new Browser_GUI();
            browser_gui.File_GUI();
        } else if (e.getSource() == Change_Information){
            main_gui.Change_Information();
        }
    }
}
