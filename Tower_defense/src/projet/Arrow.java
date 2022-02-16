package projet;

public class Arrow extends Projectile {
	public Arrow(Position p) {
		super(p);
		// TODO Auto-generated constructor stub
		this.damage = 2;
		this.speed = 0.04;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(p.x,p.y,"Asset/arrow.png",0.02,0.02);
	}

}
