package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 *         Cete classe fabrique et stocke toutes les PieceModel du Model dans
 *         une collection
 *         elle est donc responsable de rechercher et mettre e jour les
 *         PieceModel (leur position)
 *         En realite, elle delegue e une fabrique le soin de fabriquer les
 *         bonnes PieceModel
 *         avec les bonnes coordonnees
 * 
 *         En revanche, elle n'est pas responsable des algorithme metiers lies
 *         au deplacement des pieces
 *         (responsabilite de la classe Model)
 */
public class ModelImplementor {

	// la collection de pieces en jeu - melange noires et blanches
	private Collection<PieceModel> pieces;

	public ModelImplementor() {
		super();

		pieces = ModelFactory.createPieceModelCollection();
	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;

		if (findPiece(coord) != null) {
			color = findPiece(coord).getPieceColor();
		}

		return color;
	}

	public boolean isPiecehere(Coord coord) {
		boolean isPiecehere = false;
		if (findPiece(coord) != null) {
			isPiecehere = true;
		}
		return isPiecehere;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {

		boolean isMovePieceOk = false;

		if (findPiece(initCoord) != null) {
			isMovePieceOk = findPiece(initCoord).isMoveOk(targetCoord, isPieceToTake);
		}

		return isMovePieceOk;
	}

	public boolean movePiece(Coord initCoord, Coord targetCoord) {

		boolean isMovePieceDone = false;

		findPiece(initCoord).move(targetCoord);
		if (isPiecehere(targetCoord)) {
			isMovePieceDone = true;
		}

		return isMovePieceDone;
	}

	public void removePiece(Coord pieceToTakeCoord) {

		if (isPiecehere(pieceToTakeCoord)) {
			pieces.remove(findPiece(pieceToTakeCoord));
		}

	}

	public List<Coord> getCoordsOnItinerary(Coord initCoord, Coord targetCoord) {
		//Atelier 2
		return new LinkedList<Coord>();
	}

	/**
	 * @param coord
	 * @return la piece qui se trouve aux coordonnees indiquees
	 */
	private PieceModel findPiece(Coord coord) { // TODO : mettre en "private" apres test unitaires

		PieceModel findPiece = null;

		for (PieceModel current : pieces) {
			if (current.getColonne() == coord.colonne && current.getLigne() == coord.ligne) {
				findPiece = current;
			}
		}

		return findPiece;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * La methode toStrong() retourne une representation
	 * de la liste de pieces sous forme d'un tableau 2D
	 * 
	 */
	public String toString() {

		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];

		// creation d'un tableau 2D avec les noms des pieces e partir de la liste de
		// pieces
		for (PieceModel piece : this.pieces) {

			PieceSquareColor color = piece.getPieceColor();
			String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--");

			int col = piece.getColonne() - 'a';
			int lig = piece.getLigne() - 1;
			damier[lig][col] = stColor;
		}

		// Affichage du tableau formatte
		st = "     a      b      c      d      e      f      g      h      i      j\n";
		for (int lig = 9; lig >= 0; lig--) {
			st += (lig + 1) + "  ";
			for (int col = 0; col <= 9; col++) {
				String stColor = damier[lig][col];
				if (stColor != null) {
					st += stColor + "  ";
				} else {
					st += "-----  ";
				}
			}
			st += "\n";
		}

		return "\nDamier du model \n" + st;
	}

}
