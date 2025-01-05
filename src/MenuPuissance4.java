import java.util.ArrayList;
import java.util.Scanner;

public class MenuPuissance4 {
    public static void afficherMenu() {
        char joueur1 = 'O';
        char joueur2 = 'X';
        int LIGNES = 6;
        int COLONNES = 7;
        Scanner scanner = new Scanner(System.in);
        int choix;
        ArrayList<String> liste = new ArrayList<>();
        char[][] plateau = new char[LIGNES][COLONNES];


        do {
            System.out.println("1) Joueur contre Joueur");
            System.out.println("2) Joueur contre Ordinateur Facile");
            System.out.println("3) Joueur contre Ordinateur Normal");
            System.out.println("4) Joueur contre Ordinateur Difficile");
            System.out.println("5) Quitter");

            System.out.print("Choisissez un menu : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    liste.clear();
                    System.out.println("Saisir le pseudo du joueur 1 (entre 2 et 20 caractères) : ");
                    String pseudo1;
                    do {
                        pseudo1 = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pseudo1, true));
                    System.out.println("Saisir le pseudo du joueur 2 (entre 2 et 20 caractères) : ");
                    String pseudo2;
                    do {
                        pseudo2 = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pseudo2, true));
                    System.out.println();

                    MethodePuissance4.initialiserPlateau(plateau);
                    MethodePuissance4.afficherPlateau(plateau);
                    MethodePuissance4.jouerPartie(plateau, joueur1, joueur2, liste);
                    MethodePuissance4.plateauPlein(plateau);
                break;


                case 2:
                    liste.clear();
                    System.out.println("Saisir votre pseudo (entre 2 et 20 caractères) : ");
                    String pseudoJ1;
                    do {
                        pseudoJ1 = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pseudoJ1, true));
                    System.out.println("Saisir le pseudo du bot (entre 2 et 20 caractères) : ");
                    String pseudoBot;
                    do {
                        pseudoBot = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pseudoBot, true));
                    System.out.println();

                    MethodePuissance4.initialiserPlateau(plateau);
                    MethodePuissance4.afficherPlateau(plateau);
                    MethodePuissance4.jouerPartieavecbotfacile(plateau, joueur1, joueur2, liste);
                    System.out.println();
                break;

                case 3:
                    liste.clear();
                    char iaJeton = 'X';
                    char joueurJeton = 'O';
                    System.out.println("Saisir votre pseudo (entre 2 et 20 caractères) : ");
                    String pJ1;
                    do {
                        pJ1 = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pJ1, true));
                    System.out.println("Saisir le pseudo du bot (entre 2 et 20 caractères) : ");
                    String pBot;
                    do {
                        pBot = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, pBot, true));
                    System.out.println();

                    MethodePuissance4.initialiserPlateau(plateau);
                    MethodePuissance4.afficherPlateau(plateau);
                    MethodePuissance4.jouerPartieAvecBotnormale(plateau, joueurJeton, iaJeton, liste);
                    System.out.println();
                break;

                case 4:
                    liste.clear();
                    char iaJetonD = 'X';
                    char joueurJetonD = 'O';
                    System.out.println("Saisir votre pseudo (entre 2 et 20 caractères) : ");
                    String J1;
                    do {
                        J1 = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, J1, true));
                    System.out.println("Saisir le pseudo du bot (entre 2 et 20 caractères) : ");
                    String bot;
                    do {
                        bot = scanner.next();
                    } while (!MethodePuissance4.ajouterPseudo(liste, bot, true));
                    System.out.println();

                    MethodePuissance4.initialiserPlateau(plateau);
                    MethodePuissance4.afficherPlateau(plateau);
                    MethodePuissance4.jouerPartieAvecBotdifficile(plateau, joueurJetonD, iaJetonD, liste);
                    System.out.println();
                break;

                case 5:
                    System.out.println("Au revoir ! ");
                break;

                default:
                    System.out.println("Option invalide : veuillez réessayer");
            }
        } while(choix != 5);
    }
}
