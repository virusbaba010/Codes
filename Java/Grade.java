import java.util.*;

class Grade{
    void Perc(float t){
        float val;
        int x = (int) t;
        val = (t/500)*100;
        System.out.println("Total marks obtained "+x+" out of 500.");
        System.out.println("Percentage obtained "+val+"%");
        if (val >= 90) {
            System.out.println('A');
        } else if (val >= 80) {
            System.out.println('B');
        } else if (val >= 70) {
            System.out.println('C');
        } else if (val >= 60) {
            System.out.println('D');
        } else if (val >= 35 & val<60) {
            System.out.println('E');
        } else {
            System.out.println('F');
        }

    }
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        float e,h,m,ss,cs;
        System.out.print("Enter Marks for English : ");
        e = scan.nextFloat();
        System.out.print("Enter Marks for Hindi : ");
        h = scan.nextFloat();
        System.out.print("Enter Marks for Maths : ");
        m = scan.nextFloat();
        System.out.print("Enter Marks for Social Studies : ");
        ss = scan.nextFloat();
        System.out.print("Enter Marks for Computer Science : ");
        cs = scan.nextFloat();
        float t=e+h+m+ss+cs;

        Grade g = new Grade();
        g.Perc(t);
        scan.close();
    }
}