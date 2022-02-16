package projet;

public class Bomber extends Tower {

	public Bomber(Position p) {
		// TODO Auto-generated constructor stub
		super(p);
		this.price = 60;
		this.attackspeed = 20;
		this.range = 0.2;
		this.upgraded = false;
		this.fire_sky = false;
	}
	public void draw() {
		if(!upgraded) StdDraw.picture(p.x, p.y,"Asset/bomber1.png",0.1,0.1);
		else StdDraw.picture(p.x, p.y,"Asset/bomber2.png",0.1,0.1);
	}
	
	public void fireAt(Position enemyPos) {
		StdDraw.circle(p.x, p.y,range);
		Bomb b = new Bomb(new Position(p.x, p.y + 0.05));
		b.nextP = enemyPos;
		projectiles.add(b);
	}
	public void upgrade() {
		if (!upgraded) {
			attackspeed = 15;
			range = 0.3;
			upgraded = true;
		}
		else return;
	}

}
