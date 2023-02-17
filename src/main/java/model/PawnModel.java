package model;

import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

public class PawnModel implements PieceModel {

	private Coord coord;
	private PieceSquareColor pieceColor;

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord = coord;
		this.pieceColor = pieceColor;

	}

	@Override
	public char getColonne() {
		char colonne = this.coord.colonne;
		return colonne;
	}

	@Override
	public int getLigne() {
		int ligne = this.coord.ligne;
		return ligne;
	}

	@Override
	public boolean hasThisCoord(Coord coord) {
		boolean hasThisCoord = false;
		if (this.coord.compareTo(coord) == 0) {
			hasThisCoord = true;
		}
		return hasThisCoord;
	}

	@Override
	public PieceSquareColor getPieceColor() {
		PieceSquareColor color = this.pieceColor;
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String st = null;

		st = pieceColor + "[" + this.coord.ligne + "," + this.coord.colonne + "]";

		return st;
	}

	@Override
	public void move(Coord coord) {
		this.coord.colonne = coord.colonne;
		this.coord.ligne = coord.ligne;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {

		boolean ret = false;

		int distanceX = Math.abs(targetCoord.ligne - this.coord.ligne);
		int distanceY = Math.abs((int) targetCoord.colonne - (int) this.coord.colonne);

		if (isPieceToCapture) {
			ret = distanceX == distanceY && distanceX == 2;
		} else {
			ret = distanceX == distanceY && distanceX == 1;
		}
		return ret;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>();

		// TODO Atelier 2

		return coordsOnItinery;
	}

}
