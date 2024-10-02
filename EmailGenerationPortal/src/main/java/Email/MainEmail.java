package Email;

import java.util.Scanner;

public class MainEmail {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                  Welcome to our Email Generation Portal                         ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please enter your first Name:-");
        String fname=sc.nextLine();
        System.out.println("Please enter your last Name:-");
        String lname=sc.nextLine();
        System.out.println();
        childEmail em1=new childEmail(fname,lname);
    }
}