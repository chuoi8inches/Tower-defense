package projet;

public abstract class Monster {
	// Position du monstre à l'instant t
	Position p;
	// Vitesse du monstre
	double speed;
	// Position du monstre à l'instant t+1
	Position nextP;
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	boolean reached =false;
	// Point de vie du monstre
	int hp;
	// Rayon du cercle de hixbox du monstre
	double hitbox;
	// l'or que le joueur reçoit lorsqu'il tue un monstre
	int reward;
	//	Boolean pour savoir si le monstre peut voler
	boolean fly;
	// Compteur de déplacement pour savoir si le monstre à atteint le chateau du joueur
	int checkpoint = 0;
	
	public Monster(Position p) {
		this.p = p;
		this.nextP = new Position(p);
	}
	
	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	} 
	public void update() {
		if(this.hp>0){
		StdDraw.circle(p.x, p.y,hitbox);
		move();
		draw();
		}
	}
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
