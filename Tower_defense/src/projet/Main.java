package projet;

public class Main {

	public static void main(String[] args) {
		int width = 1210;
		int height = 803;
		int nbSquareX = 11;
		int nbSquareY = 11;
		int startX = 5;
		int startY = 10;		
		
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY);
		
		Position p = new Position(World.spawn);
		Monster flymonster = new FlyMonster(p);
		w.monsters.add(flymonster);
		Monster basemonster = new BaseMonster(new Position(startX * World.squareWidth + World.squareWidth / 2, startY * World.squareHeight + World.squareHeight / 2));		
		w.monsters.add(basemonster);
		//Attention: nous ne pouvons pas réaliser la vague, mais pour tester autre chose, vous pouvez utiliser la triche pour ajouter de monstres
		//Utilisation prévue si Wave marche bien
		//		Wave waves = new Wave(width, height, nbSquareX, nbSquareY);//Instanciation des vagues
		//	       
		//        for(int i=1; i<=100; i++) {
		//            waves.newWave(i);//Création de 100 niveaux
		//        }
		
		// Lancement de la boucle principale du jeu	
		w.run();
	}
}

