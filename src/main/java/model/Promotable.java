package model;

public interface Promotable {
	
	/**
	 * @return true si le pion noir est arrive e la ligne 1
	 * ou si le pion blanc est arrive e la ligne MAX
	 */
	public boolean isPromotable();
	
	/**
	 * Effectue la promotion du pion
	 */
	public void promote();
}
