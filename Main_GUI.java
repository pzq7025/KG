package homework;

/**
 * 所有需要的GUI全在这里
 */

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;

public class Main_GUI extends JFrame {
    // 设置成public方便修改其属性值
    private JMenuBar Total;
    public JMenu User_Manage, Record_Manage, Personal_Manage;
    private JMenuItem Change_User, Delete_User, Add_User, Record_Download, Change_Information;
    // 满足多态的需求
    public JMenuItem Record_Upload;

    // 将对象传递给对应的配置文件
    Main_GUI_Plugin mop;


    void init(){
        // 建立JMenuBar的按钮 在Swing中有预留 用setJMenuBar使其预留出空间
        Total = new JMenuBar();

        // 实例化Menu菜单选项
        User_Manage = new JMenu("用户管理");
        Record_Manage = new JMenu("档案管理");
        Personal_Manage = new JMenu("个人信息管理");

        // 实例化MenuItem的选项
        Change_User = new JMenuItem("修改用户");
        Delete_User = new JMenuItem("删除用户");
        Add_User = new JMenuItem("新增用户");
        Record_Upload = new JMenuItem("档案上传");
        Record_Download = new JMenuItem("档案下载");
        Change_Information = new JMenuItem("个人信息修改");

        // 使其可视化
        setJMenuBar(Total);

        // 添加到Total中
        Total.add(User_Manage);
        Total.add(Record_Manage);
        Total.add(Personal_Manage);

        // 添加到User_Manage中
        User_Manage.add(Change_User);
        User_Manage.add(Delete_User);
        User_Manage.add(Add_User);

        // 添加到Record中
        Record_Manage.add(Record_Upload);
        Record_Manage.add(Record_Download);

        // 添加到Personal_Manage中
        Personal_Manage.add(Change_Information);


        // 建立监听传递监听
        mop = new Main_GUI_Plugin();


        // 建立对应的监听对象
        Change_User.addActionListener(mop);
        Delete_User.addActionListener(mop);
        Add_User.addActionListener(mop);
        Record_Upload.addActionListener(mop);
        Record_Download.addActionListener(mop);
        Change_Information.addActionListener(mop);


        // 将监听对象进行传递
        mop.setChange_User(Change_User);
        mop.setDelete_User(Delete_User);
        mop.setAdd_User(Add_User);
        mop.setRecord_Download(Record_Download);
        mop.setRecord_Upload(Record_Upload);
        mop.setChange_Information(Change_Information);



        ImageIcon image=new ImageIcon("F:\\exploitation\\codes\\java_codes_project\\new_project\\src\\signer\\time5.jpg");
        JLabel logo_label = new JLabel(image);

        logo_label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logo_label, Integer.valueOf(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true代表透明  透明之后的gui界面是看不到背景图像的
        jp.setOpaque(false);


        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBounds(250, 200, 500, 350);
    }



    // 点击用户管理界面弹出的GUI窗口
    void Change_GUI(){
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBounds(250, 200, 500, 350);
    }


    //  点击文件管理菜单弹出的GUI界面
    private JMenuBar File_GUI;
    public JMenu File_Up, File_Down;
    void File_GUI(){
        File_GUI = new JMenuBar();

        File_Up = new JMenu("上传文件");
        File_Down = new JMenu("文件下载");

        // 设置JMenuBar可见
        setJMenuBar(File_GUI);

        // 在JMenuBar中添加JMenu 按照添加顺序进行添加
        File_GUI.add(File_Down);
        File_GUI.add(File_Up);



        JPanel panel = new JPanel();
        String [] column_Name = {"档案号", "上传者", "时间", "文件名", "概述"};
        Object [][] row_Data = {
                {"1", "2", "3", "4", "5"}
        };
        // 获取表的数据和表头名
        JTable table = new JTable(row_Data, column_Name);


        table.setForeground(Color.BLACK);                            // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));   // 字体样式
        table.setSelectionBackground(Color.BLUE);                    // 选中后字体颜色
        table.setForeground(Color.LIGHT_GRAY);                       // 选中后字体背景
        table.setGridColor(Color.GRAY);                              // 网格颜色

        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));      // 设置表头名称字样
        table.getTableHeader().setForeground(Color.GREEN);          // 设置表头名称颜色
        table.getTableHeader().setResizingAllowed(false);           // 不允许改变宽度
        table.getTableHeader().setReorderingAllowed(false);         // 不允许重新排列


        // 设置行高
        table.setRowHeight(30);

        // 第一列宽度
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小
        table.setPreferredScrollableViewportSize(new Dimension(0, 0));


        // 将表格放到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);


        // 添加滚动面板到内容面板
        panel.add(scrollPane);

        // 设置内容面板到窗口
        setContentPane(panel);

        // 设置参数
        // 窗口可见
        setVisible(true);
        // 大小不可变
        setResizable(false);
        // 设置大小及位置
        setBounds(400, 220, 450, 300);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    // 点击修改用户菜单弹出的窗口
    void Change_Information(){

    }


    public static void main(String[] args){
            Main_GUI main_gui = new Main_GUI();
            main_gui.File_GUI();
    }
}
