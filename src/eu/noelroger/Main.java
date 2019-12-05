package eu.noelroger;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Variables
        long principal = 0;
        float annualRate = 0;
        int numberOfPayments = 0;
        //----------
        while (true) {
            System.out.print("Entrez le montant à emprunter, entre 1000 et 1 million: ");
            principal = scanner.nextLong();
            if (principal >= 1000 && principal < 1000000)
                break;
            System.out.print("ATTENTION: le montant à emprunter doit se situer entre 1000 et 1 million: ");
        }
        do {
            System.out.print("Entrez le taux ANNUEL (entre 0 et 13) en %: ");
            annualRate = scanner.nextFloat();
        } while (annualRate > 13);
        while(true) {
            System.out.print("Entrez le nombre de mensualités, entre 1 et 60 inclus: ");
            numberOfPayments = scanner.nextInt();
            if (numberOfPayments <= 60 && numberOfPayments > 0)
                break;
            System.out.print("ATTENTION: le nombre de mensualités doit se situer entre 1 et 60 inclus: ");
        }
        calculateRefund(principal, annualRate, numberOfPayments);
        calculateBalance(principal, annualRate, numberOfPayments);
        scanner.close();
    }

    public static double calculateRefund(Long principal, float annualRate, int  numberOfPayments) {
        Scanner scanner = new Scanner(System.in);
        double monthlyRate = annualRate/100/12;
        double numerator = (Math.pow((1+monthlyRate), numberOfPayments))*monthlyRate;
        double denominator = (Math.pow((1+monthlyRate), numberOfPayments))-1;
        double refund = principal*(numerator/denominator);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedRefund = formatter.format(refund);
        System.out.println("Le montant de la mensualité s'élèvera à " + formattedRefund);
        System.out.println("Le remboursement total s'élèvera à " + (formatter.format(numberOfPayments * refund)));
        scanner.close();
        return refund;

    }
    public static void calculateBalance(Long principal, Float annualRate, Integer numberOfPayments ) {
        double monthlyRate = annualRate/100/12;
        int performedPayments = 0;
        while (performedPayments <= numberOfPayments ) {
            double numerator = (Math.pow((1+monthlyRate), numberOfPayments))-(Math.pow((1+monthlyRate), performedPayments));
            double denominator = (Math.pow((1+monthlyRate), numberOfPayments))-1;
            double balance = principal*(numerator/denominator);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String formattedBalance = formatter.format(balance);
            System.out.println("Après le paiement n° " + performedPayments + " le solde sera de " + formattedBalance);
            performedPayments++;
        }
    }
}