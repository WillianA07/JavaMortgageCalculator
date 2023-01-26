import java.util.Scanner;
import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        System.out.println("So you are going to buy a house and want to know your monthly payments.");
        System.out.println("Please answer the following questions and we will figure that out for you.");

        System.out.println("What is the total loan amount?");
        Scanner loanAmount = new Scanner(System.in);
        double TotalLoanAmount = loanAmount.nextDouble();

        System.out.println("What is your interest rate?");
        Scanner rate = new Scanner(System.in);
        double InterestRate = rate.nextDouble();

        System.out.println("Is your interest rate monthly or annually?");
        Scanner Type = new Scanner(System.in);
        String InterestType = Type.nextLine();

        if (InterestType.equals("monthly"))
            InterestRate = InterestRate / 100;
        else if (InterestType.equals("annually"))
            InterestRate = ((InterestRate / 100) / 12);

        System.out.println("How many years or months is your term?");
        Scanner term = new Scanner(System.in);
        String TermLength = term.nextLine();
        double monthlyTerm = TermType(TermLength);

        System.out.println("Are you putting a down payment?");
        Scanner downAnswer = new Scanner(System.in);
        String DownPaymentAns = downAnswer.nextLine();
        double DownPaymentAmount = 0;

        if (DownPaymentAns.equals("Yes")) {
            System.out.println("What is the amount of the down payment");
            Scanner DownAmt = new Scanner(System.in);
                DownPaymentAmount = DownAmt.nextDouble();
        }

        double monthlyPayments = CalculateAmount(TotalLoanAmount, InterestRate, monthlyTerm, DownPaymentAmount);

        String answer = String.format("For a loan of $%.2f and a %f%% interest rate. Your monthly payments will be $%.2f",
                TotalLoanAmount, InterestRate, monthlyPayments);
        System.out.println(answer);
    }

    private static double CalculateAmount(double TotalLoanAmount, double InterestRate, double monthlyTerm, double DownPaymentAmount) {
        // MonthlyAmount = TotalLoanAmount - DownPaymentAmount [ InterestRate (1 + InterestRate)^MonthlyTerm ] / [ (1 + InterestRate)^MonthlyTerm – 1]

        double monthlyAmount = ((TotalLoanAmount - DownPaymentAmount) * ((InterestRate * (Math.pow((1.0 + InterestRate) , monthlyTerm))) / ((Math.pow((1.0 + InterestRate) , monthlyTerm)) - 1)));
        return monthlyAmount;
    }

    private static double TermType(String TermLength) {
        char[] chars = TermLength.toCharArray();
        StringBuilder TermAmount = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                TermAmount.append(c);
            }
        }
        String yearType = "years";
        int i = TermLength.indexOf(yearType);
        if (i > 0)
            return (Double.parseDouble((TermAmount.toString()))) * 12;
        else
            return Double.parseDouble((TermAmount.toString()));
    }
}