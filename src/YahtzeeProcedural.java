//importer pour faire saisir des données à l'utilisateur
import java.util.Scanner;


public class YahtzeeProcedural {
    // Nombre de face des dés
    static int sideCount = 6;

    // fonction pour lancer les dés
    static int roll() {
        return (int) (Math.random() * sideCount) + 1;
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        int[] des = new int[5];
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            des[indexDe] = roll();
        }
        afficherDes(des);

        Scanner reader = new Scanner(System.in);

        // Boucle des 3 lancers possibles par manche
        for (int compteurManche = 0; compteurManche < 3; compteurManche++) {

            System.out.println("\n--- Lancer " + (compteurManche + 1) + " sur 3 ---");
            System.out.println("Quel(s) dé(s) souhaiteriez vous relancer ? (Séparez les d'un espace) ");
            int n = reader.nextInt();
            String Saisie = reader.nextLine();

            String[] deRelancer = (n + " " + Saisie.trim()).trim().split(" ");

            System.out.println("Vous souhaitez relancer " + deRelancer.length + " dé(s) :");

            for (int i = 0; i < deRelancer.length; i++) {
                int indexDe = Integer.parseInt(deRelancer[i]) - 1;
                if (indexDe >= 0 && indexDe < des.length) {
                    des[indexDe] = roll();
                    System.out.println("Dé " + (indexDe + 1) + " relancé : " + des[indexDe]);
                }
            }

            System.out.println("\nNouveau lancer complet :");
            afficherDes(des);
        }
    }

    //méthode d'affichage des dés au premier lancer
    public static void afficherDes(int []des ) {
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            System.out.println("Dé " + (indexDe + 1) + " : " + des[indexDe]);
        }
    }
}