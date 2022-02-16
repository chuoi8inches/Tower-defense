package projet;

public class FlyMonster extends Monster {
	
	public FlyMonster(Position p) {
		// TODO Auto-generated constructor stub
		super(p);
		this.hp = 3;
		this.speed = 0.02;
		this.reward = 8;
		this.fly = true;
		this.hitbox = 0.01;
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(p.x, p.y, "Asset/bat.png",0.07,0.07);
	}
	

}
