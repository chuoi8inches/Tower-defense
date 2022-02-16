package projet;


import java.util.ArrayList;
import java.util.List;


public class Chemin {
	//Si vous souhaitez voir la création d'un chemin aléatoire, il faut:
	//-Mettre les attribut ci-dessous dans World://Positions des cases du chemin
												//	private List<Position> position = new ArrayList<>();
												//	
												//	//Liste des cases
												//	private List<Integer[]> chemin = new ArrayList<>();
												//	
												//	//Liste des directions prises par les monstres pour chaque case
												//	private List<String> direction = new ArrayList<>();
												//	
												//	//cases sur lesquelles on peut placer des tours
												//	private List<Position> casesToursImpossibles = new ArrayList<>();
	
	//-Utilise le constructeur de World ci-dessous
												//	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY) {
												//		this.width = width;
												//		this.height = height;
												//		this.nbSquareX = nbSquareX;
												//		this.nbSquareY = nbSquareY;
												//		squareWidth = (double) 1 / nbSquareX;
												//		squareHeight = (double) 1 / nbSquareY;
												//		Chemin c = new Chemin(startSquareX, startSquareY);//creation aleatoire d'un chemin
												//		chemin = c.getChemin();
												//		direction = c.getDirection();
												//		for(int i=0; i<chemin.size(); i++) {
												//			position.add(new Position(chemin.get(i)[0] * squareWidth + squareWidth / 2, chemin.get(i)[1] * squareHeight + squareHeight / 2));
												//		}
												//		System.out.println(direction);
												//		System.out.println(position);
												//		//spawn = new Position(startSquareX * squareWidth + squareWidth / 2, startSquareY * squareHeight + squareHeight / 2);
												//		StdDraw.setCanvasSize(width, height);
												//		StdDraw.enableDoubleBuffering();
												//	}
	private Integer[] start = new Integer[2];//Coordonnees de la case de spawn des monstres
	private List<Integer[]> casesChemin = new ArrayList<>();//Liste des cases formant le chemin de parcours des monstres dans l'ordre de la case de départ à la case d'arrivee
	private List<Integer[]> casesImpossibles = new ArrayList<>();//Listes des cases qui ne peuvent plus servir à la generation du chemin
	private List<Integer[]> casesPossibles = new ArrayList<>();//Chaque case du chemin est choisie aleatoirement dans cette liste
	
	
	/**
	 * Classe qui genere un chemin aleatoire partant d'un point de depart donne et d'une taille minimale de 50 cases
	 */
	public Chemin(int startX, int startY) {
		start[0] = startX;
		start[1] = startY;
		casesChemin.add(start);//Ajout du point de spawn a la liste des cases du chemin
		casesImpossibles.add(start);//Ajout du point de spawn a la liste des cases qui ne doivent pas etre utilisees pour former la suite du chemin
		generateWay();//Generation aleatoire du chemin
		System.out.println("Done generating way, coordinates are : ");
		for(int i=0; i<casesChemin.size(); i++) {
			System.out.println(casesChemin.get(i)[0] + " " + casesChemin.get(i)[1]);
		}
	}

	
	/**
	 * Fonction donnant la liste des cases qui peuvent etre choisies pour former a chaque iteration la suite du chemin
	 */
	private void casesPossibles() {
		while(casesPossibles.size()!=0) casesPossibles.remove(casesPossibles.get(casesPossibles.size()-1));//Vide la liste casesPossibles
		Integer[] derniereCase = casesChemin.get(casesChemin.size()-1);
		
		//Voisins de la derniere case generee
		Integer[] gauche = {derniereCase[0]-1, derniereCase[1]};
		Integer[] droit = {derniereCase[0]+1, derniereCase[1]};
		Integer[] haut = {derniereCase[0], derniereCase[1]+1};
		Integer[] bas = {derniereCase[0], derniereCase[1]-1};
		
		if(derniereCase[0]!=start[0] || derniereCase[1]!=start[1]) {//Si la derniere case generee est la case de depart, toutes ses cases voisines peuvent servir de case suivante, sinon retirer des cases possibles les cases voisines des autres cases du chemin
			Integer[] avantDerniereCase = casesChemin.get(casesChemin.size()-2);
			
			//Voisins de l'avant derniere case generee
			Integer[] gaucheAvantDerniere = {avantDerniereCase[0]-1, avantDerniereCase[1]};
			Integer[] droitAvantDerniere = {avantDerniereCase[0]+1, avantDerniereCase[1]};
			Integer[] hautAvantDerniere = {avantDerniereCase[0], avantDerniereCase[1]+1};
			Integer[] basAvantDerniere = {avantDerniereCase[0], avantDerniereCase[1]-1};
			
			//Ajout de l'avant derniere case et de ses cases voisines a la liste des cases impossibles
			casesImpossibles.add(gaucheAvantDerniere);
			casesImpossibles.add(droitAvantDerniere);
			casesImpossibles.add(hautAvantDerniere);
			casesImpossibles.add(basAvantDerniere);
			casesImpossibles.add(avantDerniereCase);
			
			//Ajout des cases voisines de la derniere case a la liste des cases possibles si elles ne sont pas deja voisines d'une autre case du chemin et si elles sont sur le plateau de jeu
			if(rechercheCasesImpossibles(gauche[0], gauche[1])==false) {
				if(gauche[0]>=0 && gauche[0]<=10 && gauche[1]>=0 && gauche[1]<=10) casesPossibles.add(gauche);
			}
			if(rechercheCasesImpossibles(droit[0], droit[1])==false) {
				if(droit[0]>=0 && droit[0]<=10 && droit[1]>=0 && droit[1]<=10) casesPossibles.add(droit);
			}
			if(rechercheCasesImpossibles(haut[0], haut[1])==false) {
				if(haut[0]>=0 && haut[0]<=10 && haut[1]>=0 && haut[1]<=10) casesPossibles.add(haut);
			}
			if(rechercheCasesImpossibles(bas[0], bas[1])==false) {
				if(bas[0]>=0 && bas[0]<=10 && bas[1]>=0 && bas[1]<=10) casesPossibles.add(bas);
			}
			
		}
		else {
			if(gauche[0]>=0 && gauche[0]<=10 && gauche[1]>=0 && gauche[1]<=10) casesPossibles.add(gauche);
			if(droit[0]>=0 && droit[0]<=10 && droit[1]>=0 && droit[1]<=10) casesPossibles.add(droit);
			if(haut[0]>=0 && haut[0]<=10 && haut[1]>=0 && haut[1]<=10) casesPossibles.add(haut);
			if(bas[0]>=0 && bas[0]<=10 && bas[1]>=0 && bas[1]<=10) casesPossibles.add(bas);
		}
		
	}

	
	/**
	 * Fonction de recherche pour savoir si une case appartient aux cases impossibles
	 */
	private boolean rechercheCasesImpossibles(int x, int y) {
		for(int i=0; i<casesImpossibles.size(); i++) {
			if((x == casesImpossibles.get(i)[0] && y == casesImpossibles.get(i)[1])) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Generation aleatoire du chemin partant de la case de depart choisie
	 */
	private void generateWay() {
		while(casesChemin.size()<50) {//Generation d'un chemin aleatoire jusqu'a en obtenir un de plus de 50 cases
			while(casesImpossibles.size()!=0) casesImpossibles.remove(casesImpossibles.get(casesImpossibles.size()-1));//Vide la liste casesImpossibles
			while(casesChemin.size()!=1) casesChemin.remove(casesChemin.get(casesChemin.size()-1));//Vide la liste casesChemin jusqu'a n'avoir que la case de spawn
			do {
				casesPossibles();
				int casePossibleRandom = (int)(Math.random()*casesPossibles.size());
				if(casesPossibles.size()!=0) casesChemin.add(casesPossibles.get(casePossibleRandom));//Choix aleatoire d'une case parmi les cases possibles pour generer une partie du chemin
			}
			while(casesPossibles.size()!=0);//Choix aleatoire d'une case a ajouter au chemin jusqu'a ce qu'on ne puisse plus en ajouter
		}
	}

	
	/**
	 * Getteur pour l'obtention du chemin aleatoire genere par la classe
	 * @return List<Integer[]> casesChemin la liste des cases formant le chemin
	 */
	public List<Integer[]> getChemin(){
		return casesChemin;
	}
}
