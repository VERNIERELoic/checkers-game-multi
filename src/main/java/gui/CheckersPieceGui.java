package gui;

import javafx.scene.image.Image;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * Cette interface permet de verifier qu'un Node
 * est fonctionnellement une piece du jeu
 * 
 * Lorsque le pion du model est promu en dame
 * le visuel change
 * 
 * La methode hasSameColorAsGamer sera utilise en mode Client/server
 * pour empecher un joueur de jouer une piece qui ne lui appartient pas
 */
public interface CheckersPieceGui {
	
	public void promote(Image image);

	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor);
}
