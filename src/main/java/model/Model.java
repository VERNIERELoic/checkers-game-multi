package model;

import java.util.Collection;
import java.util.List;

import controller.OutputModelData;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 *         Cette classe gere les aspects metiers du jeu de dame
 *         independamment de toute vue
 * 
 *         Elle delegue e son objet ModelImplementor
 *         le stockage des PieceModel dans une collection
 * 
 *         Les pieces sont capables de se deplacer d'une case en diagonale
 *         si la case de destination est vide
 * 
 *         Ne sont pas geres les prises, les rafles, les dames,
 * 
 *         N'est pas gere le fait que lorsqu'une prise est possible
 *         une autre piece ne doit pas etre jouee
 * 
 */
public class Model implements BoardGame<Coord> {

	private PieceSquareColor currentGamerColor; // couleur du joueur courant

	private ModelImplementor implementor; // Cet objet sait communiquer avec les PieceModel

	public Model() {
		super();
		this.implementor = new ModelImplementor();
		this.currentGamerColor = ModelConfig.BEGIN_COLOR;
	}

	@Override
	public String toString() {
		return implementor.toString();
	}

	/**
	 * Actions potentielles sur le model : move, capture, promotion pion, rafles
	 */
	@Override
	public OutputModelData<Coord> moveCapturePromote(Coord toMovePieceCoord, Coord targetSquareCoord) {

		OutputModelData<Coord> outputModelData = null;

		boolean isMoveDone = false;
		Coord toCapturePieceCoord = null;
		Coord toPromotePieceCoord = null;
		PieceSquareColor toPromotePieceColor = null;

		// Si la piece est deplaeable (couleur du joueur courant et case arrivee
		// disponible)
		if (this.isPieceMoveable(toMovePieceCoord, targetSquareCoord)) {

			// S'il n'existe pas plusieurs pieces sur le chemin
			if (this.isThereMaxOnePieceOnItinerary(toMovePieceCoord, targetSquareCoord)) {

				// Recherche coord de l'eventuelle piece e prendre
				toCapturePieceCoord = this.getToCapturePieceCoord(toMovePieceCoord, targetSquareCoord);

				// si le deplacement est legal (en diagonale selon algo pion ou dame)
				boolean isPieceToCapture = toCapturePieceCoord != null;
				if (this.isMovePiecePossible(toMovePieceCoord, targetSquareCoord, isPieceToCapture)) {

					// deplacement effectif de la piece
					this.movePiece(toMovePieceCoord, targetSquareCoord);
					isMoveDone = true;

					// suppression effective de la piece prise
					this.remove(toCapturePieceCoord);

					// promotion eventuelle de la piece apres deplacement
					if (true) { // TODO : Test e changer atelier 3

						// TODO atelier 3
					}

					// S'il n'y a pas eu de prise
					// ou si une rafle n'est pas possible alors changement de joueur
					if (true) { // TODO : Test e changer atelier 4
						this.switchGamer();
					}

				}
			}
		}
		System.out.println(this);

		// Constitution objet de donnees avec toutes les infos necessaires e la view
		outputModelData = new OutputModelData<Coord>(
				isMoveDone,
				toCapturePieceCoord,
				toPromotePieceCoord,
				toPromotePieceColor);

		return outputModelData;

	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true si la PieceModel e deplacer est de la couleur du joueur courant
	 *         et que les coordonnees d'arrivees soient dans les limites du tableau
	 *         et qu'il n'y ait pas de piece sur la case d'arrivee
	 */
	boolean isPieceMoveable(Coord toMovePieceCoord, Coord targetSquareCoord) { // TODO : remettre en "private" apres
																				// test unitaires
		boolean bool = false;

		// TODO : e completer atelier 4 pour gerer les rafles

		bool = this.implementor.isPiecehere(toMovePieceCoord)
				&& this.implementor.getPieceColor(toMovePieceCoord) == this.currentGamerColor
				&& Coord.coordonnees_valides(targetSquareCoord)
				&& !this.implementor.isPiecehere(targetSquareCoord);

		return bool;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true s'il n'existe qu'1 seule piece e prendre d'une autre couleur sur
	 *         la trajectoire
	 *         ou pas de piece e prendre
	 */
	private boolean isThereMaxOnePieceOnItinerary(Coord toMovePieceCoord, Coord targetSquareCoord) {
		boolean isThereMaxOnePieceOnItinerary = false;
		int i = 0;
		for (Coord coord : implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord)) {
			if (implementor.isPiecehere(coord) && i < 1) {
				isThereMaxOnePieceOnItinerary = true;
				i += 1;
			}
		}
		return isThereMaxOnePieceOnItinerary;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return les coord de la piece e prendre, null sinon
	 */
	private Coord getToCapturePieceCoord(Coord toMovePieceCoord, Coord targetSquareCoord) {
		Coord toCapturePieceCoord = null;
		if (isThereMaxOnePieceOnItinerary(toMovePieceCoord, targetSquareCoord)) {
			for (Coord coord : implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord)) {
				toCapturePieceCoord = coord;
			}
		}
		return toCapturePieceCoord;
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @param isPieceToCapture
	 * @return true si le deplacement est legal
	 *         (s'effectue en diagonale, avec ou sans prise)
	 *         La PieceModel qui se trouve aux coordonnees passees en parametre
	 *         est capable de repondre ecette question (par l'intermediare du
	 *         ModelImplementor)
	 */
	boolean isMovePiecePossible(Coord toMovePieceCoord, Coord targetSquareCoord, boolean isPieceToCapture) { // unitaires
		return this.implementor.isMovePieceOk(toMovePieceCoord, targetSquareCoord, isPieceToCapture);
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 *                          Deplacement effectif de la PieceModel
	 */
	void movePiece(Coord toMovePieceCoord, Coord targetSquareCoord) { // TODO : remettre en "private" apres test
																		// unitaires
		this.implementor.movePiece(toMovePieceCoord, targetSquareCoord);
	}

	/**
	 * @param toCapturePieceCoord
	 *                            Suppression effective de la piece capturee
	 */
	private void remove(Coord toCapturePieceCoord) {
		this.implementor.removePiece(toCapturePieceCoord);
	}

	void switchGamer() { // TODO : remettre en "private" apres test unitaires
		this.currentGamerColor = (PieceSquareColor.WHITE).equals(this.currentGamerColor) ? PieceSquareColor.BLACK
				: PieceSquareColor.WHITE;

	}

}