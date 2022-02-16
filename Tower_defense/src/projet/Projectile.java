package projet;

public abstract class Projectile {
	//Degat du projectile
	int damage;
	//Vitesse de deplacement du projectile
	double speed;
	//Position du projectile au depart
	Position p;
	//Destination du projectile 
	Position nextP;
	public Projectile(Position p) {
		this.p = p;
		this.nextP = new Position(p);
	}

	public void move() {
		// Mesure sur quel axe le projectile se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle le projectile à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
			}
	}
	public abstract void draw() ;
	
	public void update() {
		move();
		draw();
	}
}
