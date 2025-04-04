package mahmud.com;

import java.util.Scanner;

public class DatabaseProperties {
    String jdbcURL = "jdbc:postgresql://localhost:5432/javaDB";
    String dbUser;
    String dbPassword;
    Scanner sc = new Scanner(System.in);

    public void inputDB() {
        System.out.println("Enter your Database UserName: ");
        dbUser = sc.nextLine();
        System.out.println("Enter your Database password: ");
        dbPassword = sc.nextLine();
    }
    public String getDBUser() {
        return dbUser;
    }
    public String getDBPassword() {
        return dbPassword;
    }
}
