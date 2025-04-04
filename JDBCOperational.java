package mahmud.com;

import java.sql.*;
import java.util.Scanner;

public class JDBCOperational {
    static Connection con = null;
    static Statement st = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    static UserInput userInput = new UserInput();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseProperties databaseProperties = new DatabaseProperties();

        try {
            // 1. Load the Driver
            Class.forName("org.postgresql.Driver");

            // 2. Establish the Connection
            databaseProperties.inputDB();
            con = DriverManager.getConnection(databaseProperties.jdbcURL, databaseProperties.dbUser, databaseProperties.dbPassword);

            // Loop to continuously ask the user for an operation
            while (true) {
                // Ask user what operation to perform
                System.out.println("\nSelect an operation: ");
                System.out.println("1. Create Tables");
                System.out.println("2. Insert User");
                System.out.println("3. Insert Product");
                System.out.println("4. Fetch Users");
                System.out.println("5. Fetch Products");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline character

                // Switch-case to execute the selected operation
                switch (choice) {
                    case 1:
                        createTables(con);
                        break;
                    case 2:
                        insertUser(con);
                        break;
                    case 3:
                        insertProduct(con);
                        break;
                    case 4:
                        fetchUsers(con);
                        break;
                    case 5:
                        fetchProduct(con);
                        break;
                    case 6:
                        System.out.println("Exiting program...");
                        return;  // Exit the program
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
                if (con != null) con.close();
                if (pst != null) pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create tables
    public static void createTables(Connection conn) throws SQLException {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (id serial primary key, username varchar(25), password varchar(100))";
        String createProductTable = "CREATE TABLE IF NOT EXISTS product (id serial primary key, name varchar(25), price NUMERIC)";

        st = conn.createStatement();
        st.executeUpdate(createUserTable);
        st.executeUpdate(createProductTable);
        System.out.println("Tables created (if not existed).");
    }

    // Method to insert user data
    public static void insertUser(Connection conn) throws Exception {
        String sqlUserInsert = "INSERT INTO USERS(username, password) values(?, ?)";
        pst = conn.prepareStatement(sqlUserInsert);

        userInput.inputUser();
        pst.setString(1, userInput.getUsername());
        pst.setString(2, AESUtil.encrypt(userInput.getPassword()));

        int rows = pst.executeUpdate();
        System.out.println("User Inserted: " + (rows == 1));
    }

    // Method to insert product data
    public static void insertProduct(Connection conn) throws Exception {
        String sqlProductInsert = "INSERT INTO product(name, price) values(?, ?)";
        pst = conn.prepareStatement(sqlProductInsert);

        userInput.inputProduct();
        pst.setString(1, userInput.getProductName());
        pst.setDouble(2, userInput.getProductPrice());

        int rows = pst.executeUpdate();
        System.out.println("Product Inserted: " + (rows == 1));
    }

    // Method to fetch users from the database
    public static void fetchUsers(Connection conn) throws Exception {
        String fetchUser = "SELECT * FROM USERS";
        st = conn.createStatement();
        rs = st.executeQuery(fetchUser);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + ": " + rs.getString("username") + ": " + AESUtil.decrypt(rs.getString("password")));
        }
    }

    // Method to fetch products from the database
    public static void fetchProduct(Connection conn) throws SQLException {
        String fetchProduct = "SELECT * FROM PRODUCT";
        st = conn.createStatement();
        rs = st.executeQuery(fetchProduct);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + ": " + rs.getString("name") + ": " + rs.getString("price"));
        }
    }
}
