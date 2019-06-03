package emailapp;

import java.util.Optional;
import java.util.Scanner;

public class EmailApp {

    public static void main (String[] args) {
        System.out.println("===> Create new employee <===");

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("First name:");
            String firstName = in.nextLine();

            System.out.println("Last name:");
            String lastName = in.nextLine();

            System.out.println("Private email:");
            String privateEmail = in.nextLine();

            System.out.println("Please choose a department for " + firstName + " " + lastName);

            int department = 0;
            boolean condition = true;
            while (condition) {
                System.out.println("1 for Sales");
                System.out.println("2 for Development");
                System.out.println("3 for Accounting");
                System.out.println("Enter department code:");

                department = Optional.ofNullable(in.nextLine()).map(EmailApp::tryParse).orElse(0);
                if (condition = department < 1 || department > 3) {
                    System.out.println("Please enter a valid department number!");
                }
            }

            EmailAccount emailAccount = new EmailAccount(firstName, lastName, department, privateEmail);
            System.out.println(emailAccount);
        }
    }

    private static Integer tryParse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
