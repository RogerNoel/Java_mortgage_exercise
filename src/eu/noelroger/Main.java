package eu.noelroger;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //----------
        long principal = (long) readNumber("Entrez le montant à emprunter, entre 1000 et 1 million: ", 1000, 1_000_000);
        float annualRate = (float) readNumber("Entrez le taux ANNUEL (entre 0 et 13) en %: ", 0, 13);
        int numberOfPayments = (int) readNumber("Entrez le nombre de mensualités, entre 1 et 360 inclus: ", 1, 360);
        double refund = calculateRefund (principal, annualRate, numberOfPayments);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedRefund = formatter.format(refund);
        System.out.println("Le montant de la mensualité s'élèvera à " + formattedRefund);
        System.out.println("Le remboursement total s'élèvera à " + (formatter.format(numberOfPayments * refund)));
        scanner.close();
    }

    public static double readNumber(String prompt, Integer min, Integer max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextDouble();
                if (value >= min && value < max)
                    break;
            System.out.print("ATTENTION: la valeur doit se situer entre " + min + " et " + max);
        }
        scanner.close();
        return value;
    }

    public static double calculateRefund (Long principal, Float annualRate, Integer numberOfPayments){
        double monthlyRate = annualRate/100/12;
        double numerator = (Math.pow((1+monthlyRate), numberOfPayments))*monthlyRate;
        double denominator = (Math.pow((1+monthlyRate), numberOfPayments))-1;
        double refund = principal*(numerator/denominator);
        return refund;
    }
}
