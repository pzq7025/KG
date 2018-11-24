package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 建立的登录界面的响应事件函数
 */

public class Login_GUI_Plugin extends conn_db implements ActionListener {
    private JTextField Name_Text, Password_Text;
    private JButton Ok_Button, Cancel_Button;
    private JFrame jFrame;


    // 接收响应的参数
    void setName_Text(JTextField name_Text) {
        Name_Text = name_Text;
    }

    void setPassword_Text(JTextField password_Text) {
        Password_Text = password_Text;
    }

    void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    void setOk_Button(JButton ok_Button) {
        Ok_Button = ok_Button;
    }

    void setCancel_Button(JButton cancel_Button) {
        Cancel_Button = cancel_Button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Name_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t姓名不能为空", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (Password_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t密码不能为空", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (e.getSource() == Ok_Button){
            try {
//                int ju = JOptionPane.showConfirmDialog(null, "\t\t确定登录", "Tip", JOptionPane.OK_CANCEL_OPTION);
//                if (ju == 0) {
                User user = DataProcess.search(Name_Text.getText(), Password_Text.getText());
                // 对信息进行检索  不为空继续 为空报错
                    if (user != null) {
                        user.GUI();
                        jFrame.dispose();
//                    }
                } else {
                    JOptionPane.showMessageDialog(null, "\t\t登录信息错误\n\t\t请重试", "WARNINGING", JOptionPane.WARNING_MESSAGE);
                    // 重置输入密码
                    Name_Text.setText("");
                    Password_Text.setText("");
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "\t\t信息错误\n\t\t请重试", "WARNING", JOptionPane.WARNING_MESSAGE);
                Name_Text.setText("");
                Password_Text.setText("");
                e1.printStackTrace();
            }
        } else if (e.getSource() == Cancel_Button){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t是否退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0){
                System.exit(0);
            }
        }
    // 监听对象的括号
    }
}
