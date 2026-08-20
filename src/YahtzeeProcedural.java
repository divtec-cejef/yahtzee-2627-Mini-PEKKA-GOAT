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

        //Choix des dés a relancer
        Scanner reader = new Scanner(System.in);
        System.out.println("Quel(s) dé(s) souhaiteriez vous relancer ? ");
        int n = reader.nextInt();
    }
//mode d'affichage

    //méthode d'affichage des dés au premier lancer
    public static void afficherDes(int []des ) {
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            System.out.println("Dé " + (indexDe + 1) + " : " + des[indexDe]);
        }
    }
}
