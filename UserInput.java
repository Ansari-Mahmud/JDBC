package mahmud.com;

import java.util.Scanner;

public class UserInput {
    private String username;
    private String password;
    Scanner sc = new Scanner(System.in);

    public void input() {
        System.out.println("Enter your username: ");
        username = sc.nextLine();
        System.out.println("Enter your password: ");
        password = sc.nextLine();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
