package controller.localController;


import controller.InputViewData;
import controller.Mediator;
import controller.OutputModelData;
import gui.CheckersSquareGui;
import gui.View;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.BoardGame;
import model.Coord;
import model.ModelConfig;


/**
 * @author francoiseperrin
 *
 * Le controller a 2 responsabilites :
 * 	- il ecoute les clics de souris de l'utilisateur sur la vue
 * 	- il invoque la methode moveCapturePromote() du model
 * 	  si actions (move + prise + promotion) OK sur model alors elles sont propagees sur view 
 *    (invoque methode moveCapturePromote() de la view)
 *    
 * La view et le model ne gerant pas les coordonnees des cases de la meme maniere
 * le controller assure la conversion :
 * 	- index de 0 e 99 pour la view
 * 	- Coord (col, ligne) pour le model ['a'..'j'][10..1]
 * 
 */
public class Controller implements Mediator, BoardGame<Integer>, EventHandler<MouseEvent>  {


	private BoardGame<Coord> model;
	private View view;

	// Cette valeur est MAJ chaque fois que l'utilisateur clique sur une piece
	// Elle doit etre conservee pour etre utilisee lorsque l'utilisateur clique sur une case
	private int toMovePieceIndex;	

	public Controller() {
		this.model =  null;
		this.view = null;
		this.setToMovePieceIndex(-1);
	}

	private void setToMovePieceIndex(int toMovePieceIndex) {
		this.toMovePieceIndex = toMovePieceIndex;
	}

	public int getToMovePieceIndex() {
		return toMovePieceIndex;
	}

	//////////////////////////////////////////////////////////////////
	//
	// Controller vu comme un mediateur entre la view et le model
	//
	//////////////////////////////////////////////////////////////////

	public void setView(View view) {
		this.view = view;
	}
	public void setModel(BoardGame<Coord> model) {
		this.model =  model;
	}

	////////////////////////////////////////////////////////////////////
	//
	// Controller vu comme un Ecouteur des evenement souris sur la view
	//
	////////////////////////////////////////////////////////////////////

	@Override
	public void handle(MouseEvent mouseEvent) {
		try {
			if(mouseEvent.getSource() instanceof CheckersSquareGui)
				checkersSquareGuiHandle(mouseEvent);
			else
				checkersPieceGuiHandle(mouseEvent);
		}
		catch (Exception e) {
			// Try - Catch pour empecher pgm de planter tant que les interfaces
			// CheckersSquareGui et CheckersPieceGui n'existent pas
			e.printStackTrace();
		}
	}
	
	/**
	 * @param mouseEvent
	 * Ecoute les evenements sur les PieceGui
	 */
	private void checkersPieceGuiHandle(MouseEvent mouseEvent) {
		
		// Recherche PieceGui selectionnee
		ImageView selectedPiece = (ImageView) mouseEvent.getSource();

		// Recherche et fixe coordonnee de la piece selectionnee 
		CheckersSquareGui parentSquare = (CheckersSquareGui)  selectedPiece.getParent();
		this.setToMovePieceIndex(parentSquare.getSquareCoord());
		
		mouseEvent.consume();
	}
	/**
	 * @param mouseEvent
	 * Ecoute les evenements sur les SquareGui
	 */
	private void checkersSquareGuiHandle(MouseEvent mouseEvent) {
		System.out.println("ici");
		// Recherche SquareGUI selectionne
		CheckersSquareGui square = (CheckersSquareGui) mouseEvent.getSource();
		int targetSquareIndex = square.getSquareCoord();

		// Le controller va invoquer la methode moveCapturePromotion() du model
		// et si le model confirme que la piece a bien ete deplacee ecet endroit, 
		// il invoquera une methode de la view pour la rafraichir
		this.moveCapturePromote(this.getToMovePieceIndex(), targetSquareIndex);

		// il n'y a plus de piece e deplacer
		this.setToMovePieceIndex(-1);

		// On evite que le parent ne recupere l'event
		mouseEvent.consume();
	}


	//////////////////////////////////////////////////////////////////
	//
	// Controller vu comme un Substitut du model 
	// il invoque les methodes du model 
	// apres actions de l'utilisateur sur la vue
	//
	//////////////////////////////////////////////////////////////////

	/**
	 * Invoque methode moveCapturePromote() du model (apres transformation des coordonnees)
	 * Si deplacement effectif sur model, invoque methode actionOnGui() de la view
	 * pour rafraichir affichage en fonction des donnees retournees par le model
	 */
	@Override
	public OutputModelData<Integer> moveCapturePromote(Integer toMovePieceIndex, Integer targetSquareIndex) {

		OutputModelData<Integer> outputControllerData = null;

		// TODO atelier 2

		// Inutile de reconstituer un objetOutputModelData<Integer>, aucun client ne le recupere en mode local
		return outputControllerData;
	}


	/**
	 * @param squareIndex
	 * @param length
	 * @return les coordonnees metier calculees e partir de l'index du SquareGUI sous la PieceGUI
	 */
	private Coord transformIndexToCoord (int squareIndex) {
		Coord coord = null;
		int  length = ModelConfig.LENGTH;
		char col = (char) ((squareIndex)%length + 'a');
		int ligne = length - (squareIndex)/length;
		coord = new Coord(col, ligne);
		return coord;
	}

	private int transformCoordToIndex (Coord coord) {
		int squareIndex = -1;
		int  length = ModelConfig.LENGTH;
		if (coord != null) {
			squareIndex = (length - coord.getLigne()) * length + (coord.getColonne()-'a');
		}
		return squareIndex;
	}


}




