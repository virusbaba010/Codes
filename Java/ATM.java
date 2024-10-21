import java.util.Scanner;

public class ATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t~~VIRUS Bank~~");
        System.out.println("\t\tWelcome to the ATM Machine");
        System.out.println("PIN = 2580");

        int pin = 2580;
        int bal = 5000;
        int attempt = 0;

        System.out.print("Please Enter your PIN: ");
        int epin = scanner.nextInt();

        boolean atm = (epin == pin);

        if (!atm) {
            System.out.println("Invalid PIN Please try again later");
        }

        while (atm) {
            System.out.println("\t\t-----------");
            System.out.println("\t\t VIRUS ATM ");
            System.out.println("\t\t-----------");

            System.out.println("Main Menu:");
            System.out.println("1> Check Balance");
            System.out.println("2> Deposit Money");
            System.out.println("3> Withdraw Money");
            System.out.println("4> Exit");

            System.out.print("Enter choice: ");
            int c = scanner.nextInt();

            if (c == 1) {
                System.out.println("Your Balance is " + bal);
            } else if (c == 2) {
                System.out.print("Enter Money you want to Deposit: ");
                int dep = scanner.nextInt();
                bal += dep;
                System.out.println(dep + " You added successfully.");
                System.out.println("You have " + bal + " now in your account.");
            } else if (c == 3) {
                System.out.print("Enter Money you want to Withdraw: ");
                int wit = scanner.nextInt();
                if (bal < wit) {
                    System.out.println("You don't have sufficient balance in account.");
                } else {
                    System.out.println("Successfully withdraw " + wit + "from account.");
                    bal -= wit;
                    System.out.println("You have " + bal + " now in your account.");
                }
            } else if (c == 4) {
                System.out.println("Thank you for using our VIRUS ATM.");
                System.out.println("Exiting...");
                atm = false;
            } else {
                System.out.println("Invalid Choice. Please enter correct option");
                attempt++;
                if (attempt == 3) {
                    atm = false;
                    System.out.println("You have maximum attempt choosed.");
                }
            }
        }

        scanner.close();
    }
}