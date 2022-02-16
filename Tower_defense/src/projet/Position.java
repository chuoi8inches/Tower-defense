package projet;

public class Position {
	double x;
	double y;
	
	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres et des tours
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position p) {
		x = p.x;
		y = p.y;
	}
	
	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}
	
	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}
	/**
	 * Renvoie la position normalisée du p
	 * @return position
	 */
	public Position normaliseP() {
		double normalizedX = (int)(this.x / World.squareWidth) * World.squareWidth + World.squareWidth / 2;
		double normalizedY = (int)(this.y / World.squareHeight) * World.squareHeight +World. squareHeight / 2;
		return new Position(normalizedX, normalizedY);
	}
	
	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
}
