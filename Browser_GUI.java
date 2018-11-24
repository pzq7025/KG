package homework;

/**
 * 浏览者界面  继承于Main_Gui
 */

public class Browser_GUI extends Main_GUI {


    @Override
    void init() {
        // 先继承后改造
        super.init();
        super.setTitle("浏览者界面");
        //浏览者界面只可以下载文件  以及修改个人信息
        super.User_Manage.setEnabled(false);
        super.Record_Upload.setEnabled(false);
    }

    @Override
    void File_GUI() {
        // 先继承后改造
        super.File_GUI();
        super.File_Up.setEnabled(false);
    }
}
