package mahmud.com;

import java.sql.*;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String jdbcURL = "jdbc:postgresql://localhost:5432/javaDB";
        String dbUser = "postgres";
        String dbPassword = "admin";

        UserInput userInput = new UserInput();

        try {
            //1. Load the Driver
            Class.forName("org.postgresql.Driver");
            //2. Establish the Connection
            con=DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            //3. Prepare the SQL Statement
            String createTable = "CREATE TABLE IF NOT EXISTS users(username varchar(25), password varchar(25))";
            String insertData ="INSERT INTO USERS VaLUES(?,?)";
            //4. Create the JDBC Statement
            st = con.createStatement();
            //5. Submit SQL Statement to Database using JDBC Statement
            st.executeUpdate(createTable);
            pst = con.prepareStatement(insertData);
            userInput.input();  // Call to collect input
            pst.setString(1, userInput.getUsername());
            pst.setString(2, userInput.getPassword());

            int x = pst.executeUpdate();
            //6. Process the Result
            if( x ==1) {
                System.out.println("Inserted");
            }else {
                System.out.println("Not Inseted");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}