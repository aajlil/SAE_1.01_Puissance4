import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class MethodePuissance4Test {

    @Test
    public void ajouterPseudo() {
        ArrayList<String> pseudos = new ArrayList<>();
        pseudos.add("Ayman");

        // Test 1 : Ajouter un pseudo valide
        MethodePuissance4.ajouterPseudo(pseudos, "Billal", false);
        assertTrue("Le pseudo 'Billal' à été ajouté", pseudos.contains("Billal"));

        // Test 2 : Ajouter un pseudo invalide (moins de 2 ou plus de 20 caractères)
        MethodePuissance4.ajouterPseudo(pseudos, "B", false);
        assertFalse("Le pseudo 'B' n'a pas été ajouté", pseudos.contains("B"));
        MethodePuissance4.ajouterPseudo(pseudos, "AZERTYUIOPQSDFGHJKLMWX", false);
        assertFalse("Le pseudo 'AZERTYUIOPQSDFGHJKLMWX' n'a pas été ajouté", pseudos.contains("AZERTYUIOPQSDFGHJKLMWX"));

        // Test 3 : Vérifier qu'un pseudo déjà existant ne peut pas être ajouté
        pseudos.add("Ayman");
        MethodePuissance4.ajouterPseudo(pseudos, "Ayman", false);
        assertFalse("Le pseudo 'Ayman' ne peut pas être ajouté deux fois", pseudos.size() == 2);
    }


    @Test
    public void initialiserPlateau() {
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];

        MethodePuissance4.initialiserPlateau(plateau);
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                assertEquals("Erreur à la case [" + l + "][" + c + "]", '.', plateau[l][c]);
            }
        }

    }

    @Test
    public void afficherPlateau() {
        char[][] plateau = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', 'X', 'O', '.', 'X', 'O'}
        };

        MethodePuissance4.afficherPlateau(plateau);
    }

    @Test
    public void placerJeton() {
        // Initialisation d'un plateau vide
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateau[l][c] = '.';
            }
        }

        // Test 1 : Placement dans une colonne valide
        boolean resultat = MethodePuissance4.placerJeton(plateau, 3, 'X');
        assertTrue("Le jeton a été placé correctement", resultat);
        assertEquals("Le jeton du joueur est au bon emplacement", 'X', plateau[5][2]);

        // Test 2 : Placement dans une colonne invalide (colonne 0)
        resultat = MethodePuissance4.placerJeton(plateau, 0, 'O');
        assertFalse("La colonne est invalide", resultat);

        // Test 3 : Placement dans une colonne pleine
        for (int i = 0; i < LIGNES; i++) {
            plateau[i][1] = 'O'; // Remplir toute la colonne 2
        }
        resultat = MethodePuissance4.placerJeton(plateau, 2, 'X');
        assertFalse("La colonne est pleine", resultat);
    }

    @Test
    public void plateauPlein() {
        int LIGNES = 6;
        int COLONNES = 7;

        // Test 1 : Plateau vide
        char[][] plateauVide = new char[LIGNES][COLONNES];
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateauVide[l][c] = '.';
            }
        }
        assertFalse("Le plateau n'est pas vide", MethodePuissance4.plateauPlein(plateauVide));

        // Test 2 : Plateau rempli
        char[][] plateauRempli = new char[LIGNES][COLONNES];
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateauRempli[l][c] = 'O';
            }
        }
        assertTrue("Le plateau est rempli", MethodePuissance4.plateauPlein(plateauRempli));

        // Test 3 : Plateau partiellement rempli
        char[][] plateauPartiel = new char[LIGNES][COLONNES];
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                if (l == 0 && c == 0) {
                    plateauPartiel[l][c] = '.';
                } else {
                    plateauPartiel[l][c] = 'X';
                }
            }
        }
        assertFalse("Le plateau doit contenir encore des cases vides, il est pas plein", MethodePuissance4.plateauPlein(plateauPartiel));
    }

    @Test
    public void verifierVictoire() {
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateau[l][c] = '.';
            }
        }

        // Test 1 : Victoire horizontale
        plateau[2][0] = 'X';
        plateau[2][1] = 'X';
        plateau[2][2] = 'X';
        plateau[2][3] = 'X'; // 4 jetons 'X' alignés horizontalement
        assertTrue("Le joueur X à gagné horizontalement", MethodePuissance4.verifierVictoire(plateau, 'X'));

        // Test 2 : Victoire verticale
        plateau[0][1] = 'O';
        plateau[1][1] = 'O';
        plateau[2][1] = 'O';
        plateau[3][1] = 'O'; // 4 jetons 'O' alignés verticalement
        assertTrue("Le joueur O à gagné verticalement", MethodePuissance4.verifierVictoire(plateau, 'O'));

        // Test 3 : Victoire diagonale descendante
        plateau[0][0] = 'X';
        plateau[1][1] = 'X';
        plateau[2][2] = 'X';
        plateau[3][3] = 'X'; // 4 jetons 'X' alignés en diagonale descendante
        assertTrue("Le joueur X à gagné en diagonale descendante", MethodePuissance4.verifierVictoire(plateau, 'X'));

        // Test 4 : Victoire diagonale montante
        plateau[3][0] = 'O';
        plateau[2][1] = 'O';
        plateau[1][2] = 'O';
        plateau[0][3] = 'O'; // 4 jetons 'O' alignés en diagonale montante
        assertTrue("Le joueur O à gagné en diagonale montante", MethodePuissance4.verifierVictoire(plateau, 'O'));

        // Test 5 : Pas de victoire
        plateau[5][5] = 'X';
        plateau[5][6] = 'X';
        plateau[0][0] = 'O';// Pas assez de jetons alignés
        assertFalse("Le joueur X n'a pas gagné", MethodePuissance4.verifierVictoire(plateau, 'X'));
    }

    @Test
    public void jouerPartie() {
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateau[l][c] = '.';
            }
        }

        // Liste des joueurs
        ArrayList<String> joueurs = new ArrayList<>();
        joueurs.add("Ayman");
        joueurs.add("Billal");

        // Simuler un jeu simple où le joueur 1 (X) gagne
        plateau[5][0] = 'X';
        plateau[5][1] = 'X';
        plateau[5][2] = 'X';
        plateau[5][3] = 'X'; // 4 jetons 'X' alignés horizontalement

        // Test : Vérifier si le joueur X gagne
        boolean victoire = MethodePuissance4.verifierVictoire(plateau, 'X');
        assertTrue("Le joueur X à gagné", victoire);
    }

    @Test
    public void colonneValide() {
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateau[l][c] = '.';
            }
        }

        plateau[0][2] = 'X'; // La colonne 3 a une case remplie, mais il reste des cases vides

        assertTrue("La colonne 1 est valide", Bot.colonneValide(plateau, 1));
        assertTrue("La colonne 2 est valide", Bot.colonneValide(plateau, 2));
        assertTrue("La colonne 4 est valide", Bot.colonneValide(plateau, 4));
        assertTrue("La colonne 5 est valide", Bot.colonneValide(plateau, 5));
        assertTrue("La colonne 6 est valide", Bot.colonneValide(plateau, 6));
        assertTrue("La colonne 7 est valide", Bot.colonneValide(plateau, 7));

        plateau[1][2] = 'X';
        plateau[2][2] = 'X';
        plateau[3][2] = 'X';
        plateau[4][2] = 'X';
        plateau[5][2] = 'X'; // La colonne 3 est maintenant pleine

        assertFalse("La colonne 3 n'est pas valide car elle est pleine", Bot.colonneValide(plateau, 3));
    }

    @Test
    public void enleverJeton() {
        int LIGNES = 6;
        int COLONNES = 7;
        char[][] plateau = new char[LIGNES][COLONNES];

        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                plateau[l][c] = '.';
            }
        }

        plateau[0][2] = 'X';
        plateau[1][2] = 'O';

        Bot.enleverJeton(plateau, 3);
        assertEquals("Le jeton dans la première ligne de la colonne 3 à été enlevé", '.', plateau[0][2]);
        assertEquals("Le jeton dans la deuxième ligne de la colonne 3 est toujours la", 'O', plateau[1][2]);
    }
}

