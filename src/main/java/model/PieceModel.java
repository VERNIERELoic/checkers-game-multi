package model;


import java.util.List;

import nutsAndBolts.PieceSquareColor;

public interface PieceModel {
	
	
	/**
	 * @return the coord
	 */
	public char getColonne() ;
	public int getLigne() ;
	
	/**
	 * @param coord
	 * @return true si la piece est aux coordonnees passees en parametre
	 */
	public boolean hasThisCoord(Coord coord);
	
	/**
	 * @param coord the coord to set
	 * le deplacement d'une piece change ses coordonnees
	 */
	public void move(Coord coord);


	/**
	 * @return the pieceColor
	 */
	public PieceSquareColor getPieceColor() ;
	
	
	/**
	 * @param targetCoord
	 * @param isPieceToCapture
	 * @return true si le deplacement est legal
	 */
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture);

	/**
	 * @param targetCoord
	 * @return liste des coordonnees des cases traversees par itineraire de deplacement
	 */
	public List<Coord> getCoordsOnItinerary(Coord targetCoord);

	
}

