package projet;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Monster> monsters = new ArrayList<Monster>();
	// l'ensemble des tours, pour gerer (notamment) l'affichage
	List<Tower> towers = new ArrayList<Tower>();
	// Position par laquelle les monstres vont venir
	static Position spawn;
	//List de position du point de virage
	List<Position> turnpoints = new ArrayList<Position>();
	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	static double squareWidth;
	static double squareHeight;
	
	// Nombre de points de vie du joueur
	int life = 20;
	
	// Nombre de points dor du joueur
	int gold = 100;
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	// Condition pour commnencer la partie
	boolean notStart = true;
	// Condition pour terminer la partie
	boolean end = false;
	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases données
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		spawn = new Position(startSquareX * squareWidth + squareWidth / 2, startSquareY * squareHeight + squareHeight / 2);
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground() {
		 for (int i = 0; i < nbSquareX; i++)
			 for (int j = 0; j < nbSquareY; j++)			 
				 StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2, "Asset/grass.png", squareWidth, squareHeight);		 
	 }
	 
	 /**
	  * Initialise le chemin sur la position du point de départ des monstres. Cette fonction permet d'afficher une route qui sera différente du décors.
	  */
	 public void drawPath() {
		 Position p = spawn;
		 for(int i = 0; i<3;i++) {
			 StdDraw.picture(p.x, p.y,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x - i*squareWidth, p.y - squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x - 2*squareWidth, p.y - 2*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
		 }
		 for(int i = 0; i<6;i++) {
			 StdDraw.picture(p.x + (i-2)*squareWidth, p.y - 3*squareHeight,"Asset/cobble1.png",squareWidth, squareHeight);
			 StdDraw.picture(p.x + 3*squareWidth, p.y - 4*squareHeight,"Asset/cobble1.png",squareWidth, squareHeight);
		 }
		 for(int i = 0; i<8;i++) {
			 StdDraw.picture(p.x + (3-i)*squareWidth, p.y - 5*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x - 4*squareWidth, p.y - 6*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
		 }
		 for(int i =0;i<10;i++) {
			 StdDraw.picture(p.x + (i-4)*squareWidth, p.y - 7*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x + 4*squareWidth, p.y - 8*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x + 5*squareWidth, p.y - 8*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
		 }
		 for(int i =0; i<10;i++) {
			 StdDraw.picture(p.x + (4-i)*squareWidth, p.y - 9*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x - 5*squareWidth, p.y - 10*squareHeight,"Asset/cobble1.png", squareWidth, squareHeight);
			 StdDraw.picture(p.x - 5*squareWidth, p.y - 10*squareHeight,"Asset/castle.png", squareWidth, squareHeight);
		 }
	 }
	 /**
	  * Affiche certaines informations sur l'écran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 StdDraw.setPenColor(StdDraw.BLACK);
		 StdDraw.text((nbSquareX-1) *squareWidth+squareWidth/2, (nbSquareY-1) *squareHeight+squareHeight/2, Integer.toString(life));
		 StdDraw.picture((nbSquareX-1) *squareWidth+squareWidth/4, (nbSquareY-1) *squareHeight+squareHeight/2, "Asset/heart.png",squareWidth/4,squareHeight/4);
		 StdDraw.text((nbSquareX-1) *squareWidth+squareWidth/2, (nbSquareY-1) *squareHeight+squareHeight/4, Integer.toString(gold));
		 StdDraw.picture((nbSquareX-1) *squareWidth+squareWidth/4, (nbSquareY-1) *squareHeight+squareHeight/4, "Asset/gold.png",squareWidth/4,squareHeight/4);
		 if(end) StdDraw.text(5.5*squareWidth,5.5*squareHeight,"Game over");
	 }
	 
	 /**
	  * Fonction qui récupère le positionnement de la souris et permet d'afficher une image de tour en temps réél
	  *	lorsque le joueur appuie sur une des touches permettant la construction d'une tour.
	  */
	 public void drawMouse() {
		double normalizedX = (int)(StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		switch (key) {
		case 'a' : 
			 // TODO Ajouter une image pour représenter une tour d'archers
			StdDraw.picture(normalizedX,normalizedY,"Asset/archer1.png",squareWidth,squareHeight);
			 break;
		case 'b' :
			// TODO Ajouter une image pour représenter une tour à canon
			StdDraw.picture(normalizedX,normalizedY,"Asset/bomber1.png",squareWidth,squareHeight);
			 break;
		}
	 }
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateMonsters() {
		//Ajoute les point de virage 
		Position p = spawn;
		turnpoints.add(new Position(p.x, p.y - squareHeight).normaliseP());
		turnpoints.add(new Position(p.x - 2*squareWidth, p.y - squareHeight).normaliseP());		//Les points de virage
		turnpoints.add(new Position(p.x - 2*squareWidth, p.y - 3*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x + 3*squareWidth, p.y - 3*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x + 3*squareWidth, p.y - 5*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x - 4*squareWidth, p.y - 5*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x - 4*squareWidth, p.y - 7*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x + 5*squareWidth, p.y - 7*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x + 4*squareWidth, p.y - 9*squareHeight).normaliseP());	
		turnpoints.add(new Position(p.x - 5*squareWidth, p.y - 9*squareHeight).normaliseP());
		turnpoints.add(new Position(p.x - 5*squareWidth, p.y - 10*squareHeight).normaliseP());
		
		Iterator<Monster> i = monsters.iterator();
		Monster m;
		while (i.hasNext() && !notStart) {
			 m = i.next();
			 for(Position pos: turnpoints) {
				 if(m.p.normaliseP().dist(spawn) >= m.nextP.normaliseP().dist(spawn)) { 
					 m.p.x = m.p.normaliseP().x;
					 m.p.y = m.p.normaliseP().y;
					 m.nextP = pos.normaliseP();
				 }
			 }
			 if(m.p.normaliseP().equals(new Position(p.x - 5*squareWidth, p.y - 10*squareHeight).normaliseP())) m.reached =true;//verifier si le monster a atteint le chateau
			 m.update();
		 }
	 }
	 /**
	  * Pour chaque tour dans la liste des tours,utilise fireAt(Monster) qui génère les porjectiles et utilise la fonction updateProjectiles() qui appelle les fonctions move() et draw() de Projectile.
	  * Modifie la position du protjectile au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateTowers() {
			Iterator<Tower> i = towers.iterator();
			Tower t;
			while (i.hasNext()) {
				 t = i.next();
				 t.draw();
				 boolean firing = false;
				 for(Monster m: monsters) { 
					 Position currentMonsterPosition = m.p;
					 if(t.p.dist(currentMonsterPosition) <= t.range &&(t.fire_sky||!m.fly) &&m.hp>0 && !firing ) {
						 t.fireAt(currentMonsterPosition);
						 StdDraw.pause(t.attackspeed);
						 firing =true;
						 for(int j = 0;j < t.projectiles.size();j++) {
							 if(m.p.dist(t.projectiles.get(j).p) <= m.hitbox) {
								 m.hp-=t.projectiles.get(j).damage;
								 t.projectiles.remove(j);
							 }
							 if(m.hp<=0) {
								 gold +=m.reward;
								 firing = false;
								 t.projectiles.clear();
							 }							 
						 }
						 t.updateProjectiles();
					 }
				 }
			 }
		 }
	 
	 
	 /**
	  * Met à jour toutes les informations du plateau de jeu ainsi que les déplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		drawBackground();
		drawPath();
		drawInfos();
		updateMonsters();
		updateTowers();
		drawMouse();
		//le joueur perd un vie si un monstre atteint le château
		for(Monster m: monsters) {
			if(m.reached && m.checkpoint ==0) {
				life--;
				m.checkpoint++;
			}
		}
		return life;
	 }
	 
	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour la touche séléctionnée
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (50g).");
			break;
		case 'b':
			System.out.println("Bomb Tower selected (60g).");
			break;
		case 'e':
			System.out.println("Evolution selected (40g).");
			break;
		case 's':
			System.out.println("Starting game!");
			System.out.println("Monsters come in 20 seconds, meanwhile you can't place tower");
			notStart = false;
			break;
		case 'c':
			System.out.println("Cheating...");
			System.out.println("Press g and click to add 100 gold");
			System.out.println("Press k and click to kill all monster");
			System.out.println("Press i to be immortal(add 100 lives)");
			System.out.println("Press m and click to add a base monster");
			System.out.println("Press f and click to add a fly monster");
			break;
		case 'q':
			System.out.println("Exiting.");
			this.end = true;
		}
	}
	
	/**
	 * Vérifie lorsque l'utilisateur clique sur sa souris qu'il peut: 
	 * 		- Ajouter une tour à la position indiquée par la souris.
	 * 		- Améliorer une tour existante.
	 * Puis l'ajouter à la liste des tours
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
		Position p = new Position(normalizedX, normalizedY);
		List<Position> listBannedPosition = new ArrayList<Position>();//List de position la tour ne peut pas depasser
		 for(int i = 0; i<3;i++) {
			 Position currentPos = new Position(spawn.x - i*World.squareWidth, spawn.y - World.squareHeight);
			 listBannedPosition.add(currentPos);
		 }
		 for(int i = 0; i<6;i++) {
			 Position currentPos = new Position(spawn.x + (i-2)*World.squareWidth, spawn.y - 3*World.squareHeight);
			 listBannedPosition.add(currentPos);
		 }
		 for(int i = 0; i<8;i++) listBannedPosition.add(new Position(spawn.x + (3-i)*World.squareWidth, spawn.y - 5*World.squareHeight));
		 for(int i = 0; i<10;i++)  listBannedPosition.add(new Position(spawn.x + (i-4)*World.squareWidth, spawn.y - 7*World.squareHeight));
		 for(int i = 0; i<10;i++) listBannedPosition.add(new Position(spawn.x + (4-i)*World.squareWidth, spawn.y - 9*World.squareHeight));
		 
		 boolean isBannedPosition = false;//boolean pour savoir si position p est invalide
		 for(Position pos: listBannedPosition){
			 if(p.equals(pos.normaliseP()) || p.equals(spawn)) {
				 isBannedPosition = true;
				 break;
			 }
		 }
	
		switch (key) {
		case 'a':
			if(gold>=50) {
			Archer a = new Archer(p);
			if(!towers.stream().anyMatch(t -> t.p.equals(a.p)) && !isBannedPosition) {
			towers.add(a);
			gold-=a.price;
			a.draw();
			}
			break;
			}break;
		case 'b':
			if(gold>=60) {
				Bomber b = new Bomber(p);
				if(!towers.stream().anyMatch(t -> t.p.equals(b.p)) && !isBannedPosition) {
				b.draw();
				towers.add(b);
				gold-=b.price;
				}
				break;
			}break;
		case 'e':
			if(gold>=40) {
			for(Tower t: towers) {
				if(!t.upgraded && t.p.x == p.x && t.p.y == p.y) {
					t.upgrade();
					gold-=40;
					break;
				}
			}
			}break;
		case 'g':gold+=300; break;
		case 'k': monsters.clear(); break;
		case 'm': monsters.add(new BaseMonster(new Position(spawn.x,spawn.y))); break;
		case 'f': monsters.add(new FlyMonster(new Position(spawn.x,spawn.y))); break;
		case 'i': life += 100; break;
		}
	}
	
	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal les différentes possibilités 
	 * offertes au joueur pour intéragir avec le clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost 50g).");
		System.out.println("Press B to select Cannon Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press C to cheat.");
		System.out.println("Press S to start.");
	}
	
	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		boolean hasWait = false;//Un boolean pour savoir si le jeu attendre 20 seconds d'après le joueur appuie S
		while(!end) {
			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			if(!notStart && !hasWait) {//si joueur appuie S pour le premier fois, il a 20 seconds avant l'apparition de monstres 
				StdDraw.pause(20000);
				hasWait = true;
			}
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			update();
			if(update()==0) { end = true; System.out.println("Game over");}
			StdDraw.show();
			StdDraw.pause(20);			
		}
	}
}
