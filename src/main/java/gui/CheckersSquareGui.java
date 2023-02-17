package gui;

/**
 * @author francoise.perrin
 * Cette interface permet de verifier qu'un Node
 * est fonctionnellement une case du jeu
 *  
 */
public interface CheckersSquareGui {
	
	/**
	 *Retourne l'indice du carre sur la grille (Ne de 0 e 99)
	 */
	int getSquareCoord();
}
