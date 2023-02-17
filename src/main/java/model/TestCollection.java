package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 
 */
public class TestCollection {

	public static void main(String[] args) {
		
		// la collection de pieces en jeu - melange noires et blanches
		Collection<PieceModel> pieces = null ;	
	
		
		/* Implementation de la Collection par une LinkedList 
		
		* Remplissage 
		* Affichage selon l'ordre d'insertion dans collection - 5 pieces par ligne
		* Puis tests de differents algos de tri avec affichage
		* */
		pieces = new LinkedList<PieceModel>();
		pieces = fillCollection(pieces);
		System.out.println("Affichage LinkedList selon l'ordre d'insertion dans collection");
		System.out.println(getRender(pieces));
		
//		TestSortList(pieces); // Test de tri de la liste implementee par une LinkedList
//
//		
//		/* Implementation de la Collection par une ArrayList 
//		
//		* Remplissage 
//		* Affichage selon l'ordre d'insertion dans collection
//		* Puis tests de differents algos de tri avec affichage
//		* */
//		pieces = new ArrayList<PieceModel>();
//		pieces = fillCollection(pieces);
//		System.out.println("\nAffichage ArrayList selon l'ordre d'insertion dans collection");
//		System.out.println(getRender(pieces));
//		TestSortList(pieces);
//		
//		/* Implementation de la Collection par un HashSet  
//		
//		* Remplissage 1 serie de pions blancs et de pions noirs
//		*    puis 2 series de pions noirs
//		* Affichage selon l'ordre d'insertion dans collection 
//		*    <-- KO avant MAJ classe AbstractPiece
//		* Puis tests tri <-- KO
//		* */
//		pieces = new HashSet<PieceModel>();
//		pieces = fillCollection(pieces);
//		System.out.println("\nAffichage HashSet selon l'ordre d'insertion dans collection");
//		System.out.println(getRender(pieces));
////		Collections.sort((Set<PieceModel>) pieces); // --> KO compilation : pas tri sur Set
//	
//		
//		/* Implementation de la Collection par un TreeSet  
//		
//		* Remplissage 
//		* Affichage selon l'ordre d'insertion dans collection 
//		* */
//		pieces = new TreeSet<PieceModel>();
//		// pieces = new TreeSet<PieceModel>(new PieceComparator());
//		pieces = fillCollection(pieces);
//		System.out.println("\nAffichage TreeSet selon l'ordre d'insertion dans collection");
//		System.out.println(getRender(pieces));
	}

	
	private static Collection<PieceModel> fillCollection(Collection<PieceModel> pieces) {
		
		// Creation des pion blancs et ajout dans la collection de pieces
		for ( Coord coord : ModelConfig.WHITE_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.WHITE));
		}

		// Creation des pions noirs et ajout dans la collection de pieces
		for ( Coord coord : ModelConfig.BLACK_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		}

		//	// Creation des pions noirs et ajout e nouveau dans la collection de pieces
		//  // Utile pour verifier pb hashcode sur les hashSet
		//	for ( Coord coord : ModelConfig.BLACK_PIECE_COORDS){
		//		pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		//	}

		return pieces;
	}

	private static void TestSortList(Collection<PieceModel> pieces) {

//		// Affichage dans ordre croissant des cases sur le damier (de 0 e 99)
//		// Tri selon ordre naturel des PieceModel
//		Collections.sort((List<PieceModel>) pieces); 
//		System.out.println("Affichage selon l'ordre croissant des cases");
//		System.out.println(getRender(pieces));
//
//		// Affichage dans ordre croissant des col puis des lignes
//		// Tri avec un autre comparateur
//		Collections.sort((List<PieceModel>)pieces, new PieceComparator()); 
//		System.out.println("Affichage selon l'ordre croissant des col puis des lignes");
//		System.out.println(getRender(pieces));	
	}
	
	private static String getRender(Collection<PieceModel> pieces) {

		String st = "";
		int i = 1;

		// Parcours collection en utilisant boucle for-each
		for(PieceModel piece : pieces ) {
			st += piece;
			if (i++%5 == 0)
				st+= "\n";
		}
		
		// Parcours collection en utilisant un iterateur ou un ListIterator
		// TODO
		
		return st;	
	}

}

class PieceComparator  implements Comparator<PieceModel>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * 
	 * Permet de comparer en 1er sur les colonnes puis sur les lignes
	 */
	@Override
	public int compare(PieceModel o1, PieceModel o2) {

		int comp = 0;
		
		// TODO
		
		return comp;
	}
}


