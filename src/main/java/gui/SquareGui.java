package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 * 
 * Classe d'affichage des carres du damier
 * leur couleur est initialise par les couleurs par defaut du jeu
 *
 */
class SquareGui extends BorderPane implements CheckersSquareGui {
	
	private PieceSquareColor squareColor;    		// le carre est Noir ou Blanc

	public SquareGui (PieceSquareColor squareColor) {
		// ToDo Atelier 2
	}
	
	/**
	 *Retourne l'indice du carre sur la grille (Ne de 0 e 99)
	 */
	@Override
	public int getSquareCoord() {
		int index = -1;
		Pane parent = (Pane) this.getParent();
		index = parent.getChildren().indexOf(this);
		return index;
	}

}
