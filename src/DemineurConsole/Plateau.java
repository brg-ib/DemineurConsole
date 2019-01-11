package DemineurConsole;

/**
 * Le démineur console en JAVA 
 * @author Ihcen Borgi - Brahim Mlaghui
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.*;

public class Plateau {

  private int lin, col;
  public static Scanner scanner;
  static long chrono = 0;
  
  /*
   * Si le jeu est terminé
   */
  public boolean gameOver; 
  /*
   * Si c'est la premiere fois qu'on ouvre une case
   */
  private boolean firstTime; 
  /*
   * Nombre de bombes autour
   */
  private int prox[][];
  /*
  * Si la case est ouverte
  */
  private boolean[][] open;  
  /*
   * Si la case contient un drapeau
   */
  private boolean[][] flag; 
  /*
   *  Si la case contient une bombe
   */
  private boolean[][] bombe;
  /*
   * Nombre de cases ouvertes
   */
  private int nrOpen;
  /*
   * Nombre de bombes
   */
  private int nrbombe; 

  /**
   * Constructeur Plateau
   * Permet d'initialiser le plateau du jeu à l'état par defaut
   * @param lin
   * @param col
   * @param nrbombe
   * @return
   */
  public Plateau(int lin, int col, int nrbombe) {
    this.lin = lin;
    this.col = col;
    this.nrbombe = nrbombe;

    nrOpen = 0;
    gameOver = false;
    firstTime = true;
    
    prox = new int[lin + 1][col + 1];
    open = new boolean[lin + 1][col + 1];
    flag = new boolean[lin + 1][col + 1];
    bombe = new boolean[lin + 2][col + 2];
    
    // Initialisation de la matrice:
    for (int i = 1; i <= lin; i++)
      for (int j = 1; j <= col; j++)
        open[i][j] = flag[i][j] = bombe[i][j] = false;
    
    // Calcul correctement les bombes à proximités
    for (int i = 0; i <= lin + 1; i++)
      bombe[i][0] = bombe[i][col + 1] = false;
    for (int j = 0; j <= col + 1; j++)
      bombe[0][j] = bombe[lin + 1][j] = false;
  }

  /**
   * Methode qui genere les bombes
   * Ne pas mettre de bombes dans la cellule (x,y) 
   * @param x
   * @param y
   */
  public void generate(int x, int y) {
    ArrayList<Cell> cells = new ArrayList<>();
    for (int i = 1; i <= lin; i++)
      for (int j = 1; j <= col; j++)
        if (!(i == x && j == y))
          cells.add(new Cell(i, j));
    
    /**
     * Placement aléatoire de nrbombe
     */
    Collections.shuffle(cells);
    for (int i = 0; i < nrbombe; i++)
      bombe[cells.get(i).x][cells.get(i).y] = true;
    
    /**
     *  Arrays pour les mouvements
     *  On considere 8 voisins
     */
    int addLin[] = {-1, -1, 0, 1, 1,  1,  0, -1};
    int addCol[] = { 0,  1, 1, 1, 0, -1, -1, -1};
    
    /**
     *  Generation de la matrice des bombes à proximité
     */
    for (int i = 1; i <= lin; i++)
      for (int j = 1; j <= col; j++) {
        prox[i][j] = 0;
        for (int k = 0; k < 8; k++)
          if (bombe[i + addLin[k]][j + addCol[k]])
            prox[i][j]++;
      }
  }
  
  /**
   * Affiche le nouveau plateau actualisé
   * @param
   */
  public void print() {
    System.out.println();
    for (int i = 1; i <= lin; i++) {
      for (int j = 1; j <= col; j++) {
        if (open[i][j]) {
          if (bombe[i][j])
            System.out.print('*');
          else if (prox[i][j] != 0)
            System.out.print(prox[i][j]);
          else
            System.out.print('_');
        }
        else if (flag[i][j])
          System.out.print('?');
        else
          System.out.print('X');
        System.out.print(' ');
      }
      System.out.println();
    }
//     chrono();
    System.out.print("******Aide**********\n"
    		+ "args[0] - Coordonnee de la ligne\n"
    		+ "args[1] - Coordonnee de la colonne\n"
    		+ "args[2] = 1 pour ouvrir / 2 pour flag/unflag, 0 pour activer le mode triche\n"
    		+ "Votre choix: ");
    //redo();
    //save();
    // triche();
    }
  

  /**
   * Actualise le nouveau plateau avec les nouveaux parametres...
   * @param	l
   * @param c
   * @param flag[][]
   * @param open[][]
   * @param bombe[][]
   * @param prox[][]
   * @return 
   */
  public Plateau(int l, int c, boolean flag[][], boolean open[][],boolean bombe[][], int prox[][]) {
	 this.lin = l;
	 this.col = c;
	 this.flag = flag;
	 this.open = open;
	 this.bombe = bombe;
	 this.prox = prox;
	 print();
  }
  
  
  /**
   * Debut de la création de la mathode save()
   * Permet de sauvegarder la partie en cours
   * ATTENTION : Methode non fonctionnel pour le moment
   * @param 
   */
  public void save() {
	 // Plateau(l,c, flag[][], open[][], bombe[][], prox[][]);
  }
  

 /* public void chrono() {
	  long chrono2 = System.;
//	  long temps = chrono2 - chrono;
	    System.out.print("Temps écoulé: "+ chrono +"ms1");     
  }*/
  
  /**
   * Affiche les bombes de la grille
   * Si true alors une bombe se cache derriere
   * @param 
   */
  public void triche() {
	  System.out.println("Mode triche activé");
	  for (int i = 1; i <= lin; i++) {
	        for (int j = 1; j <= col; j++) {
	        	System.out.print(" "+bombe[i][j]+" ") ;
	        }
	        System.out.println();
	  }
  }
  
  /**
   * Permet d'ouvrir/flag/unflag une case passée en parametre
   * Si le parametre de la case saisi depasse le tableau alors une erreur est affichée.
   * Si une case est déjà ouverte, une erreur est affichée.
   * args[0] = x ; args[1] = y ; args[2] = 1 pour ouvrir / 2 pour flag, 0 pour activer
   * le mode triche.
   * @param
   */
  public void place() {
	  
	int x = scanner.nextInt();
    int y = scanner.nextInt();
    int type = scanner.nextInt();
    
	if (type == 0)
		triche();
    
	if (!(1 <= x && x <= lin && 1 <= y && y <= col) || !(type == 1 || type == 2)) {
      System.out.println("Erreur de placement / saisie.");
      return;
    }

    if (type == 1) {
      if (open[x][y])
        System.out.println("Erreur! Deja ouvert");
      else if (bombe[x][y])
        finJeu(false);
      else {
        if (firstTime) {
          generate(x, y);
          firstTime = false;
        }
          
        open[x][y] = true;
        flag[x][y] = false;
        if (++nrOpen == lin * col - nrbombe)
          finJeu(true);
      }
    }
    else if (open[x][y])
      System.out.println("Erreur! Deja ouvert");
    else
      flag[x][y] ^= true;
  }
  
  /**
   * Verifie si le jeu est terminé.
   * @param win
   */
  public void finJeu(boolean win) {
    gameOver = true;
    if (win)
      System.out.println("C'est Gagné !");
    else {
      for (int i = 1; i <= lin; i++)
        for (int j = 1; j <= col; j++)
          open[i][j] = true;
      System.out.println("Perdu :(");
    }
  }
  

  
  private class Cell {
    int x, y;

    /**
     * Determine les coordonnees d'une cellule
     * @param x
     * @param y
     * @return 
     */
    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}