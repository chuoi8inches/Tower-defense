package projet;

public class Bomb extends Projectile {

	public Bomb(Position p) {
		super(p);
		// TODO Auto-generated constructor stub
		this.damage = 8;
		this.speed = 0.02;
	}
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(p.x,p.y,"Asset/bomb.png",0.02,0.02);
	}

}
