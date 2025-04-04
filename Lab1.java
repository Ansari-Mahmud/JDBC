package mahmud.com;

import java.sql.*;

public class Lab1 {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String jdbcURL = "jdbc:postgresql://localhost:5432/javaDB";
        String user = "postgres";
        String password = "admin";
        try {
            //1. Load the Driver
            Class.forName("org.postgresql.Driver");
            //2. Establish the Connection
            con=DriverManager.getConnection(jdbcURL, user, password);
            //3. Prepare the SQL Statement
            String createTable = "CREATE TABLE IF NOT EXISTS users(username varchar(25), password varchar(25))";
            String insertData ="INSERT INTO USERS VaLUES('Student','Programmer@123')";
            //4. Create the JDBC Statement
            st = con.createStatement();
            //5. Submit SQL Statement to Database using JDBC Statement
            st.executeUpdate(createTable);
            int x = st.executeUpdate(insertData);
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