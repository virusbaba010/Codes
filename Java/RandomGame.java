import java.util.*;

class RandomGame{
    static boolean flag;
    void Luck(int a, int c){
        Random rand = new Random();
        
        int rn = rand.nextInt(100)+1;
        if (a>rn){
            System.out.println("In "+c+" attempts");
            System.out.println("Random Number : "+rn +" is Too low");
        }
        else if(a<rn){
            System.out.println("In "+c+" attempts");
            System.out.println("Random Number : "+rn +" is Too high");
        }
        else if(rn==a){
            System.out.println("In "+c+" attempts");
            System.out.println("Random Number : "+rn +" Congrats you choosed a correct number");
            flag = false;
        }
        if(c==5){
            System.out.println("Not Win");
            System.out.println("You have completed your 5 chances. \nThank You!!");
            flag = false;
        }
    }
    public static void main(String [] args){
        System.out.println("\t\t<<<<<\tVirusBaba Gaming\t>>>>>");
        Scanner scan = new Scanner(System.in);
        int a,c=1;
        
        flag = true;
        RandomGame gm = new RandomGame();
        do{
            System.out.print("Enter a Number to check Luck:");
            a = scan.nextInt();
            gm.Luck(a,c);
            c++;
        }
        while(flag);
        
        scan.close();
    }
}