package eu.noelroger;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //----
        System.out.print("Entrez le montant à emprunter: ");
        int principal = scanner.nextInt();
        System.out.println("Vous voulez emprunter " + principal + " euros.");
        System.out.print("Entrez le taux ANNUEL en %: ");
        float annualRate = scanner.nextFloat();
//        System.out.println(annualRate);
        System.out.println("Le taux annuel est de " + annualRate + "%.");
        System.out.println("Le taux mensuel sera de " + (annualRate/100/12) + ".");
        System.out.print("Entrez le nombre de mensualités: ");
        int numberOfPayments = scanner.nextInt();
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
