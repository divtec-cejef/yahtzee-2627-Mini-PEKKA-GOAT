public class YahtzeeProcedural {

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
    }

    public static void afficherDes(int []des ) {
        for (int indexDe = 0; indexDe < des.length; indexDe++) {
            System.out.println("Dé " + (indexDe + 1) + " : " + des[indexDe]);
        }
    }
}