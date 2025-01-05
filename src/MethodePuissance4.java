import java.util.Scanner;
import java.util.ArrayList;

public class MethodePuissance4 {

    public static boolean ajouterPseudo(ArrayList<String> l, String pseudo, boolean afficherMessages) {
        if (pseudo.length() < 2 || pseudo.length() > 20) {
            if (afficherMessages) {
                System.out.println("Pseudo invalide");
            }
            return false;
        }
        if (l.contains(pseudo)) {
            if (afficherMessages) {
                System.out.println("Erreur, pseudo déjà existant");
            }
            return false;
        } else {
            l.add(pseudo);
            return true;
        }
    }




    public static void initialiserPlateau(char[][] plateau) {
        int LIGNES = 6;
        int COLONNES = 7;
        char vide = '.';

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES ; c++) {
                plateau[l][c] = vide;
            }
        }
    }

    public static void afficherPlateau(char[][] plateau) {
        final String RESET = "\u001B[0m";   // Réinitialiser les styles
        final String BOLD = "\u001B[1m";    // Texte en gras
        final String RED = "\u001B[31m";    // Couleur rouge
        final String BLUE = "\u001B[34m";   // Couleur bleue

        int LIGNES = 6;
        int COLONNES = 7;

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                char pion = plateau[l][c];
                // Applique la couleur et le style selon le type de pion
                if (pion == 'O') {
                    System.out.print("| " + BOLD + RED + pion + RESET + " ");
                } else if (pion == 'X') {
                    System.out.print("| " + BOLD + BLUE + pion + RESET + " ");
                } else {
                    System.out.print("| " + pion + " ");
                }
            }
            System.out.println("|");
        }

        // Afficher les chiffres en dessous du plateau
        for (int chiffre = 1; chiffre <= COLONNES; chiffre++) {
            System.out.print(" " + chiffre + "  ");
        }
        System.out.println();
    }

    public static boolean placerJeton(char[][] plateau, int colonne, char joueur) {
        int LIGNES = 6;
        int COLONNES = 7;
        char vide = '.';

        if ( colonne < 1 || colonne > COLONNES){
            System.out.println("Colonne invalide");
            return false;
        }
        colonne--;

        for (int i = LIGNES - 1; i >= 0; i--) { // part du bas de la colonne
            if (plateau[i][colonne] == vide) {
                plateau[i][colonne] = joueur; // place le jeton du joueur
                return true;
            }
        }
        return false; // si la colonne est pleine

    }

    public static boolean plateauPlein(char[][] plateau) {
        int LIGNES = 6;
        int COLONNES = 7;
        char vide = '.';
        for (int l = 0 ; l < LIGNES ; l++ ){
            for (int c = 0; c < COLONNES; c++) {
                if (plateau[l][c] == vide) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean verifierVictoire(char[][] plateau, char joueur) {
        int LIGNES = 6;
        int COLONNES = 7;

        // Vérification des alignements horizontaux
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES - 3; j++) {
                if (plateau[i][j] == joueur && plateau[i][j + 1] == joueur && plateau[i][j + 2] == joueur && plateau[i][j + 3] == joueur) {
                    return true;
                }
            }
        }

        // Vérification des alignements verticaux
        for (int i = 0; i < LIGNES - 3; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == joueur && plateau[i + 1][j] == joueur && plateau[i + 2][j] == joueur && plateau[i + 3][j] == joueur) {
                    return true;
                }
            }
        }

        // Vérification des alignements diagonaux (montants)
        for (int i = 3; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES - 3; j++) {
                if (plateau[i][j] == joueur && plateau[i - 1][j + 1] == joueur && plateau[i - 2][j + 2] == joueur && plateau[i - 3][j + 3] == joueur) {
                    return true;
                }
            }
        }

        // Vérification des alignements diagonaux (descendants)
        for (int i = 0; i < LIGNES - 3; i++) {
            for (int j = 0; j < COLONNES - 3; j++) {
                if (plateau[i][j] == joueur && plateau[i + 1][j + 1] == joueur && plateau[i + 2][j + 2] == joueur && plateau[i + 3][j + 3] == joueur) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void jouerPartie(char[][] plateau, char joueur1, char joueur2, ArrayList<String> liste) {
        Scanner scanner = new Scanner(System.in);
        String PremJoueur = liste.get(0);
        String DeuxJoueur = liste.get(1);
        boolean victoire = false;
        char joueurActuel = joueur1;

        while (!victoire && !MethodePuissance4.plateauPlein(plateau)) {
            String nomJoueurActuel;
            if (joueurActuel == joueur1) {
                nomJoueurActuel = PremJoueur;
            } else {
                nomJoueurActuel = DeuxJoueur;
            }

            System.out.print("C'est au tour du joueur " + nomJoueurActuel + ". Choisissez une colonne : ");
            int colonne = scanner.nextInt();


            if (MethodePuissance4.placerJeton(plateau, colonne, joueurActuel)) {
                MethodePuissance4.afficherPlateau(plateau);

                if (MethodePuissance4.plateauPlein(plateau)) {
                    break;
                }

                victoire = MethodePuissance4.verifierVictoire(plateau, joueurActuel);
                if (victoire) {
                    break;
                }
            }

            else {
                System.out.println("Colonne pleine. Veuillez choisir une autre colonne.");
                continue; // Demande au joueur de rejouer sans changer de joueur
            }

            if (!victoire) {
                if (joueurActuel == joueur1) {
                    joueurActuel = joueur2;
                } else {
                    joueurActuel = joueur1;
                }
            }
        }

        if (victoire) {
            String nomJoueurGagnant;
            if (joueurActuel == joueur1) {
                nomJoueurGagnant = PremJoueur;
            } else {
                nomJoueurGagnant = DeuxJoueur;
            }
            System.out.println("Le joueur " + nomJoueurGagnant + " a gagné !\n");
        }
        else if (MethodePuissance4.plateauPlein(plateau)) {
            System.out.println("Partie terminée avec une égalité entre " + PremJoueur + " et " + DeuxJoueur + " !\n");
        }
    }

    public static void jouerPartieavecbotfacile(char[][] plateau, char joueur1, char bots, ArrayList<String> liste) {
        Scanner scanner = new Scanner(System.in);
        String PremJoueur = liste.get(0);// Nom du joueur humain
        String DeuxJoueur = liste.get(1);// Nom du bot
        boolean victoire = false;
        char joueurActuel = joueur1;

        while (!victoire && !MethodePuissance4.plateauPlein(plateau)) {
            String nomJoueurActuel;
            int colonne;

            if (joueurActuel == joueur1) {
                // Tour du joueur humain
                nomJoueurActuel = PremJoueur;
                System.out.print("C'est au tour du joueur " + nomJoueurActuel + ". Choisissez une colonne : ");
                colonne = scanner.nextInt();
            } else {
                // Tour du bot
                nomJoueurActuel = "Bot";
                System.out.println();
                System.out.println("C'est au tour de " + nomJoueurActuel + ".");
                colonne = Bot.iaFacile(plateau); // Le bot choisit une colonne valide
                System.out.println(nomJoueurActuel + " a choisi la colonne : " + colonne);
                System.out.println();
            }

            if (MethodePuissance4.placerJeton(plateau, colonne, joueurActuel)) {
                MethodePuissance4.afficherPlateau(plateau);

                if (MethodePuissance4.plateauPlein(plateau)) {
                    break;
                }
            } else {
                System.out.println("Colonne pleine. Veuillez choisir une autre colonne.");
                continue; // Demande au joueur ou au bot de rejouer sans changer de joueur
            }

            victoire = MethodePuissance4.verifierVictoire(plateau, joueurActuel);

            if (!victoire) {
                // Alterner entre le joueur humain et le bot
                if (joueurActuel == joueur1) {
                    joueurActuel = bots;
                } else {
                    joueurActuel = joueur1;
                }
            }
        }

        // Affichage du gagnant
        if (victoire) {
            String nomJoueurGagnant;
            if (joueurActuel == joueur1) {
                nomJoueurGagnant = PremJoueur;
            } else {
                nomJoueurGagnant = DeuxJoueur;
            }
            System.out.println("Le joueur " + nomJoueurGagnant + " a gagné !\n");
        } else if (MethodePuissance4.plateauPlein(plateau)){
            System.out.println("Partie terminée avec une égalité entre " + PremJoueur + " et " + DeuxJoueur + " !\n");
        }
    }

    public static void jouerPartieAvecBotnormale(char[][] plateau, char joueur1, char bots, ArrayList<String> liste) {
        Scanner scanner = new Scanner(System.in);
        String PremJoueur = liste.get(0);
        String DeuxJoueur = liste.get(1);
        boolean victoire = false;
        char joueurActuel = joueur1;

        while (!victoire && !MethodePuissance4.plateauPlein(plateau)) {
            String nomJoueurActuel;
            int colonne;

            if (joueurActuel == joueur1) {
                // Tour du joueur humain
                nomJoueurActuel = PremJoueur;
                System.out.println();
                System.out.print("C'est au tour du joueur " + nomJoueurActuel + ". Choisissez une colonne : ");
                colonne = scanner.nextInt();
            } else {
                // Tour du bot
                nomJoueurActuel = DeuxJoueur;
                System.out.println();
                System.out.println("C'est au tour du joueur " + nomJoueurActuel + ".");
                colonne = Bot.iaNormale(plateau, bots, joueur1); // Le bot choisit une colonne stratégiquement
                System.out.println(nomJoueurActuel + " a choisi la colonne : " + colonne);
            }

            if (MethodePuissance4.placerJeton(plateau, colonne, joueurActuel)) {
                MethodePuissance4.afficherPlateau(plateau);

                if (MethodePuissance4.plateauPlein(plateau)) {
                    break;
                }
            } else {
                System.out.println("Colonne pleine. Veuillez choisir une autre colonne.");
                continue; // Demande au joueur ou au bot de rejouer sans changer de joueur
            }

            victoire = MethodePuissance4.verifierVictoire(plateau, joueurActuel);

            if (!victoire) {
                // Alterner entre le joueur humain et le bot
                if (joueurActuel == joueur1) {
                    joueurActuel = bots;
                } else {
                    joueurActuel = joueur1;
                }
            }
        }

        // Affichage du gagnant
        if (victoire) {
            String nomJoueurGagnant;
            if (joueurActuel == joueur1) {
                nomJoueurGagnant = PremJoueur;
            } else {
                nomJoueurGagnant = DeuxJoueur;
            }
            System.out.println("Le joueur " + nomJoueurGagnant + " a gagné !\n");
        } else if (MethodePuissance4.plateauPlein(plateau)){
            System.out.println("Partie terminée avec une égalité entre " + PremJoueur + " et " + DeuxJoueur + " !\n");
        }
    }

    public static void jouerPartieAvecBotdifficile(char[][] plateau, char joueur1, char bots, ArrayList<String> liste) {
        Scanner scanner = new Scanner(System.in);
        String PremJoueur = liste.get(0);
        String DeuxJoueur = liste.get(1);
        boolean victoire = false;
        char joueurActuel = joueur1;

        while (!victoire && !MethodePuissance4.plateauPlein(plateau)) {
            String nomJoueurActuel;
            int colonne;

            if (joueurActuel == joueur1) {
                // Tour du joueur humain
                nomJoueurActuel = PremJoueur;
                System.out.println();
                System.out.print("C'est au tour du joueur " + nomJoueurActuel + ". Choisissez une colonne : ");
                colonne = scanner.nextInt();
            } else {
                // Tour du bot
                nomJoueurActuel = DeuxJoueur;
                System.out.println();
                System.out.println("C'est au tour du joueur " + nomJoueurActuel + ".");
                colonne = Bot.iaDifficile(plateau, bots, joueur1); // Le bot utilise l'IA difficile
                System.out.println(nomJoueurActuel + " a choisi la colonne : " + colonne);
            }

            if (MethodePuissance4.placerJeton(plateau, colonne, joueurActuel)) {
                MethodePuissance4.afficherPlateau(plateau);

                if (MethodePuissance4.plateauPlein(plateau)) {
                    break;
                }
            } else {
                System.out.println("Colonne pleine. Veuillez choisir une autre colonne.");
                continue; // Demande au joueur ou au bot de rejouer sans changer de joueur
            }

            victoire = MethodePuissance4.verifierVictoire(plateau, joueurActuel);

            if (!victoire) {
                // Alterner entre le joueur humain et le bot
                if (joueurActuel == joueur1) {
                    joueurActuel = bots;
                } else {
                    joueurActuel = joueur1;
                }
            }
        }

        // Affichage du gagnant
        if (victoire) {
            String nomJoueurGagnant;
            if (joueurActuel == joueur1) {
                nomJoueurGagnant = PremJoueur;
            } else {
                nomJoueurGagnant = DeuxJoueur;
            }
            System.out.println("Le joueur " + nomJoueurGagnant + " a gagné !\n");
        } else if (MethodePuissance4.plateauPlein(plateau)){
            System.out.println("Partie terminée avec une égalité entre " + PremJoueur + " et " + DeuxJoueur + " !\n");
        }
    }
}






