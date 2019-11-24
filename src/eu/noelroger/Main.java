package eu.noelroger;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //----
        int principal = 0;
        while (principal < 1000 || principal > 1000000) {
            System.out.print("Entrez le montant à emprunter, entre 1000 et 1 million: ");
            principal = scanner.nextInt();
        }
        System.out.println("Vous voulez emprunter " + principal + " euros.");
        float annualRate = 0;
        do {
            System.out.print("Entrez le taux ANNUEL (entre 0 et 13) en %: ");
            annualRate = scanner.nextFloat();
        } while (annualRate > 13);
        System.out.println("Le taux annuel est de " + annualRate + "%.");
        System.out.println("(Taux mensuel: " + (annualRate/100/12) + ".)");
        int numberOfPayments = 0;
        while (numberOfPayments == 0 || numberOfPayments > 30) {
            System.out.print("Entrez le nombre de mensualités, entre 1 et 30 inclus: ");
            numberOfPayments = scanner.nextInt();
        }
        System.out.println("Vous avez choisi de payer en " + numberOfPayments + " mois.");
        // calcul du numérateur
        double monthlyRate = (annualRate/100/12);
        double numerator = (Math.pow((1+monthlyRate), numberOfPayments))*monthlyRate;
        // calcul du dénominateur
        double denominator = (Math.pow((1+monthlyRate), numberOfPayments))-1;
        double refund = principal*(numerator/denominator);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedRefund = formatter.format(refund);
        System.out.println("Le montant de la mensualité s'élèvera à " + formattedRefund);
        scanner.close();
    }
}
