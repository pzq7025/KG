package homework;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataProcess {
    // 初始状态为true
    private static boolean connectDB = true;
    // 获取user的名字 账号  进行哈希匹配
    static Hashtable<String, User> users;
    // 获取docs的名字  账号  进行哈希匹配
    static Hashtable<String, Doc> docs;

    static {
        // 构建哈希表的静态成员变量
        users = new Hashtable<String, User>();
        users.put("Jack", new Browser("Jack", "123", "Browser"));
        users.put("Rose", new Adminstrator("Rose", "123", "Adminstrator"));
        users.put("Petry", new Operator("Petry", "123", "Operator"));
        // 构造函数完成对DB的操作
        Init();


        // 定义文件集的哈希表   以及获取时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        docs = new Hashtable<String, Doc>();
        docs.put("0001", new Doc("0001", "jack", timestamp, "Doc Source Java", "Doc.txt"));


    }

    // 调控connecttion的连接
    public static void Init(){
        // 更改connectDB的状态
        // 建立数据库的连接


        // update  the  DataBase connection status
//        if (Math.random() > 0.2){
//            connectDB = true;
//        } else {
//            connectDB = false;
//        }

    }


//    public static User searchUser(String name) throws SQLException{
//        // 判断一个对数据库的判断
//        if (!connectDB){
//            throw new SQLException("Not Connected to DB");
//        }
//        double ranVal = Math.random();
//        if (ranVal > 1000){
//            throw new SQLException("Error is excecuting Query");
//        }
//        if (users.containsKey(name)){
//            return users.get(name);
//        }
//        return null;
//    }


    // dataProcess的哈希匹配方法  进行对象调用  就可以完成对应的哈希匹配
    // 正确返回对应的对象信息 错误返回失败null
    public static User search(String name, String password) throws SQLException {
        // 添加对数据库的判断
        // 如果不能连接数据库
//        if (!connectDB){
//            throw new SQLException("Not Connection is Error!");
//        }
//
//        double ranVal = Math.random();
//        if (ranVal > 1000){
//            throw new SQLException("Error in excecuting Query!");
//        }


        if (users.containsKey(name)) {
            User temp = users.get(name);
            if ((temp.getPassword()).equals(password))
                return temp;
        }
        return null;
    }



    // 显示用户信息  与GetAll配对使用
    public static void showInfo() throws SQLException {
        // Show all balances in hash table.
        Enumeration<User> e = DataProcess.getAllUser();
        User user;
        try {
            System.out.println("Information:");
            // 通过获取的getAll获取到用户的信息
            while (e.hasMoreElements()) {
                user = e.nextElement();
                System.out.println("Name:" + user.getName() + "\tPassword:" + user.getPassword() + "\tRole:" + user.getRole());
            }
        } catch (Exception e1){
            e1.printStackTrace();
        }

    }


    // 获取枚举类
    public static Enumeration<User> getAllUser() throws SQLException{
        // 对数据库的连接做判断
//        if (!connectDB){
//            throw new SQLException("Not Connection DataBase!");
//        }
//        double ranVal = Math.random();
//        if (ranVal > 1000){
//            throw new SQLException("Error in excecuting Query");
//        }
        // 将信息返还给调用的函数
        Enumeration<User> e  = users.elements();
        return e;
    }


    // 更新数据
    public static boolean update(String name, String password, String role) throws SQLException{
        // 添加一个对数据库的判断
        User user;
//        if (!connectDB){
//            throw new SQLException("Not Connection DataBase!");
//        }
//
//        double ranval = Math.random();
//        if (ranval > 1000){
//            throw new SQLException("Error in excecuting Update");
//        }

        if (users.containsKey(name)) {
            if (role.equalsIgnoreCase("administrator"))
                user = new Adminstrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);
            users.put(name, user);
            return true;
        }else {
            return false;
        }
    }


    // 插入数据
    public static boolean insert_User(String name, String password, String role) throws SQLException{
        // 添加一个对数据库的判断
        User user;
//        if (!connectDB){
//            throw new SQLException("Not Connected to Database");
//        }
//        double ranValue = Math.random();
//        if (ranValue > 1000){
//            throw new SQLException("Error in excecuting Insert");
//        }

        if (users.containsKey(name))
            return false;
        else{
            if (role.equalsIgnoreCase("administrator"))
                user = new Adminstrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);

            users.put(name, user);
            return true;
        }
    }


    // 删除用户
    public static boolean delete(String name) throws SQLException{
        // 添加一个对数据库的操作
//        if (!connectDB){
//            throw new SQLException("Not Connected to DataBase");
//        }
//
//        double ranValue = Math.random();
//        if (ranValue > 1000){
//            throw new SQLException("Error in excecuting Delete");
//        }

        if (users.containsKey(name)){
            users.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public void disconnectFromDB(){
        if (connectDB){
            // close Statement and Connection
        }
        try {
            if (Math.random() > 1000){
                throw new SQLException("Error in disconnecting DB!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connectDB = false;
        }
    }


    // 对数据库进行一个内容的查找
    // 获取的关键是获取ID信息
    public static Doc search_Doc(String ID) throws SQLException{
        // 获取的关键就是ID
        if (docs.containsKey(ID)){
            Doc temp = docs.get(ID);
            return temp;
        }
        return null;
    }


    // 对Doc进行哈希查找
    public static Enumeration<Doc> getAllDocs() throws SQLException {
        Enumeration<Doc> e = docs.elements();
        return e;
    }




    // 对Doc进行添加
    public static boolean insert_Doc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
        Doc doc;

        // 如果存在就报错
        if (docs.containsKey(ID)) {
            return false;
            // 不存在 建立新的对象 以及对象内容放入Hash Table
        } else {
            doc = new Doc(ID, creator, timestamp, description, filename);
            docs.put(ID, doc);
            return true;
        }
    }

    // 显示Document
//    public static void showDoc(){
//        // Show all balances in hash table.
//        Enumeration names;
//        names = docs.keys();
//        String str;
//        while(names.hasMoreElements()) {
//            str = (String) names.nextElement();
//            System.out.println(str + ": " +
//                    docs.get(str));
//        }
//    }


}
