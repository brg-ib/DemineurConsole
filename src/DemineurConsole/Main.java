
package DemineurConsole;

/**
 * Le démineur console en JAVA 
 * @author Ihcen Borgi - Brahim Mlaghui
 * @version 1.0
 */
import java.util.*;

public class Main {

	/**
	 * Methode Main principale
	 * @param args
	 */
  public static void main(String[] args){
  		System.out.println("*********************\n"
  				+ "*     Bienvenue     *\n"
  				+ "*    au demineur    *\n"
  				+ "*Par Ihcen & Brahim *\n"
  				+ "*********************");
		Plateau.scanner = new Scanner(System.in);
		int entree;
		do {
			System.out.print("[1] Nouvelle Partie\n"
					+ "[2] Charger une partie\n"
					+ "[3] Aide\n"
					+ "[4] A propos\n"
					+ "[5] Quitter\n"
					+ "Votre choix: ");
			
			entree = Plateau.scanner.nextInt();			
			
			switch (entree) {
			case 1:
				lancerJeu();
	        	System.out.println();
				break;
			case 2:
				System.out.println("Chargez le jeu"); 
				break;
	        case 3:
	        	System.out.println();
	        	System.out.println("** Aide **");
	        	System.out.println("Ouvir une case: ligne colonne 1");
	        	System.out.println("Placer un drapeau: ligne colonne 2");
	        	System.out.println("Drapeau: ? \n"
	        			+ "Bombe: *\n"
	        			+ "Case vide: _");
	        	System.out.println();
	        	break;
	        case 4:
	        	System.out.println();
	        	System.out.println("** A propos **");
	        	System.out.println("Jeu developpé par Ihcen et Brahim");
	        	System.out.println("dans le cadre du projet BTS 2eme année");
	        	System.out.println();
	        	break;
	        case 5:
	        	System.out.println("A bientôt !");
	        	break;
	        default:
	        	System.out.println("Erreur de saisie. Recommencez.");
	        	System.out.println();
			}
		} while (entree != 5);
		Plateau.scanner.close();
	}


  /**
   * Lancement du jeu grace à la methode lancerJeu()
   */
  private static void lancerJeu() {
		int b, l, c;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Nombre de lignes: ");
		l = sc.nextInt();
		System.out.print("Nombre de colonnes: ");
		c = sc.nextInt();
		System.out.print("Nombre de bombes: ");
		b = sc.nextInt();
		Plateau table = new Plateau(l, c, b);
		while (!table.gameOver) {
			table.print();
			table.place();
		}
		table.print();
	}
}
