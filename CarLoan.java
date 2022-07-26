import java.util.Scanner;

public class CarLoan {
    /*
    calculate the monthly car payment
    a user should expect to make after
    taking out a car loan.
    */
    public static void main(String[] args) {
        Scanner scanChoice = new Scanner(System.in);
        System.out.println("Enter the amount of your car loan: ");
        int carLoan = scanChoice.nextInt();
        //int carLoan = 10000;
        //int loanLength = 3;
        System.out.println("Enter the repayment years of your loan: ");
        int loanLength = scanChoice.nextInt();// years
        //int interestRate = 5;
        System.out.println("Enter the interest rate: ");
        int interestRate = scanChoice.nextInt();// represent an % interest rate
        //int downPayment = 2000;
        System.out.println("Enter the down payment: ");
        int downPayment = scanChoice.nextInt();//represent the down payment provided by a user for this car purchase.
        if (loanLength <=0 ){
            System.out.println("Error! You must take out a valid car loan.");
        } else if (downPayment >= carLoan){
            System.out.println("The car can be paid in full.");
        } else {
            int remainingBalance = carLoan - downPayment;
            int months = loanLength * 12;
            int monthlyBalance = remainingBalance / months;
            int interest = monthlyBalance * interestRate / 100;
            int monthlyPayment = monthlyBalance + interest;
            System.out.println("The monthly installment of your loan is: " + monthlyPayment);
        }

    }
}