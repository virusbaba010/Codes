import java.util.*;
class ATM {
    //fix current amount is Null
    static float camt=0;   
    static boolean flag = true; 

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int amt, x;

        do{
            System.out.println("Press 1 for Check Balance ");
            System.out.println("Press 2 for Deposit Money ");
            System.out.println("Press 3 for Withdraw Money ");
            System.out.println("Press 4 for Cancel Transaction ");
            System.out.print("Enter Choice : ");
            x=scan.nextInt();
            scan.nextLine();
            

            switch (x) {
                case 1:
                    System.out.println("Current Amount in Your Account is " + camt);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit : ");
                    amt = scan.nextInt();
                    scan.nextLine();
                    if (amt > 0) {
                        camt += amt;
                        System.out.println("Transaction Completed.");
                        System.out.println("Current Amount in Your Account is "+camt);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw : ");
                    amt = scan.nextInt();
                    scan.nextLine();
                    if (amt>0 && camt>=amt) {
                        camt=camt-amt;
                        System.out.println("Transaction Completed.");
                        System.out.println("Current Amount in Your Account is "+camt);
                    } else {
                        System.out.println("Insufficient Balance or Invalid Amount.");
                    }
                    break;

                case 4:
                    System.out.println("Transaction Cancelled. Thank You for using VirusBaba ATM.");
                    flag =false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (flag) {
                System.out.println("Want to do more transactions (yes/no) : ");
                String l = scan.nextLine();
                
                if (!l.equalsIgnoreCase("yes")) {
                    break;   
                }
            }

        }
        while(flag);

        scan.close();
    }
}
