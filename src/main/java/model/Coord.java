package model;

import javafx.print.PrintColor;

/**
 * @author francoiseperrin
 *
 *         Coordonnees des PieceModel
 */
public class Coord implements Comparable<Coord> {

	char colonne; // ['a'..'j']
	int ligne; // [10..1]
	static final int MAX = ModelConfig.LENGTH; // 10

	public Coord(char colonne, int ligne) {
		super();
		this.colonne = colonne;
		this.ligne = ligne;
	}

	public char getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}

	@Override
	public String toString() {
		return "[" + ligne + "," + colonne + "]";
	}

	/**
	 * @param coord
	 * @return true si 'a' <= col < 'a'+MAX et 1 < lig <= MAX
	 */
	public static boolean coordonnees_valides(Coord coord) {

		boolean ret = false;
		char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
		boolean isLetterInArray = false;

		for (char col : letters) {
			if (col == coord.colonne) {
				isLetterInArray = true;
				break;
			}
		}

		if (coord.ligne <= MAX && isLetterInArray == true) {
			ret = true;
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * La methode compareTo() indique comment comparer un objet e l'objet courant
	 * selon l'ordre dit naturel
	 * Dans cet application, nous decidons que l'ordre naturel est celui
	 * correspondant au Ne de la case d'un tableau 2D represente par la Coord
	 * ainsi le Ne 1 correspond e la Coord ['a', 10], le Ne 100 correspond e la
	 * Coord ['j', 1]
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coord) {
			if (this.colonne == ((Coord) obj).colonne && this.ligne == ((Coord) obj).ligne) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int compareTo(Coord o) {
		if (o.ligne == this.ligne && (int) o.colonne == (int) this.colonne) {
			return 0;
		} else if (o.ligne < this.ligne || (int) o.colonne < (int) this.colonne) {
			return -1;
		} else {
			return 1;
		}
	}

}
