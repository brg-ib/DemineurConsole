package DemineurConsole;

import java.util.*;

class Main {

  public static void main(String[] args){
  		System.out.println("*********************");
		System.out.println("*     Bienvenue     *");
		System.out.println("*    au Demineur    *");
		System.out.println("*Par Ihcen & Brahim *");
		System.out.println("*********************");
		System.out.println();

		Plateau.scanner = new Scanner(System.in);
		int entree;
		do {
			System.out.println("[1] Nouvelle Partie");
		    System.out.println("[2] Charger une partie");
			System.out.println("[3] Aide");
			System.out.println("[4] A propos");
			System.out.println("[5] Quitter");
			System.out.print("Votre choix: ");
			
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
