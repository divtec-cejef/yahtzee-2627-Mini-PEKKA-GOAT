//importer pour faire saisir des données à l'utilisateur
import java.util.Scanner;


public class YahtzeeProcedural {
    // Nombre de face des dés
    static int sideCount = 6;

    // fonction pour lancer les dés
    static int roll() {
        return (int) (Math.random() * sideCount) + 1;
    }

    // fonction qui demande les relances, récupère la saisie et retourne un tableau d'indices
    // retourne null si la saisie est vide ou vaut "0" (signal d'arrêt)
    static int[] demanderRelances(Scanner reader) {
        System.out.println("Quel(s) dé(s) souhaiteriez vous relancer ? (Séparez les d'un espace, ou laissez vide / tapez 0 pour arrêter) ");
        String Saisie = reader.nextLine().trim();

        if (Saisie.isEmpty() || Saisie.equals("0")) {
            return null;
        }

        String[] tokens = Saisie.split(" ");
        int[] indices = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            indices[i] = Integer.parseInt(tokens[i]) - 1;
        }
        return indices;
    }

    // méthode qui relance les dés aux indices donnés
    static void relancerDes(int[] des, int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            int indexDe = indices[i];
            if (indexDe >= 0 && indexDe < des.length) {
                des[indexDe] = roll();
                System.out.println("Dé " + (indexDe + 1) + " relancé : " + des[indexDe]);
            }
        }
    }

    // Point d'entrée du programme
    public static void main(String[] args) {
        int[] des = new int[5];
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            des[indexDe] = roll();
        }
        afficherDes(des);

        Scanner reader = new Scanner(System.in);
        boolean stop = true;
        // Boucle des relances possibles (max 2 relances = 3 lancers au total par manche)
        for (int compteurManche = 0; compteurManche < 2 && stop; compteurManche++) {

            System.out.println("\n Relance " + (compteurManche + 1) + " sur 2");
            int[] deRelancer = demanderRelances(reader);

            if (deRelancer == null) {
                System.out.println("Aucune relance demandée, fin de la manche.");
                stop = false;
            } else {
                System.out.println("Vous souhaitez relancer " + deRelancer.length + " dé(s) :");
                relancerDes(des, deRelancer);

                System.out.println("\nNouveau lancer complet :");
                afficherDes(des);
            }
        }
    }

    //méthode d'affichage des dés au premier lancer
    public static void afficherDes(int []des ) {
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            System.out.println("Dé " + (indexDe + 1) + " : " + des[indexDe]);
        }
    }
}