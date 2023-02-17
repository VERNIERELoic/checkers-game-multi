package gui;

import controller.InputViewData;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author francoiseperrin
 * <p>
 * Cette classe represente le damier de la vue
 * <p>
 * Elle tire les valeurs d'affichage d'une fabrique de constante (GuiConfig)
 * 		public final static int size = 10;
 * 		public final static double height = 600.0;
 * 
 * Elle delegue e une fabrique le soin de creer et positionner les cases noires et blanches
 * et de creer et positionner les pieces e leur position initiale
 * 
 * Lorsque le model est MAJ, la methode moveCapturePromotion() est invoquee pour 
 * deplacer effectivement la piece sur le damier et eventuellement prendre et/ou promouvoir une PieceGui
 * (invocation e partir du controller en passant par classe View)
 * 
 */
class Board extends GridPane {

	public Board (EventHandler<MouseEvent> clicListener) {
		super();

		int nbCol, nbLig;
		nbCol = nbLig = GuiConfig.SIZE;

		BorderPane square = null;
		ImageView piece = null;

		for (int ligne = 0; ligne < nbLig; ligne++){
			for (int col = 0; col < nbCol; col++) {

				///// Creation d'une case /////

				// creation d'un BorderPane
				square = GuiFactory.createSquare(col, ligne);

				// ajout d'un ecouteur sur le carre
				square.setOnMouseClicked(clicListener);

				// taille des carres = taille de la fenetre / nombre de carres par lignes
				square.prefWidthProperty().bind(this.prefWidthProperty().divide(nbCol));
				square.prefHeightProperty().bind(this.prefHeightProperty().divide(nbLig));

				// ajout du carre sur le damier
				this.add(square, col, ligne);


				///// Si une piece doit se trouver sur cette case /////

				// creation de la piece uniquement si doit etre sur cette case
				piece = GuiFactory.createPiece(col, ligne);

				if (piece != null) {

					// ajout d'un ecouteur de souris
					// si la piece est selectionnee, elle sera supprime de son emplacement actuel
					// et repositionnee sur une autre case
					piece.setOnMouseClicked(clicListener);

					// gestion de la taille et position de la piece (au centre du carre)
					piece.fitWidthProperty().bind(square.widthProperty().divide(1.5));
					piece.fitHeightProperty().bind(square.heightProperty().divide(1.5));
					piece.xProperty().bind((square.widthProperty().subtract(piece.fitWidthProperty())).divide(2));
					piece.yProperty().bind(square.heightProperty().subtract(piece.fitHeightProperty()).divide(2));

					// Ajout de la piece sur le carre noir
					square.getChildren().add(piece);
				}
			}
		}
	}

	/////////////////////////////////////////////////////////////
	// Actions sur la view
	// initiees par le controller en passant par la classe View
	/////////////////////////////////////////////////////////////


	/**
	 * @param dataToRefreshView
	 * Cette methode est appelee par le controller en passant par la classe View
	 * afin de rafraichir la view lorsque le model a ete mis e jour
	 */
	public void actionOnGui(InputViewData<Integer> dataToRefreshView) {
		
		if (dataToRefreshView != null) {
			
			////////////////////////////////////////////////////
			// la PieceGui de la vue est effectivement deplacee
			////////////////////////////////////////////////////
			if (dataToRefreshView.toMovePieceIndex != -1 && dataToRefreshView.targetSquareIndex != -1) {
				ImageView toMovePiece = null;
				BorderPane toMovePieceSquare = (BorderPane) this.getChildren().get(dataToRefreshView.toMovePieceIndex);
				BorderPane targetSquare = (BorderPane) this.getChildren().get(dataToRefreshView.targetSquareIndex);

				// Ajout sur la case de destination
				if (!toMovePieceSquare.getChildren().isEmpty())
					toMovePiece = (ImageView) toMovePieceSquare.getChildren().get(0);

				// clear la case d'origine de la piece deplacee
				if (toMovePiece != null) {
					targetSquare.getChildren().add(toMovePiece);
					toMovePieceSquare.getChildren().removeAll();
				}
			}

			////////////////////////////////////////////////////
			// la PieceGui de la vue est eventuellement promue
			////////////////////////////////////////////////////
			if (dataToRefreshView.promotedPieceIndex != -1) {
				BorderPane targetSquare = (BorderPane) this.getChildren().get(dataToRefreshView.promotedPieceIndex);
				ImageView piece = (ImageView) targetSquare.getChildren().get(0);
				// delegation e la fabrique qui sait comment fabriquer des images
				GuiFactory.PromotePiece(piece, dataToRefreshView.promotedPieceColor);
			}

			////////////////////////////////////////////////////
			// l'eventuelle piece intermediaire est supprimee 
			////////////////////////////////////////////////////
			if (dataToRefreshView.capturedPieceIndex != -1) {
				// clear la case d'origine de la piece supprimee
				BorderPane capturedPieceSquare = (BorderPane) this.getChildren().get(dataToRefreshView.capturedPieceIndex);
				capturedPieceSquare.getChildren().clear();
			}
		}

	}

}



