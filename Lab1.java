<<<<<<< HEAD
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the database Password: ");
        String dbPassword = sc.nextLine();
        try {
            //1. Load the Driver
            Class.forName("org.postgresql.Driver");
            //2. Establish the Connection
            con=DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            //3. Prepare the SQL Statement
            String createTable = "CREATE TABLE IF NOT EXISTS users(username varchar(25), password varchar(25))";
            System.out.println("Enter the Username: ");
            String name = sc.nextLine();
            System.out.println("Enter the Password: ");
            String password = sc.nextLine();
            String insertData ="INSERT INTO USERS VaLUES(?,?)";
            //4. Create the JDBC Statement
            st = con.createStatement();
            //5. Submit SQL Statement to Database using JDBC Statement
            st.executeUpdate(createTable);
            pst = con.prepareStatement(insertData);
            pst.setString(1, name);
            pst.setString(2, password);
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
=======
package com.mahmud.jlc;

import java.sql.*;

public class Lab1 {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			//1. Load the Driver
			Class.forName("org.postgresql.Driver");
			//2. Establish the Connection
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaProgrmaming", "postgres", "admin");
			//3. Prepare the SQL Statement
			String sql ="insert into product values(3,'Grapes',30)";
			//4. Create the JDBC Statement
			st = con.createStatement();
			//5. Submit SQL Statement to Database using JDBC Statement
			int x = st.executeUpdate(sql);
			//6. Process the Result
			if( x ==1) {
				System.out.println("Inserted");
			}else {
				System.out.println("Not Inseted");
			}
		} catch (SQLException e) {
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
>>>>>>> 07cffc3533c1c1b0e35f915f526f1433a159857e
