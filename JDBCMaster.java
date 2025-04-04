package mahmud.com;

import java.sql.*;

public class JDBCMaster {
    static Connection con = null;
    static Statement st = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    static UserInput userInput =new UserInput();
    public static void main(String[] args) {
        DatabaseProperties databaseProperties = new DatabaseProperties();

        // Encrypt the password
        //String encryptedPassword = AESUtil.encrypt(password);
        try {
            //1. Load the Driver
            Class.forName("org.postgresql.Driver");
            //2. Establish the Connection
            databaseProperties.inputDB();
            con= DriverManager.getConnection(databaseProperties.jdbcURL,databaseProperties.dbUser,databaseProperties.dbPassword);
            createTables(con);
            insertUser(con);
            insertProduct(con);
            fetchUsers(con);
            fetchProduct(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //7. Close the Resources
        finally {
            try {
                if(st != null)
                    st.close();
                if(rs != null)
                    rs.close();
                if(con != null)
                    con.close();
                if(pst != null)
                    pst.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    //T0 CREATE TABLE
    public static void createTables(Connection conn) throws SQLException{
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (id serial primary key, username varchar(25), password varchar(100))";
        String createProductTable = "CREATE TABLE IF NOT EXISTS product (id serial primary key, name varchar(25), price NUMERIC)";
            st = con.createStatement();
            st.executeUpdate(createUserTable);
            st.executeUpdate(createProductTable);
            System.out.println("Tables created (if not existed).");
    }
    //TO INSERT DATA INTO USERS
    public static void insertUser(Connection conn) throws Exception {
        String sqlUserInsert = "INSERT INTO USERS(username,password) values(?,?)";
        pst = con.prepareStatement(sqlUserInsert);
        userInput.inputUser();
        pst.setString(1,userInput.getUsername());
        pst.setString(2,AESUtil.encrypt(userInput.getPassword()));
        int rows = pst.executeUpdate();
        System.out.println("User Inserted: "+(rows ==1));
    }
    //TO INSERT DATA INTO PRODUCT
    public static void insertProduct(Connection conn) throws Exception {
        String sqlProductInsert = "INSERT INTO product(name,price) values(?,?)";
        pst = con.prepareStatement(sqlProductInsert);
        userInput.inputProduct();
        pst.setString(1,userInput.getProductName());
        pst.setDouble(2,userInput.getProductPrice());
        int rows = pst.executeUpdate();
        System.out.println("Product Inserted: "+(rows ==1));
    }
    //FETCH DATA FROM USERS
    public static void fetchUsers(Connection con) throws Exception {
        String fetchUser = "SELECT * FROM USERS";
        st = con.createStatement();
        rs = st.executeQuery(fetchUser);
        while(rs.next()){
            System.out.println(rs.getInt("id")+": "+rs.getString("username")+": "+AESUtil.decrypt(rs.getString("password")));
        }
    }
    //FETCH DATA FROM PRODUCT
    public static void fetchProduct(Connection conn) throws SQLException{
        String fetchProduct = "SELECT * FROM PRODUCT";
        st = con.createStatement();
        rs = st.executeQuery(fetchProduct);
        while(rs.next()){
            System.out.println(rs.getInt("id")+": "+rs.getString("name")+": "+rs.getString("price"));

        }
    }
}
