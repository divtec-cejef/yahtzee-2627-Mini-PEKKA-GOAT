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
            if (indices[i] >= 0 && indices[i] < des.length) {
                des[indices[i]] = roll();
                System.out.println("Dé " + (indices[i] + 1) + " relancé : " + des[indices[i]]);
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

        afficherScores(des);
    }


    //méthode d'affichage des dés au premier lancer
    public static void afficherDes(int []des ) {
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            System.out.println("Dé " + (indexDe + 1) + " : " + des[indexDe]);
        }
    }

    // fonction qui compte le nombre d'occurrences de chaque face (index 1 à 6, index 0 inutilisé)
    static int[] compterOccurrences(int[] des) {
        int[] occurrences = new int[sideCount + 1];
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            occurrences[des[indexDe]]++;
        }
        return occurrences;
    }

    // fonction qui calcule le score de la combinaison "Une paire" (2 dés identiques -> 5 pts, sinon 0)
    static int calculerUnePaire(int[] occurrences) {
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] >= 2) {
                return 5;
            }
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Deux paires" (2x2 dés identiques différents -> 10 pts, sinon 0)
    static int calculerDeuxPaires(int[] occurrences) {
        int nombrePaires = 0;
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] == 2) {
                nombrePaires++;
            }
        }
        if (nombrePaires >= 2) {
            return 10;
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Brelan" (3 dés identiques -> somme des 3, sinon 0)
    static int calculerBrelan(int[] occurrences) {
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] >= 3) {
                return face * 3;
            }
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Carré" (4 dés identiques -> somme des 4, sinon 0)
    static int calculerCarre(int[] occurrences) {
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] >= 4) {
                return face * 4;
            }
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Full House" (3 identiques + 2 identiques -> 25 pts, sinon 0)
    static int calculerFullHouse(int[] occurrences) {
        boolean aTrois = false;
        boolean aDeux = false;
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] == 3) {
                aTrois = true;
            }
            if (occurrences[face] == 2) {
                aDeux = true;
            }
        }
        if (aTrois && aDeux) {
            return 25;
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Petite suite" (4 dés consécutifs -> 30 pts, sinon 0)
    static int calculerPetiteSuite(int[] occurrences) {
        for (int debut = 1; debut <= sideCount - 3; debut++) {
            boolean suiteTrouvee = true;
            for (int face = debut; face < debut + 4; face++) {
                if (occurrences[face] == 0) {
                    suiteTrouvee = false;
                }
            }
            if (suiteTrouvee) {
                return 30;
            }
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Grande suite" (5 dés consécutifs -> 40 pts, sinon 0)
    static int calculerGrandeSuite(int[] occurrences) {
        for (int debut = 1; debut <= sideCount - 4; debut++) {
            boolean suiteTrouvee = true;
            for (int face = debut; face < debut + 5; face++) {
                if (occurrences[face] == 0) {
                    suiteTrouvee = false;
                }
            }
            if (suiteTrouvee) {
                return 40;
            }
        }
        return 0;
    }

    // fonction qui calcule le score de la combinaison "Yahtzee" (5 dés identiques -> 50 pts, sinon 0)
    static int calculerYahtzee(int[] occurrences) {
        for (int face = 1; face <= sideCount; face++) {
            if (occurrences[face] == 5) {
                return 50;
            }
        }
        return 0;
    }

    // fonction qui affiche le tableau des 8 combinaisons avec leur score (0 si non atteinte)
    static void afficherScores(int[] des) {
        int[] occurrences = compterOccurrences(des);

        System.out.println("\n--- Tableau des scores ---");
        System.out.println("Une paire : " + calculerUnePaire(occurrences));
        System.out.println("Deux paires : " + calculerDeuxPaires(occurrences));
        System.out.println("Brelan : " + calculerBrelan(occurrences));
        System.out.println("Carré : " + calculerCarre(occurrences));
        System.out.println("Full House : " + calculerFullHouse(occurrences));
        System.out.println("Petite suite : " + calculerPetiteSuite(occurrences));
        System.out.println("Grande suite : " + calculerGrandeSuite(occurrences));
        System.out.println("Yahtzee : " + calculerYahtzee(occurrences));
    }
}