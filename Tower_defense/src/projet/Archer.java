package projet;

public class Archer extends Tower{
	
	
	public Archer(Position p) {
		super(p);
		// TODO Auto-generated constructor stub
		this.price = 50;
		this.attackspeed = 15;
		this.range = 0.3;
		this.upgraded = false;
		this.fire_sky = true;
	}
	public void draw() {
		if (upgraded == false ) {
			StdDraw.picture(p.x, p.y,"Asset/archer1.png",0.1,0.1);
		}
		else StdDraw.picture(p.x, p.y,"Asset/archer2.png",0.1,0.1);
	}
	
	public void fireAt(Position enemyPos) {
		StdDraw.circle(p.x, p.y,range);
		Arrow a = new Arrow(new Position(p.x, p.y + 0.05));
		a.nextP = enemyPos;
		projectiles.add(a);
	}
	public void upgrade() {
		if (upgraded ==false) {
			attackspeed =10;
			range = 0.4;
			upgraded = true;
		}
		else return;
	}
}
