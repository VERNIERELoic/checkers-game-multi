package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 *         Cette classe permet de donner une image aux pièces
 *
 */

public class PieceGui extends ImageView implements CheckersPieceGui {
	private PieceSquareColor color;

	public PieceGui(Image image, PieceSquareColor color) {
		this.color = color;
		setImage(image);
	}

	@Override
	public void promote(Image image) {
		setImage(image);
	}

	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {

		if (gamerColor == color)
			return true;
		else
			return false; // A changer
	}

}