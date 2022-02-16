package projet;

import java.util.LinkedList;
import java.util.List;

public abstract class Tower {
	Position p;
	//Cout d'or d'une tour
	int price;
	//Sa vitesse d'attaque
	int attackspeed;
	//Sa portee
	double range;
	//Un boolean pour savoir si la tour est amelioree
	boolean upgraded = false;
	//Un boolean pour savoir si la tour peut attaquer les monstres arienne
	boolean fire_sky;
	//Les projectiles que la tour a tirés
	List<Projectile> projectiles = new LinkedList<Projectile>();
	public Tower(Position p) {
		this.p = p;
	}
	public abstract void draw();
	public abstract void upgrade();
	/*
	 * Fonction pour faire tirer la tour à la position choisie
	 */
	public abstract void fireAt(Position enemyPos);
	
	/*
	 * Mise à jour les projectiles tirées
	 */
	public void updateProjectiles() {
		for(Projectile pr: projectiles) {
			if(p.dist(pr.p) <= range) pr.update();
		}
	 }
}
