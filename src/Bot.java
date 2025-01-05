import java.util.Random;
public class Bot {

    public static int iaFacile(char[][] plateau) { // joue une colonne aléatoire valide
        Random aleatoire = new Random();
        int colonne;

        do {
            colonne = aleatoire.nextInt(plateau[0].length) + 1; // Colonne entre 1 et 7
        } while (!colonneValide(plateau, colonne));
        return colonne;
    }

    public static boolean colonneValide(char[][] plateau, int colonne) {
        // Vérifie si au moins une case dans la colonne est vide, en commençant par le bas
        for (int i = plateau.length - 1; i >= 0; i--) {
            if (plateau[i][colonne - 1] == '.') {
                return true; // La colonne est valide si une case vide est trouvée
            }
        }
        return false; // Si aucune case vide n'est trouvée, la colonne est pleine
    }



    public static int iaNormale(char[][] plateau, char iaJeton, char joueurJeton) {
        // Vérifie si l'IA peut gagner en un coup
        int COLONNES = 7;
        for (int col = 1; col <= COLONNES; col++) {
            if (colonneValide(plateau, col)) {
                MethodePuissance4.placerJeton(plateau, col, iaJeton);
                if (MethodePuissance4.verifierVictoire(plateau, iaJeton)) {
                    enleverJeton(plateau, col);
                    return col;
                }
                enleverJeton(plateau, col);
            }
        }

        // Vérifie si le joueur peut gagner et bloque
        for (int col = 1; col <= COLONNES; col++) {
            if (colonneValide(plateau, col)) {
                MethodePuissance4.placerJeton(plateau, col, joueurJeton);
                if (MethodePuissance4.verifierVictoire(plateau, joueurJeton)) {
                    enleverJeton(plateau, col);
                    return col;
                }
                enleverJeton(plateau, col);
            }
        }
        // Sinon, joue comme une IA facile
        return iaFacile(plateau);
    }

    public static void enleverJeton(char[][] plateau, int colonne) {
        for (int i = 0; i < plateau.length; i++) {
            if (plateau[i][colonne - 1] != '.') {
                plateau[i][colonne - 1] = '.';
                break;
            }
        }
    }



    public static int iaDifficile(char[][] plateau, char iaJetonD, char joueurJetonD) {
        // Etape 1 : vérifier si l'IA peut gagner directement
        int COLONNES = 7;
        for (int col = 1; col <= COLONNES; col++) {
            if (colonneValide(plateau, col)) {
                MethodePuissance4.placerJeton(plateau, col, iaJetonD);
                if (MethodePuissance4.verifierVictoire(plateau, iaJetonD)) {
                    enleverJeton(plateau, col);
                    return col; // Gagne immédiatement si possible
                }
                enleverJeton(plateau, col);
            }
        }

        // Étape 2 : Vérifier si le joueur humain peut gagner au prochain tour
        for (int col = 1; col <= COLONNES; col++) {
            if (colonneValide(plateau, col)) {
                MethodePuissance4.placerJeton(plateau, col, joueurJetonD); // Utilise le jeton du joueur humain
                if (MethodePuissance4.verifierVictoire(plateau, joueurJetonD)) {
                    enleverJeton(plateau, col);
                    return col; // Bloque immédiatement si possible
                }
                enleverJeton(plateau, col);
            }
        }

        // Étape 3 : Évaluer les colonnes pour jouer la meilleure colonne possible
        int meilleurScore = Integer.MIN_VALUE;
        int meilleureColonne = -1;

        for (int col = 1; col <= COLONNES; col++) {
            if (colonneValide(plateau, col)) {
                MethodePuissance4.placerJeton(plateau, col, iaJetonD);
                int score = evaluerPlateau(plateau, iaJetonD);
                enleverJeton(plateau, col);

                if (score > meilleurScore) {
                    meilleurScore = score;
                    meilleureColonne = col;
                }
            }
        }
        if (meilleureColonne == -1) { // si il n'y a pas de meilleure colonne, le bot joue de manière facile
            return iaFacile(plateau);
        }

        return meilleureColonne;
    }




    public static int evaluerPlateau(char[][] plateau, char jeton) {
        int score = 0;
        int LIGNES = 6;
        int COLONNES = 7;

        // Évaluation des alignements horizontaux
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c <= COLONNES - 4; c++) {
                int alignement = 0;
                for (int k = 0; k < 4; k++) {
                    if (plateau[l][c + k] == jeton) {
                        alignement++;
                    }
                }
                if (alignement == 4) {
                    score = score + 10; // gros score pour gagner
                } else if (alignement == 3) {
                    score = score + 3; // Un alignement de 3 jetons est valide
                }
            }
        }

        // Évaluation des alignements verticaux
        for (int c = 0; c < COLONNES; c++) {
            for (int l = 0; l <= LIGNES - 4; l++) {
                int alignement = 0;
                for (int k = 0; k < 4; k++) {
                    if (plateau[l + k][l] == jeton) {
                        alignement++;
                    }
                }
                if (alignement == 4) {
                    score = score + 10; // gros score pour gagner
                } else if (alignement == 3) {
                    score = score + 3; // Un alignement de 3 jetons est valide
                }
            }
        }

        // Évaluation des alignements diagonaux descendants
        for (int l = 0; l <= LIGNES - 4; l++) {
            for (int c = 0; c <= COLONNES - 4; c++) {
                int alignement = 0;
                for (int k = 0; k < 4; k++) {
                    if (plateau[l + k][c + k] == jeton) {
                        alignement++;
                    }
                }
                if (alignement == 4) {
                    score = score + 10; // gros score pour gagner
                } else if (alignement == 3) {
                    score = score + 3; // Un alignement de 3 jetons est valide
                }
            }
        }

        // Évaluation des alignements diagonaux montants
        for (int l = 3; l < LIGNES; l++) {
            for (int c = 0; c <= COLONNES - 4; c++) {
                int alignement = 0;
                for (int k = 0; k < 4; k++) {
                    if (plateau[l - k][c + k] == jeton) {
                        alignement++;
                    }
                }
                if (alignement == 4) {
                    score = score + 10; // gros score pour gagner
                } else if (alignement == 3) {
                    score = score + 3; // Un alignement de 3 jetons est valide
                }
            }
        }
        return score;
    }
}
