package mahmud.com;

import java.util.Scanner;

public class UserInput {
    private String username;
    private String password;
    private String name;
    private double price;
    Scanner sc = new Scanner(System.in);

    public void inputUser() {
        System.out.println("Enter your username: ");
        username = sc.nextLine();
        System.out.println("Enter your password: ");
        password = sc.nextLine();
    }
    public void inputProduct() {
        System.out.println("Enter Product Name: ");
        name = sc.nextLine();
        System.out.println("Enter Product Price: ");
        price = sc.nextDouble();
    }

    public String getProductName(){
        return name;
    }
    public double getProductPrice(){
        return price;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
