package controller;

import java.io.Serializable;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Objet cre par le Model dans methode MoveCapturePromote()
 * e destination du Controller qui en extrait les donnees pour creer
 * l'objet InputViewModel e destination de la View
 * 
 */
public class OutputModelData<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public boolean isMoveDone = false;
	public T capturedPieceCoord = null;
	public T promotedPieceCoord = null;
	public PieceSquareColor promotedPieceColor = null;
	
	
	public OutputModelData(
			boolean isMoveDone, 
			T capturedPieceCoord,
			T promotedPieceCoord,
			PieceSquareColor promotedPieceColor) {
		super();
		this.isMoveDone = isMoveDone;
		this.capturedPieceCoord = capturedPieceCoord;
		this.promotedPieceCoord = promotedPieceCoord;
		this.promotedPieceColor = promotedPieceColor;
	}


	@Override
	public String toString() {
		return "DataAfterMove [isMoveDone=" + isMoveDone + ", capturedPieceIndex=" + capturedPieceCoord
				+ ", promotedPieceIndex=" + promotedPieceCoord + ", promotedPieceColor=" + promotedPieceColor + "]";
	}


	

	
}
