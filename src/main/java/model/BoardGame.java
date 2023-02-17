package model;

import controller.OutputModelData;

/**
 * @author francoise.perrin
 *
 * Cette interface definit le comportement attendu des jeux de plateau
 * @param <T>
 * 
 * 
 */
public interface BoardGame<T>  {

	/**
	 * @param toMovePieceIndex
	 * @param targetSquareIndex
	 * @return 1 objet complexe
	 * 		- true si le deplacement a ete effectue, false sinon
	 * 		- eventuellement les coordonnees de la piece capturee, null sinon 
	 * 		- eventuellement les coordonnees et la couleur du pion promus en dame, null sinon
	 */
	public OutputModelData<T> moveCapturePromote(T toMovePieceIndex, T targetSquareIndex);
	
}