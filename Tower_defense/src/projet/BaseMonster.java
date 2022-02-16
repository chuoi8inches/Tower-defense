package projet;

public class BaseMonster extends Monster {
	
	public BaseMonster(Position p) {
		super(p);
		this.hp = 5;
		this.speed = 0.01;
		this.reward = 5;
		this.fly = false;
		this.hitbox = 0.02;
	}
	
	/**
	 * Affiche un monstre qui change de couleur au cours du temps
	 * Le monstre est représenté par un cercle de couleur bleue ou grise
	 */

	public void draw() {
		StdDraw.picture(p.x, p.y, "Asset/basemonster.png",0.09,0.09);
	}
	
}
