//package projet;

//Ce que on essaie avec Wave, malheureusement ça n'a pas marché

//
//public class Wave {
//
//	private int width ;
//	private int height;
//	private int nbSquareX;
//	private int nbSquareY;
//	private int startX = (int)(11*Math.random());//point de spawn aleatoire
//	private int startY = (int)(11*Math.random());
//	private World w;
//	
//	public Wave (int width, int height, int nbSquareX, int nbSquareY) {
//		this.width = width;
//		this.height = height;
//		this.nbSquareX = nbSquareX;
//		this.nbSquareY = nbSquareY;
//		w = new World(width, height, nbSquareX, nbSquareY, startX, startY);//Création d'un monde
//	}
//	
//	
//	/**
//	 * Gestion des vagues de monstres en fonction du niveau
//	 */
//	public void newWave(int lvl) throws InterruptedException {
//		System.out.println("Prochaine vague dans 20sec...");
//		//Thread.sleep(20000);
//		w.monstersLife.clear();//suppression des monstres créés lors des précédentes vagues
//		for(int i=0; i<10*lvl; i++) {//Spawn d'un nombre de monstre égal à 10 fois le niveau
//			if(lvl%2==0) w.monsters.add(new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2)));
//			else w.monsters.add(new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2)));//Alternance entre deux niveaux entre les monstres volants et les monstres classiques
//			w.monsters.get(w.monsters.size()-1).speed = 0.01+0.0005*lvl;//Vitesse des monstres dépendante du niveau
//			w.monstersLife.add(10*lvl);//points de vie des monstres égal à 10*le niveau
//		}
//		w.setGold(w.getGold()+20*lvl);//argent gagné à la fin de chaque vague, augmentant avec le niveau
//		w.run();
//	}
//	
//	
//	
//}
